package com.ra.ss15.controller;

import com.ra.ss15.model.dto.request.ProductRequest;
import com.ra.ss15.model.dto.response.APIResponse;
import com.ra.ss15.model.entity.Product;
import com.ra.ss15.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Product>>> getAll(){
        List<Product> products = productService.getProducts();
        return new ResponseEntity<>(new APIResponse<>(true, "Lấy danh sách sản phẩm thành công!", products, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Product>> save(@RequestBody ProductRequest request){
        return new ResponseEntity<>(new APIResponse<>(true, "Thêm sản phẩm thành công!", productService.save(request), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Product>> update(@PathVariable Long id, @RequestBody ProductRequest request){
        Product updateProduct = productService.update(request, id);
        return new ResponseEntity<>(new APIResponse<>(true, "Cập nhật sản phẩm thành công!", updateProduct, HttpStatus.OK),HttpStatus.OK);
    }
}
