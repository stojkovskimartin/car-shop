package com.example.emtseminarska.repository;

import com.example.emtseminarska.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Transactional
    void removeById(Long id);
}
