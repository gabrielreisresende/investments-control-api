package com.resendegabriel.investmentscontrolapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.resendegabriel.investmentscontrolapi.model.Dividend;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DividendResponse(Long id,

                               LocalDate date,

                               BigDecimal value,

                               BigDecimal dividendYield,

                               VariableAssetResponse asset) {

    public static DividendResponse fromEntity(Dividend dividendEntity) {
        return DividendResponse.builder()
                .id(dividendEntity.getId())
                .date(dividendEntity.getDate())
                .value(dividendEntity.getValue())
                .dividendYield(dividendEntity.getDividendYield())
                .asset(VariableAssetResponse.fromEntity(dividendEntity.getVariableAsset()))
                .build();
    }

    public static List<DividendResponse> fromEntityList(List<Dividend> dividends) {
        return dividends.stream()
                .map(DividendResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
