package org.example.backend.controllers;

import org.example.backend.dtos.*;
import org.example.backend.entities.SaleItemBase;
import org.example.backend.services.BrandBaseService;
import org.example.backend.services.SaleItemService;
import org.example.backend.utils.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/itb-mshop")
@CrossOrigin(origins = {
        "http://localhost:5173"
})

public class SaleItemController {
    @Autowired
    private SaleItemService saleItemService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;
    @Autowired
    private BrandBaseService brandBaseService;

    @GetMapping("/v1/sale-items")
    public ResponseEntity<List<SaleItemDTO>> getSalesItems() {
        List<SaleItemBase> items = saleItemService.getAllByCreateTime();
        List<SaleItemDTO> dto = listMapper.mapList(items, SaleItemDTO.class, modelMapper);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/v2/sale-items")
    public ResponseEntity<PageResponseDTO> getSalesItemsWithPage(@ModelAttribute PageRequestDTO request) {
        Page<SaleItemBase> saleItems = saleItemService.getByBrandNameIn(
                request.getFilterBrands(), request.getPage(), request.getSize()
                , request.getSortField() , request.getSortDirection()
        );

        // Convert content to DTO
        List<SaleItemDetailDTO> contentDTOs = saleItems.getContent()
                .stream()
                .map(item -> modelMapper.map(item, SaleItemDetailDTO.class))
                .collect(Collectors.toList());

        // Set content and pagination into response DTO
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setContent(contentDTOs);
        pageResponseDTO.setPage(saleItems.getNumber());
        pageResponseDTO.setSize(saleItems.getSize());
        pageResponseDTO.setTotalElements((int) saleItems.getTotalElements());
        pageResponseDTO.setTotalPages(saleItems.getTotalPages());
        pageResponseDTO.setFirst(saleItems.isFirst());
        pageResponseDTO.setLast(saleItems.isLast());
        pageResponseDTO.setSort(request.getSortField() + ": " + request.getSortDirection());
        return ResponseEntity.ok(pageResponseDTO);
    }


    @GetMapping("/v1/sale-items/{id}")
    public ResponseEntity<SaleItemDetailDTO> getSaleItemById(@PathVariable Integer id) {
        return ResponseEntity.ok(modelMapper.map(saleItemService.getById(id), SaleItemDetailDTO.class));
    }

    @PostMapping("/v1/sale-items")
    public ResponseEntity<SaleItemDetailDTO> createSaleItem(@RequestBody SaleItemCreateDTO dto){

        SaleItemBase saleItemBase = modelMapper.map(dto, SaleItemBase.class);
        saleItemBase.setBrand(brandBaseService.getById(dto.getBrand().getId()));
        SaleItemBase saved = saleItemService.createSaleItem(saleItemBase);
        SaleItemDetailDTO responseDto = modelMapper.map(saved,SaleItemDetailDTO.class);
        responseDto.setBrandName(saved.getBrand().getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/v1/sale-items/{id}")
    public ResponseEntity<SaleItemDetailDTO> updateSaleItem(@PathVariable Integer id, @RequestBody SaleItemEditDTO dto) {
        SaleItemBase existingItem = saleItemService.getById(id);

        SaleItemBase saleItemBase = modelMapper.map(dto, SaleItemBase.class);
        saleItemBase.setId(existingItem.getId());
        saleItemBase.setBrand(brandBaseService.getById(dto.getBrand().getId()));

        SaleItemBase saved = saleItemService.updateSaleItem(saleItemBase);

        SaleItemDetailDTO responseDto = modelMapper.map(saved, SaleItemDetailDTO.class);

        responseDto.setBrandName(saved.getBrand().getName());
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/v1/sale-items/{id}")
    public ResponseEntity<Void> deleteSaleItem(@PathVariable Integer id) {
        saleItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
