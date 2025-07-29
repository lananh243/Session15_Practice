package com.ra.ss15.service;

import com.ra.ss15.model.dto.request.ProductRequest;
import com.ra.ss15.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product findById(Long id);
    Product save(ProductRequest request);
    Product update(ProductRequest request, Long id);
    void delete(Long id);
}
