package com.ra.ss15.service.imp;

import com.ra.ss15.model.dto.request.UserLoginRequest;
import com.ra.ss15.model.dto.request.UserRegisterRequest;
import com.ra.ss15.model.dto.response.JWTResponse;
import com.ra.ss15.model.entity.Role;
import com.ra.ss15.model.entity.User;
import com.ra.ss15.repository.RoleRepository;
import com.ra.ss15.repository.UserRepository;
import com.ra.ss15.security.jwt.JWTProvider;
import com.ra.ss15.security.principal.CustomerUserDetails;
import com.ra.ss15.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JWTProvider jwtProvider;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User register(UserRegisterRequest userRegisterRequest) {
        User user = User.builder()
                .userName(userRegisterRequest.getUsername())
                .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
                .fullName(userRegisterRequest.getFullName())
                .email(userRegisterRequest.getEmail())
                .phone(userRegisterRequest.getPhone())
                .enabled(true)
                .roles(mapRoleStringToRole(userRegisterRequest.getRoles()))
                .build();
        return userRepository.save(user);
    }

    @Override
    public JWTResponse login(UserLoginRequest userLoginRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));
        }catch (AuthenticationException e){
            log.error("Sai username hoac password!");
        }
        CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();
        String token = jwtProvider.generateToken(userDetails.getUsername());

        return JWTResponse.builder()
                .username(userDetails.getUsername())
                .fullName(userDetails.getFullName())
                .enabled(userDetails.isEnabled())
                .phone(userDetails.getPhone())
                .authorities(userDetails.getAuthorities())
                .token(token)
                .build();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User searchByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Username not found!"));
    }

    @Override
    public User toggleUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found!"));
        user.setEnabled(!user.getEnabled());
        return userRepository.save(user);
    }

    private List<Role> mapRoleStringToRole(List<String> roles) {
        List<Role> roleList = new ArrayList<>();

        if(roles!=null && !roles.isEmpty()){
            roles.forEach(role->{
                switch (role){
                    case "ROLE_ADMIN":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_admin")));
                        break;
                    case "ROLE_USER":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
                        break;
                    case "ROLE_MODERATOR":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_moderator")));
                        break;
                    default:
                        roleList.add(roleRepository.findByRoleName("ROLE_USER").orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
                }
            });
        }else{
            roleList.add(roleRepository.findByRoleName("ROLE_USER").orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
        }
        return roleList;
    }
}
