package com.resendegabriel.investmentscontrolapi.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record VariableAssetRequest(@NotNull @Positive Integer quantity,

                                   BigDecimal averageValue,

                                   @NotBlank String code,

                                   @NotNull Long walletId
) {
}
