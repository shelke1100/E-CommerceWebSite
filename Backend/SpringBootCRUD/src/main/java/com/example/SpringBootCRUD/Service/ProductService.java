package com.example.SpringBootCRUD.Service;

import com.example.SpringBootCRUD.Entity.CategoryEntity;
import com.example.SpringBootCRUD.Entity.Product;
import com.example.SpringBootCRUD.dto.ProductDto;
import com.example.SpringBootCRUD.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductDto productDto, CategoryEntity category) {
        Product product = new Product(productDto.getName(), productDto.getImageUrl(), productDto.getPrice(), productDto.getDescription(), category);
        productRepository.save(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : allProducts) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        if (product.getCategory() != null) {
            productDto.setCategoryId(product.getCategory().getId());
        } else {
            productDto.setCategoryId(null);
        }
        return productDto;
    }

    public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) {
            throw new Exception("Product not present");
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}


