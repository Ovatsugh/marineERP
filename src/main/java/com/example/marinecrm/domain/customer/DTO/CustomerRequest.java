package com.example.marinecrm.domain.customer.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomerRequest(
        @NotBlank(message = "Nome é obrigatório") String name,
        @NotBlank(message = "CPF é obrigatório") String cpf,
        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(regexp = "^\\d{10,11}$", message = "Telefone inválido") String phone
) {}
