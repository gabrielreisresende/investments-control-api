package com.resendegabriel.investmentscontrolapi.service;

import com.resendegabriel.investmentscontrolapi.model.VariableAsset;
import com.resendegabriel.investmentscontrolapi.model.dto.VariableAssetRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.VariableAssetResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.VariableAssetUpdate;
import com.resendegabriel.investmentscontrolapi.repository.VariableAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class VariableAssetService {

    @Autowired
    private VariableAssetRepository variableAssetRepository;

    @Autowired
    private StockService stockService;

    @Autowired
    private WalletService walletService;

    public VariableAssetResponse addVariableAssetToWallet(VariableAssetRequest assetRequest) {
        var wallet = walletService.findById(assetRequest.walletId());
        var stock = stockService.findByCode(assetRequest.code());
        var variableAssetEntity = VariableAsset.fromRequest(assetRequest, stock, wallet);
        return VariableAssetResponse.fromEntity(variableAssetRepository.save(variableAssetEntity));
    }

    public VariableAssetResponse updateVariableActive(Long assetId, VariableAssetUpdate assetRequest) {
        var asset = findById(assetId);
        asset.updateData(assetRequest);
        variableAssetRepository.save(asset);
        return VariableAssetResponse.fromEntity(asset);
    }

    public VariableAssetResponse getById(Long assetId) {
        var asset = findById(assetId);
        return VariableAssetResponse.fromEntity(asset);
    }

    public void deleteById(Long assetId) {
        findById(assetId);
        variableAssetRepository.deleteById(assetId);
    }

    protected VariableAsset findById(Long id) {
        return variableAssetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Ativo de renda variável não encontrado. Id " + id));
    }
}
