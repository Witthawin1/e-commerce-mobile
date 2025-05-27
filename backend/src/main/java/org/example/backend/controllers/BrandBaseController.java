package org.example.backend.controllers;

import org.example.backend.dtos.*;
import org.example.backend.entities.BrandBase;
import org.example.backend.entities.SaleItemBase;
import org.example.backend.exceptions.BrandNotFoundException;
import org.example.backend.exceptions.DuplicateBrandException;
import org.example.backend.exceptions.InvalidBrandException;
import org.example.backend.exceptions.ServerException;
import org.example.backend.services.BrandBaseService;
import org.example.backend.services.SaleItemService;
import org.example.backend.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/itb-mshop")
@CrossOrigin(origins = {
    "http://localhost:5173"
})

public class BrandBaseController {
    @Autowired
    private BrandBaseService brandBaseService;
    @Autowired
    private SaleItemService saleItemService;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/v1/brands")
    public ResponseEntity<List<BrandBaseDTO>> getAllBrands() {
        List<BrandBase> brands = brandBaseService.getAllBrands();
        List<BrandBaseDTO> brandsDTO = listMapper.mapList(brands, BrandBaseDTO.class, modelMapper);
        return ResponseEntity.ok(brandsDTO);
    }

    @GetMapping("/v1/brands/{id}")
    public ResponseEntity<BrandDetailDTO> getBrandById(@PathVariable Integer id) {
            BrandBase brand = brandBaseService.getById(id);
            BrandDetailDTO responseDto = modelMapper.map(brand, BrandDetailDTO.class);
            responseDto.setName(brand.getName());
            responseDto.setNoOfSaleItems(saleItemService.getAllByBrandId(brand.getId()).size());
            return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/v1/brands")
    public ResponseEntity<BrandDetailDTO> createBrand(@RequestBody BrandBaseCreateDTO dto) {
            if (dto.getName() == null || dto.getName().trim().isEmpty()) {
                throw new InvalidBrandException("Brand name is required.");
            }
            if (brandBaseService.existsByNameIgnoreCase(dto.getName().trim())) {
                throw new DuplicateBrandException("Brand name already exists.");
            }
            BrandBase brandBase = modelMapper.map(dto, BrandBase.class);
            BrandBase saved = brandBaseService.createBrand(brandBase);
            BrandDetailDTO responseDto = modelMapper.map(saved, BrandDetailDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/v1/brands/{id}")
    public ResponseEntity<BrandDetailDTO> updateBrand(@PathVariable Integer id, @RequestBody BrandBaseEditDTO dto) {
            BrandBase existingItem = brandBaseService.getById(id);
            if (existingItem == null) {
                throw new BrandNotFoundException("brand not found");
            }
            if (dto.getName() == null || dto.getName().trim().isEmpty()) {
                throw new InvalidBrandException("Brand name is required.");
            }
            if (brandBaseService.existsByNameIgnoreCase(dto.getName().trim()) && !(existingItem.getName().equalsIgnoreCase(dto.getName()))) {
                throw new DuplicateBrandException("Brand name already exists.");
            }
            modelMapper.map(dto, existingItem);
            existingItem.setId(id);
            BrandBase updated = brandBaseService.updateBrand(existingItem);
            BrandDetailDTO responseDto = modelMapper.map(updated, BrandDetailDTO.class);
            responseDto.setName(updated.getName());
            responseDto.setNoOfSaleItems(saleItemService.getAllByBrandId(existingItem.getId()).size());
            return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/v1/brands/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
            BrandBase existingItem = brandBaseService.getById(id);
            if (existingItem == null) {
                throw new BrandNotFoundException("brand not found");
            }
            brandBaseService.deleteBrandById(id);
            return ResponseEntity.noContent().build();
    }

//    @PatchMapping("/v1/brands/{id}/softDelete")
//    public ResponseEntity<Void> softDeleteBrand(@PathVariable Integer id) {
//        try {
//            BrandBase brand = brandBaseService.getById(id);
//            if (brand == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//            brand.setIsActive(false);
//            brandBaseService.updateBrand(brand);
//            return ResponseEntity.noContent().build();
//        } catch (RuntimeException e) {
//            if (e.getMessage().contains("not found")) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//            }
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}