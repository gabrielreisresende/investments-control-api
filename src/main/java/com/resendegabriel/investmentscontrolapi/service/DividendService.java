package com.resendegabriel.investmentscontrolapi.service;

import com.resendegabriel.investmentscontrolapi.model.Dividend;
import com.resendegabriel.investmentscontrolapi.model.dto.DividendRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.DividendResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.DividendUpdate;
import com.resendegabriel.investmentscontrolapi.repository.DividendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DividendService {

    @Autowired
    private DividendRepository dividendRepository;

    @Autowired
    private VariableAssetService variableAssetService;

    public DividendResponse save(DividendRequest dividendRequest) {
        var variableAsset = variableAssetService.findById(dividendRequest.assetId());
        var dividendEntity = Dividend.fromRequest(dividendRequest, variableAsset);
        dividendRepository.save(dividendEntity);
        return DividendResponse.fromEntity(dividendEntity);
    }

    public DividendResponse update(Long id, DividendUpdate dividendUpdate) {
        var dividendEntity = findById(id);
        dividendEntity.updateData(dividendUpdate);
        dividendRepository.save(dividendEntity);
        return DividendResponse.fromEntity(dividendEntity);
    }

    public DividendResponse getById(Long dividendId) {
        var dividendEntity = findById(dividendId);
        return DividendResponse.fromEntity(dividendEntity);
    }

    public List<DividendResponse> getByAssetCode(String code) {
        var dividends = dividendRepository.findAllByVariableAssetStockCode(code);

        if (dividends.isEmpty())
            throw new NoSuchElementException("Nenhum dividendo encontrado para esse ativo de codigo: " + code);

        return DividendResponse.fromEntityList(dividends);
    }

    public void deleteById(Long dividendId) {
        findById(dividendId);
        dividendRepository.deleteById(dividendId);
    }

    protected Dividend findById(Long id) {
        return dividendRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhum dividendo encontrado com esse id. Id " + id));
    }
}
