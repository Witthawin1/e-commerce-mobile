package org.example.backend.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.backend.entities.BrandBase;
import org.example.backend.entities.SaleItemBase;
import org.example.backend.exceptions.BrandNotFoundException;
import org.example.backend.exceptions.BrandhasSaleItemException;
import org.example.backend.repositories.BrandBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandBaseService {
    @Autowired
    private BrandBaseRepository brandBaseRepository;

    @PersistenceContext
    private EntityManager em;

    public List<BrandBase> getAllBrands() {
        return brandBaseRepository.findAllByOrderByCreatedOnAscIdAsc();
    }

    public BrandBase getById(int id) {
        return brandBaseRepository.findById(id).orElseThrow(() -> new BrandNotFoundException("Brand not found with id: " + id));
    }

    @Transactional
    public BrandBase createBrand(BrandBase brandBase){
        BrandBase created = brandBaseRepository.save(brandBase);
        em.flush();
        em.refresh(created);
        return created;
    }
    public boolean existsByNameIgnoreCase(String name) {
        return brandBaseRepository.existsByNameIgnoreCase(name);
    }

    @Transactional
    public BrandBase updateBrand(BrandBase brandBase) {
        BrandBase saved =  brandBaseRepository.save(brandBase);
        em.flush();
        em.refresh(saved);
        return saved;
    }

    public void deleteBrandById(int id) {
        BrandBase brand = brandBaseRepository.findById(id).orElseThrow(() -> new BrandNotFoundException("Brand not found with id: " + id));
        if (!brand.getSaleItemBases().isEmpty()) {
            throw new BrandhasSaleItemException("Brand has sale item(s) and cannot be deleted.");
        }
        brandBaseRepository.delete(brand);
    }
}
