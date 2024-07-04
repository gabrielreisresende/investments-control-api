package com.resendegabriel.investmentscontrolapi.model.dto;

import jakarta.validation.constraints.NotBlank;

public record WalletRequest(@NotBlank String name) {
}
