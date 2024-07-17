package com.resendegabriel.investmentscontrolapi.model.dto.fixedAsset;

import com.resendegabriel.investmentscontrolapi.model.FixedAsset;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record FixedAssetResponse(Long id,

                                 String fixedAssetType,

                                 BigDecimal initialValue,

                                 BigDecimal currentValue,

                                 BigDecimal rentability,

                                 LocalDate valid,

                                 Long walletId) {

    public static FixedAssetResponse fromEntity(FixedAsset entity) {
        return FixedAssetResponse.builder()
                .id(entity.getId())
                .fixedAssetType(entity.getFixedAssetType().toString())
                .initialValue(entity.getInitialValue())
                .currentValue(entity.getCurrentValue())
                .rentability(entity.getRentability())
                .valid(entity.getValid())
                .walletId(entity.getWallet().getId())
                .build();
    }

    public static List<FixedAssetResponse> fromEntityList(List<FixedAsset> entityList) {
        return entityList.stream()
                .map(FixedAssetResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
