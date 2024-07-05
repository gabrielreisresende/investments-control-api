package com.resendegabriel.investmentscontrolapi.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record VariableAssetUpdate(@NotNull @Positive Integer quantity,

                                  BigDecimal averageValue) {
}
