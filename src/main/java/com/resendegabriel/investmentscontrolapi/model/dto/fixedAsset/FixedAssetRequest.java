package com.resendegabriel.investmentscontrolapi.model.dto.fixedAsset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FixedAssetRequest(@NotBlank String fixedAssetType,

                                @NotNull BigDecimal initialValue,

                                @NotNull BigDecimal currentValue,

                                @NotNull LocalDate valid,

                                @NotNull Long walletId) {
}
