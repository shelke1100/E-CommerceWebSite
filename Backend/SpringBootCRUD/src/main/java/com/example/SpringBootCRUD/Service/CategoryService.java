package com.example.SpringBootCRUD.Service;

import com.example.SpringBootCRUD.Entity.CategoryEntity;
import com.example.SpringBootCRUD.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public void createCategory(CategoryEntity categoryEntity) {
        categoryRepo.save(categoryEntity);
    }

    public List<CategoryEntity> listCategories() {
        return categoryRepo.findAll();
    }

    public void editCategory(int categoryId, CategoryEntity updateCategory) {
        Optional<CategoryEntity> optionalCategory = categoryRepo.findById(categoryId);
        if (optionalCategory.isPresent()) {
            CategoryEntity category = optionalCategory.get();
            category.setCategoryName(updateCategory.getCategoryName());
            category.setDescription(updateCategory.getDescription());
            category.setImageUrl(updateCategory.getImageUrl());
            categoryRepo.save(category);
        } else {
            throw new IllegalArgumentException("Category with ID " + categoryId + " not found");
        }
    }

    public boolean findById(int categoryId) {
        return categoryRepo.findById(categoryId).isPresent();
    }

    public void deleteCategory(int categoryId)
    {
        categoryRepo.deleteById(categoryId);
    }
}


//package com.example.SpringBootCRUD.Service;
//
//import com.example.SpringBootCRUD.Entity.EntityCategory;
//import com.example.SpringBootCRUD.repository.CategoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class CategoryService {
//
//    @Autowired
//    CategoryRepository categoryRepo;
//
//    public void createCategory(EntityCategory EntityCategory) {
//        categoryRepo.save(EntityCategory);
//    }
//    public List<EntityCategory> ListCategory() {
//        return categoryRepo.findAll();
//    }
//    @Transactional
//    public void editCategory(int categoryId, EntityCategory updateCategory) {
//        EntityCategory category = categoryRepo.findById(categoryId);
//        category.setCategory_name(updateCategory.getCategory_name());
//        category.setDescription(updateCategory.getDescription());
//        category.setImage_url(updateCategory.getImage_url());
//        categoryRepo.save(category);
//    }
//    public boolean findById(int categoryId) {
//        return categoryRepo.findById(categoryId).isPresent();
//    }


//}
