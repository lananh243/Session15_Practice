package com.ra.ss15.service;

import com.ra.ss15.model.dto.request.ProductB2Request;
import com.ra.ss15.model.entity.ProductB2;

import java.util.List;

public interface ProductB2Service {
    List<ProductB2> getProducts();
    ProductB2 getProductById(Long id);
    ProductB2 save(ProductB2Request request);
    ProductB2 update(ProductB2Request request, Long id);
    void delete(Long id);
}
