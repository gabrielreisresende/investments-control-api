package com.resendegabriel.investmentscontrolapi.model.dto.wallet;

import jakarta.validation.constraints.NotBlank;

public record WalletRequest(@NotBlank String name) {
}
