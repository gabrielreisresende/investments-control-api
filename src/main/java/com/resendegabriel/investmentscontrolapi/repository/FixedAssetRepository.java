package com.resendegabriel.investmentscontrolapi.repository;

import com.resendegabriel.investmentscontrolapi.model.FixedAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FixedAssetRepository extends JpaRepository<FixedAsset, Long> {
}
