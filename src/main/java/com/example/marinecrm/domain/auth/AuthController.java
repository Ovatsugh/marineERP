package com.example.marinecrm.domain.auth;

import com.example.marinecrm.domain.auth.DTO.AuthResponse;
import com.example.marinecrm.domain.auth.DTO.AuthRequest;
import com.example.marinecrm.domain.auth.DTO.LoginRequest;
import com.example.marinecrm.domain.auth.service.LoginService;
import com.example.marinecrm.domain.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;
    private final RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Validated @RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginService.execute(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Validated @RequestBody AuthRequest request) {
        return ResponseEntity.ok(registerService.execute(request));
    }
}
