package com.resendegabriel.investmentscontrolapi.model.dto.divedend;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DividendRequest(@NotNull LocalDate date,

                              @NotNull BigDecimal value,

                              @NotNull Long assetId) {
}
