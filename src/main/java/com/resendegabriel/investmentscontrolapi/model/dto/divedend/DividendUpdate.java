package com.resendegabriel.investmentscontrolapi.model.dto.divedend;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DividendUpdate(LocalDate date,

                             BigDecimal value) {
}
