package org.example.backend.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.backend.entities.SaleItemBase;
import org.example.backend.exceptions.SaleItemNotFoundException;
import org.example.backend.repositories.SaleItemBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleItemService {
    @Autowired
    private SaleItemBaseRepository saleItemBaseRepository;

    @PersistenceContext
    private EntityManager em;

    public List<SaleItemBase> getAll() {
        return saleItemBaseRepository.findAll();
    }

    public List<SaleItemBase> getAllByCreateTime() {
        return saleItemBaseRepository.findAllByOrderByCreatedOnAscIdAsc();
    }

    public SaleItemBase getById(int id) {
        return saleItemBaseRepository.findById(id).orElseThrow(
                () -> new SaleItemNotFoundException("SaleItem not found for this id :: " + id)
        );
    }

    @Transactional
    public SaleItemBase createSaleItem(SaleItemBase saleItemBase){
        SaleItemBase created = saleItemBaseRepository.save(saleItemBase);
        em.flush();
        em.refresh(created);
        return created;
    }

    @Transactional
    public SaleItemBase updateSaleItem(SaleItemBase saleItemBase) {
        SaleItemBase saved = saleItemBaseRepository.save(saleItemBase);
        em.flush();
        em.refresh(saved);
        return saved;
    }


    public void deleteById(int id) {
        SaleItemBase saleItem = getById(id);
        saleItemBaseRepository.delete(saleItem);
    }

    public List<SaleItemBase> getAllByBrandId(int brandId) {
        return saleItemBaseRepository.findAllByBrandId(brandId);
    }

    public Page<SaleItemBase> getByBrandNameIn(List<String> filterBrands, Integer pageNum, Integer pageSize, String sortField, String sortDirection) {

        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortField);
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sort);

        if (filterBrands == null || filterBrands.isEmpty()) {
            return saleItemBaseRepository.findAll(pageRequest);
        } else {
            return saleItemBaseRepository.findByBrandNameIn(filterBrands, pageRequest);
        }
    }



}

