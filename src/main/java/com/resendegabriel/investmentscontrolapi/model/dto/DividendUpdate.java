package com.resendegabriel.investmentscontrolapi.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DividendUpdate(LocalDate date,

                             BigDecimal value) {
}
