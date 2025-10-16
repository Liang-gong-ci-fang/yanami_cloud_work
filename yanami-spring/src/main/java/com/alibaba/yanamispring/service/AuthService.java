package com.alibaba.yanamispring.service;

import com.alibaba.yanamispring.dto.LoginRequest;
import com.alibaba.yanamispring.dto.LoginResponse;
import com.alibaba.yanamispring.dto.UserInfo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    // 模拟用户数据（实际项目中应该使用数据库）
    private static final String DEMO_USERNAME = "admin";
    private static final String DEMO_PASSWORD = "123456";

    public LoginResponse login(LoginRequest request) {
        // 验证用户名和密码
        if (DEMO_USERNAME.equals(request.getUsername()) &&
            DEMO_PASSWORD.equals(request.getPassword())) {

            // 生成简单的 token（实际项目中应使用 JWT）
            String token = UUID.randomUUID().toString();

            // 创建用户信息
            UserInfo userInfo = new UserInfo(request.getUsername(), "admin");

            return new LoginResponse(true, "登录成功", token, userInfo);
        } else {
            return new LoginResponse(false, "用户名或密码错误", null, null);
        }
    }
}
