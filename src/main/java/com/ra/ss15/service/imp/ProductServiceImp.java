package com.ra.ss15.service.imp;

import com.ra.ss15.model.dto.request.ProductRequest;
import com.ra.ss15.model.entity.Category;
import com.ra.ss15.model.entity.Product;
import com.ra.ss15.repository.CategoryRepository;
import com.ra.ss15.repository.ProductRepository;
import com.ra.ss15.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
    }

    @Override
    public Product save(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCateId()).orElseThrow(()-> new RuntimeException("Category not found"));

        Product product = Product.builder()
                .productName(request.getProductName())
                .producer(request.getProducer())
                .yearMaking(request.getYearMaking())
                .expireDate(request.getExpireDate())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .category(category)
                .build();
        return productRepository.save(product);
    }

    @Override
    public Product update(ProductRequest request, Long id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        Category category = categoryRepository.findById(request.getCateId()).orElseThrow(()-> new RuntimeException("Category not found"));

        existingProduct.setProductName(request.getProductName());
        existingProduct.setProducer(request.getProducer());
        existingProduct.setYearMaking(request.getYearMaking());
        existingProduct.setExpireDate(request.getExpireDate());
        existingProduct.setQuantity(request.getQuantity());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setCategory(category);
        return productRepository.save(existingProduct);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        productRepository.delete(product);

    }
}
