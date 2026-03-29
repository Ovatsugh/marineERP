package com.example.marinecrm.domain.auth.service;

import com.example.marinecrm.Command;
import com.example.marinecrm.domain.auth.DTO.AuthResponse;
import com.example.marinecrm.domain.auth.DTO.AuthRequest;
import com.example.marinecrm.domain.auth.DTO.LoginRequest;
import com.example.marinecrm.domain.user.User;
import com.example.marinecrm.domain.user.UserRepository;
import com.example.marinecrm.exceptions.InvalidCredentialsException;
import com.example.marinecrm.infra.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements Command<LoginRequest, AuthResponse> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse execute(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return new AuthResponse(jwtService.generateToken(user));
    }
}
