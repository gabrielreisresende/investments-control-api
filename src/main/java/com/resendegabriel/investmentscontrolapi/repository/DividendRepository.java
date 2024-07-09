package com.resendegabriel.investmentscontrolapi.repository;

import com.resendegabriel.investmentscontrolapi.model.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DividendRepository extends JpaRepository<Dividend, Long> {

    List<Dividend> findAllByVariableAssetStockCode(String code);
}
