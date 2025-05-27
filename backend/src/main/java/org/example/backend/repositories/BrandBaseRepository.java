package org.example.backend.repositories;

import org.example.backend.entities.BrandBase;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import java.util.List;

public interface BrandBaseRepository extends JpaRepository<BrandBase, Integer> {
    List<BrandBase> findAllByOrderByCreatedOnAscIdAsc();
    BrandBase findByName(String name);
    boolean existsByNameIgnoreCase(String name);
}

