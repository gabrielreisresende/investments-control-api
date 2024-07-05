package com.resendegabriel.investmentscontrolapi.model.dto;

import com.resendegabriel.investmentscontrolapi.model.VariableAsset;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record VariableAssetResponse(Integer quantity,

                                    BigDecimal averageValue,

                                    BigDecimal totalValue,

                                    String assetCode,

                                    Long walletId
) {

    public static VariableAssetResponse fromEntity(VariableAsset entity) {
        return VariableAssetResponse.builder()
                .quantity(entity.getQuantity())
                .averageValue(entity.getAverageValue())
                .totalValue(entity.getTotalValue())
                .assetCode(entity.getStock().getCode())
                .walletId(entity.getWallet().getId())
                .build();
    }

    public static List<VariableAssetResponse> fromEntityList(List<VariableAsset> entityList) {
        return entityList.stream()
                .map(VariableAssetResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
