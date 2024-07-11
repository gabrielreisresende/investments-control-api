package com.resendegabriel.investmentscontrolapi.model.dto.wallet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.resendegabriel.investmentscontrolapi.model.Wallet;
import com.resendegabriel.investmentscontrolapi.model.dto.fixedAsset.FixedAssetResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.variableAsset.VariableAssetResponse;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record WalletResponse(Long id,

                             String name,

                             BigDecimal totalValue,

                             BigDecimal variableAssetsTotalValue,

                             BigDecimal fixedAssetsTotalValue,

                             @JsonIgnoreProperties(value = {"walletId"})
                             List<VariableAssetResponse> variableAssets,

                             @JsonIgnoreProperties(value = {"walletId"})
                             List<FixedAssetResponse> fixedAssets) {

    public static WalletResponse fromEntity(Wallet entity) {
        return WalletResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .totalValue(entity.getTotalValue())
                .variableAssetsTotalValue(entity.getVariableAssetsValue())
                .fixedAssetsTotalValue(entity.getFixedAssetsValue())
                .variableAssets(VariableAssetResponse.fromEntityList(
                        entity.getVariableAssets() != null ? entity.getVariableAssets() : Collections.emptyList()))
                .fixedAssets(FixedAssetResponse.fromEntityList(
                        entity.getFixedAssets() != null ? entity.getFixedAssets() : Collections.emptyList()))
                .build();
    }

    public static List<WalletResponse> fromEntityList(List<Wallet> wallets) {
        return wallets.stream()
                .map(WalletResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
