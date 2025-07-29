package com.ra.ss15.controller;

import com.ra.ss15.model.dto.request.ComboRequest;
import com.ra.ss15.model.dto.response.APIResponse;
import com.ra.ss15.model.entity.Combo;
import com.ra.ss15.service.ComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
public class ComboController {
    @Autowired
    private ComboService comboService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Combo>>> getAllCombos() {
        List<Combo> combos = comboService.findAll();
        return new ResponseEntity<>(new APIResponse<>(true, "Lấy danh sách thành công!", combos, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Combo>> createCombo(@RequestBody ComboRequest request){
        return new ResponseEntity<>(new APIResponse<>(true, "Thêm mới thành công!", comboService.save(request), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Combo>> updateCombo(@PathVariable Long id, @RequestBody ComboRequest request){
        return new ResponseEntity<>(new APIResponse<>(true, "Cập nhật thành công!", comboService.update(request,id), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Combo>> deleteCombo(@PathVariable Long id){
        comboService.delete(id);
        return new ResponseEntity<>(new APIResponse<>(true, "Xóa thành công!", null, HttpStatus.OK), HttpStatus.OK);
    }
}
