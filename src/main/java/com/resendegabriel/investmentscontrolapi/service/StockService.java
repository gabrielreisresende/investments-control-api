package com.resendegabriel.investmentscontrolapi.service;

import com.resendegabriel.investmentscontrolapi.entity.Stock;
import com.resendegabriel.investmentscontrolapi.entity.dto.StockResponse;
import com.resendegabriel.investmentscontrolapi.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Page<StockResponse> findAllWithFilters(String type, String sector, Pageable pageable) {
        var stocksData = stockRepository.findAllWithFilters(type, sector, pageable);

        if (stocksData.isEmpty())
            throw new NoSuchElementException("Nenhum ativo encontrado.");

        return stocksData.map(StockResponse::fromEntity);
    }

    public Stock findByCode(String code) {
        return stockRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("Nenhum ativo com esse codigo: " + code));
    }
}
