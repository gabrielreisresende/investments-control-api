package com.resendegabriel.investmentscontrolapi.service;

import com.resendegabriel.investmentscontrolapi.model.FixedAsset;
import com.resendegabriel.investmentscontrolapi.model.dto.FixedAssetRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.FixedAssetResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.FixedAssetUpdate;
import com.resendegabriel.investmentscontrolapi.repository.FixedAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FixedAssetService {

    @Autowired
    private FixedAssetRepository fixedAssetRepository;

    @Autowired
    private WalletService walletService;

    public FixedAssetResponse addFixedAssetToWallet(FixedAssetRequest assetRequest) {
        var wallet = walletService.findById(assetRequest.walletId());
        var fixedAssetEntity = FixedAsset.fromRequest(assetRequest, wallet);
        fixedAssetRepository.save(fixedAssetEntity);
        return FixedAssetResponse.fromEntity(fixedAssetEntity);
    }

    public FixedAssetResponse updateFixedAsset(Long assetId, FixedAssetUpdate fixedAssetUpdate) {
        var asset = findById(assetId);
        asset.updateData(fixedAssetUpdate);
        fixedAssetRepository.save(asset);
        return FixedAssetResponse.fromEntity(asset);
    }

    public FixedAssetResponse getById(Long assetId) {
        var asset = findById(assetId);
        return FixedAssetResponse.fromEntity(asset);
    }

    public void deleteById(Long assetId) {
        findById(assetId);
        fixedAssetRepository.deleteById(assetId);
    }

    protected FixedAsset findById(Long id) {
        return fixedAssetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ativo de renda fixa não encontrado. Id " + id));
    }
}
