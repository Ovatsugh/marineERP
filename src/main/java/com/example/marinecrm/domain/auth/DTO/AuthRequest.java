package com.example.marinecrm.domain.auth.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AuthRequest(
        @NotBlank(message = "Nome é obrigatório") String name,
        @NotBlank(message = "Email é obrigatório") String email,
        @NotBlank(message = "Senha é obrigatória") String password,
        @NotNull(message = "Empresa é obrigatória") UUID companyId
) { }
