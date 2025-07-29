package com.ra.ss15.service;

import com.ra.ss15.model.dto.request.UserLoginRequest;
import com.ra.ss15.model.dto.request.UserRegisterRequest;
import com.ra.ss15.model.dto.response.JWTResponse;
import com.ra.ss15.model.entity.Role;
import com.ra.ss15.model.entity.RoleName;
import com.ra.ss15.model.entity.User;

import java.util.List;

public interface UserService {
    User register(UserRegisterRequest userRegisterRequest);
    JWTResponse login(UserLoginRequest userLoginRequest);
    List<User> findAll();
    User searchByUsername(String userName);
    User toggleUser(Long id);
    User updateRole(Long id, RoleName newRole);
    User getCurrentUser();
}
