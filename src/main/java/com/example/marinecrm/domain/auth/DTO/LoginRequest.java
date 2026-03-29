package com.example.marinecrm.domain.auth.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record LoginRequest(
        @NotBlank(message = "Email é obrigatório") String email,
        @NotBlank(message = "Senha é obrigatória") String password

) {
}
