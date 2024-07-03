package com.resendegabriel.investmentscontrolapi.entity.dto;

import com.resendegabriel.investmentscontrolapi.entity.Stock;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record StockResponse(String code,

                            String name,

                            BigDecimal closePrice,

                            String sector,

                            String type) {

    public static StockResponse fromEntity(Stock entity) {
        return StockResponse.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .closePrice(entity.getClosePrice())
                .sector(entity.getSector())
                .type(entity.getType())
                .build();
    }
}
