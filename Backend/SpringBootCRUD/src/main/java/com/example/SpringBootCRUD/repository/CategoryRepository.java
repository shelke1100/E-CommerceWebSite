package com.example.SpringBootCRUD.repository;

import com.example.SpringBootCRUD.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {

    CategoryEntity getById(int CategoryID);
}
