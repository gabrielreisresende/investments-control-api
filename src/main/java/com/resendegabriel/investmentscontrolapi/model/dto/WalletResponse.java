package com.resendegabriel.investmentscontrolapi.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.resendegabriel.investmentscontrolapi.model.Wallet;
import lombok.Builder;

import java.util.Collections;
import java.util.List;

@Builder
public record WalletResponse(Long id,

                             String name,

                             @JsonIgnoreProperties(value = {"walletId"})
                             List<VariableAssetResponse> variableAssets,

                             @JsonIgnoreProperties(value = {"walletId"})
                             List<FixedAssetResponse> fixedAssets) {

    public static WalletResponse fromEntity(Wallet entity) {
        return WalletResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .variableAssets(VariableAssetResponse.fromEntityList(
                        entity.getVariableAssets() != null ? entity.getVariableAssets() : Collections.emptyList()))
                .fixedAssets(FixedAssetResponse.fromEntityList(
                        entity.getFixedAssets() != null ? entity.getFixedAssets() : Collections.emptyList()))
                .build();
    }
}
