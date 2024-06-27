package com.example.SpringBootCRUD.controller;

import com.example.SpringBootCRUD.Entity.CategoryEntity;
import com.example.SpringBootCRUD.Service.CategoryService;
import com.example.SpringBootCRUD.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody CategoryEntity categoryEntity) {
        categoryService.createCategory(categoryEntity);
        return new ResponseEntity<>(new ApiResponse(true, "Created a new category"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<CategoryEntity> listCategories() {
        return categoryService.listCategories();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody CategoryEntity categoryEntity) {
        if (!categoryService.findById(categoryId)) {
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId, categoryEntity);
        return new ResponseEntity<>(new ApiResponse(true, "Category updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int categoryId) {
        if (!categoryService.findById(categoryId)) {
            return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"), HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse(true, "Category deleted successfully"), HttpStatus.OK);
    }
}

//************************************************************
//package com.example.SpringBootCRUD.controller;
//
//import com.example.SpringBootCRUD.Entity.EntityCategory;
//import com.example.SpringBootCRUD.Service.CategoryService;
//import com.example.SpringBootCRUD.common.ApiResponse;
//import jdk.jfr.Category;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/category")
//public class CategoryController {
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @PostMapping("/create")
//    public ResponseEntity<ApiResponse> createCategory(@RequestBody EntityCategory EntityCategory)
//
//    {
//        categoryService.createCategory(EntityCategory);
//        return new ResponseEntity<>(new ApiResponse(true, "create a new category"), HttpStatus.CREATED);
//    }
//    @GetMapping("/list")
//    public List<EntityCategory> listCategory()
//    {
//        return categoryService.ListCategory();
//    }
//
//    @PostMapping("/update/{categoryId}")
//    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody EntityCategory EntityCategory) {
//        System.out.println("category id"+categoryId);
//        if (!categoryService.findById(categoryId))
//        {
//            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category does not exist"), HttpStatus.NOT_FOUND);
//
//        }
//        categoryService.editCategory(categoryId, EntityCategory);
//        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Category updated successfully"), HttpStatus.OK);
//    }
//}
