package org.example.backend.repositories;

import org.example.backend.entities.SaleItemBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaleItemBaseRepository extends JpaRepository<SaleItemBase, Integer> {
    List<SaleItemBase> findAllByOrderByCreatedOnAscIdAsc();
    List<SaleItemBase> findAllByBrandId(Integer brandId);

    @Query("SELECT s FROM SaleItemBase s JOIN s.brand b WHERE b.name IN :brandNames")
    Page<SaleItemBase> findByBrandNameIn(List<String> brandNames, Pageable pageable);
}

