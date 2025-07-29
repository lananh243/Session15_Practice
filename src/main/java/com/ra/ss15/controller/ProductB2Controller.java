package com.ra.ss15.controller;

import com.ra.ss15.model.dto.request.ProductB2Request;
import com.ra.ss15.model.dto.response.APIResponse;
import com.ra.ss15.model.entity.ProductB2;
import com.ra.ss15.service.ProductB2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductB2Controller {
    @Autowired
    private ProductB2Service productB2Service;

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductB2>>> getProducts() {
        List<ProductB2> products = productB2Service.getProducts();
        return new ResponseEntity<>(new APIResponse<>(true, "Lấy danh sách thanh công!", products, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<ProductB2>> save(@RequestBody ProductB2Request request){
        return new ResponseEntity<>(new APIResponse<>(true, "Thêm thành công!", productB2Service.save(request), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProductB2>> update(@PathVariable Long id, @RequestBody ProductB2Request request){
        return new ResponseEntity<>(new APIResponse<>(true, "Cập nhật thành công!", productB2Service.update(request,id), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<ProductB2>> delete(@PathVariable Long id){
        productB2Service.delete(id);
        return new ResponseEntity<>(new APIResponse<>(true, "Xóa thành công!", null, HttpStatus.OK), HttpStatus.OK);
    }
}
