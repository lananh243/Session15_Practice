package com.ra.ss15.controller;

import com.ra.ss15.model.dto.request.PlayAreaRequest;
import com.ra.ss15.model.dto.response.APIResponse;
import com.ra.ss15.model.entity.PlayArea;
import com.ra.ss15.service.PlayAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/play-areas")
public class PlayAreaController {
    @Autowired
    private PlayAreaService playAreaService;

    @GetMapping
    public ResponseEntity<APIResponse<List<PlayArea>>> getAll(){
        List<PlayArea> playAreas = playAreaService.findAll();
        return new ResponseEntity<>(new APIResponse<>(true, "Lấy danh sách thành công!", playAreas, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse<PlayArea>> save(@RequestBody PlayAreaRequest request){
        return new ResponseEntity<>(new APIResponse<>(true, "Thêm thành công!", playAreaService.save(request), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<PlayArea>> update(@PathVariable Long id, @RequestBody PlayAreaRequest request){
        PlayArea playArea = playAreaService.update(request,id);
        return new ResponseEntity<>(new APIResponse<>(true, "Cập nhật thành công!", playArea, HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<?>> delete(@PathVariable Long id){
        playAreaService.delete(id);
        return new ResponseEntity<>(new APIResponse<>(true, "Xóa thành công!", null, HttpStatus.OK), HttpStatus.OK);
    }
}
