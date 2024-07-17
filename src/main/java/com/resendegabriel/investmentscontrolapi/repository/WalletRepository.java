package com.resendegabriel.investmentscontrolapi.repository;

import com.resendegabriel.investmentscontrolapi.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    List<Wallet> findAllByUserId(Long userId);
}
