package com.alibaba.yanamispring.controller;

import com.alibaba.yanamispring.dto.LoginRequest;
import com.alibaba.yanamispring.dto.LoginResponse;
import com.alibaba.yanamispring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
