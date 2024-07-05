package com.resendegabriel.investmentscontrolapi.model.dto;

import com.resendegabriel.investmentscontrolapi.model.Wallet;
import lombok.Builder;

import java.util.List;

@Builder
public record WalletResponse(Long id,

                             String name,

                             List<VariableAssetResponse> variableAssets,

                             List<FixedAssetResponse> fixedAssets) {

    public static WalletResponse fromEntity(Wallet entity) {
        return WalletResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .variableAssets(VariableAssetResponse.fromEntityList(entity.getVariableAssets()))
                .fixedAssets(FixedAssetResponse.fromEntityList(entity.getFixedAssets()))
                .build();
    }
}
