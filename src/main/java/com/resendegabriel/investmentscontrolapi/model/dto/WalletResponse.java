package com.resendegabriel.investmentscontrolapi.model.dto;

import com.resendegabriel.investmentscontrolapi.model.FixedAsset;
import com.resendegabriel.investmentscontrolapi.model.VariableAsset;
import com.resendegabriel.investmentscontrolapi.model.Wallet;
import lombok.Builder;

import java.util.List;

@Builder
public record WalletResponse(Long id,

                             String name,

                             List<VariableAsset> variableAssets,

                             List<FixedAsset> fixedAssets) {

    public static WalletResponse fromEntity(Wallet entity) {
        return WalletResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .variableAssets(entity.getVariableAssets())
                .fixedAssets(entity.getFixedAssets())
                .build();
    }
}
