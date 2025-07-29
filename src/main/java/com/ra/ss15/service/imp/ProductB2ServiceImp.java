package com.ra.ss15.service.imp;

import com.ra.ss15.model.dto.request.ProductB2Request;
import com.ra.ss15.model.entity.ProductB2;
import com.ra.ss15.repository.ProductB2Repository;
import com.ra.ss15.service.ProductB2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductB2ServiceImp implements ProductB2Service {
    @Autowired
    private ProductB2Repository productB2Repository;
    @Override
    public List<ProductB2> getProducts() {
        return productB2Repository.findAll();
    }

    @Override
    public ProductB2 getProductById(Long id) {
        return productB2Repository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
    }

    @Override
    public ProductB2 save(ProductB2Request request) {
        ProductB2 pro = ProductB2.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .size(request.getSize())
                .toppings(request.getToppings())
                .build();
        return productB2Repository.save(pro);
    }

    @Override
    public ProductB2 update(ProductB2Request request, Long id) {
        ProductB2 pro = productB2Repository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        pro.setName(request.getName());
        pro.setDescription(request.getDescription());
        pro.setPrice(request.getPrice());
        pro.setSize(request.getSize());
        pro.setToppings(request.getToppings());
        return productB2Repository.save(pro);
    }

    @Override
    public void delete(Long id) {
        ProductB2 deleted = productB2Repository.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        productB2Repository.delete(deleted);

    }
}
