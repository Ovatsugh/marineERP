package com.example.marinecrm.domain.auth.service;

import com.example.marinecrm.Command;
import com.example.marinecrm.domain.auth.DTO.AuthResponse;
import com.example.marinecrm.domain.auth.DTO.AuthRequest;
import com.example.marinecrm.domain.company.Company;
import com.example.marinecrm.domain.company.CompanyRepository;
import com.example.marinecrm.domain.user.User;
import com.example.marinecrm.domain.user.UserRepository;
import com.example.marinecrm.enums.Roles;
import com.example.marinecrm.exceptions.EmailAlreadyExistsException;
import com.example.marinecrm.exceptions.ResourceNotFoundException;
import com.example.marinecrm.infra.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterService implements Command<AuthRequest, AuthResponse> {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    @Transactional
    public AuthResponse execute(AuthRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        Company company = companyRepository.findById(request.companyId()).orElseThrow(() ->
                new ResourceNotFoundException("Empresa não encontrada: " + request.companyId()));

        User user = new User(request, passwordEncoder.encode(request.password()), company, Roles.ADMIN);
        User saved = userRepository.save(user);

        return new AuthResponse(jwtService.generateToken(saved));
    }
}
