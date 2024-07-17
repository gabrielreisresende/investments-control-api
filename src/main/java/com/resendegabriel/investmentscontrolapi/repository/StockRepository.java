package com.resendegabriel.investmentscontrolapi.repository;

import com.resendegabriel.investmentscontrolapi.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, String> {

    @Query("SELECT s FROM Stock s WHERE " +
            "(:type IS NULL OR s.type = :type) AND " +
            "(:sector IS NULL OR s.sector = :sector)")
    Page<Stock> findAllWithFilters(String type, String sector, Pageable pageable);

    Optional<Stock> findByCode(String code);
}
