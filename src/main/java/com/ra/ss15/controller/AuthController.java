package com.ra.ss15.controller;

import com.ra.ss15.model.dto.request.UserLoginRequest;
import com.ra.ss15.model.dto.request.UserRegisterRequest;
import com.ra.ss15.model.dto.response.APIResponse;
import com.ra.ss15.model.dto.response.JWTResponse;
import com.ra.ss15.model.entity.User;
import com.ra.ss15.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse<User>> register(@RequestBody UserRegisterRequest request){
        return new ResponseEntity<>(new APIResponse<>(true, "Đăng kí thành công!", userService.register(request), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<JWTResponse>> login(@RequestBody UserLoginRequest request){
        return new ResponseEntity<>(new APIResponse<>(true, "Đăng nhập thành công!", userService.login(request), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<User>>> getAllUser(){
        List<User> findAll = userService.findAll();
        return new ResponseEntity<>(new APIResponse<>(true, "Danh sách người dùng",  findAll, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<APIResponse<User>> searchByUsername(@RequestParam String username){
        User user = userService.searchByUsername(username);
        return new ResponseEntity<>(new APIResponse<>(true, "Tìm thấy người dùng theo username",  user, HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping("/{id}/toggle-enabled")
    public ResponseEntity<APIResponse<?>> toggleEnabled(@PathVariable Long id){
        User updateUser = userService.toggleUser(id);
        String status = updateUser.getEnabled() ? "Mở khóa" : "Khóa";
        return new ResponseEntity<>(new APIResponse<>(true, "Đã " + status + " người dùng thành công.", updateUser, HttpStatus.OK), HttpStatus.OK);
    }
}
