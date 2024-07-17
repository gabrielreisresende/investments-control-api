package com.resendegabriel.investmentscontrolapi.model.dto.wallet;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletRequest(@NotBlank String name,

                            @NotNull Long userId) {
}
