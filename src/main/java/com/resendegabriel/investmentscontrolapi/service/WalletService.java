package com.resendegabriel.investmentscontrolapi.service;

import com.resendegabriel.investmentscontrolapi.model.Wallet;
import com.resendegabriel.investmentscontrolapi.model.dto.wallet.WalletRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.wallet.WalletResponse;
import com.resendegabriel.investmentscontrolapi.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public WalletResponse create(WalletRequest walletRequest) {
        var wallet = Wallet.fromRequest(walletRequest);
        walletRepository.save(wallet);
        return WalletResponse.fromEntity(wallet);
    }

    @Transactional
    public WalletResponse updateData(Long id, WalletRequest walletRequest) {
        var wallet = findById(id);
        wallet.updateData(walletRequest);
        return WalletResponse.fromEntity(wallet);
    }

    public WalletResponse getById(Long id) {
        var wallet = findById(id);
        return WalletResponse.fromEntity(wallet);
    }

    public void deleteById(Long id) {
        findById(id);
        walletRepository.deleteById(id);
    }

    protected Wallet findById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhuma carteira  de investimentos com esse id. Id " + id));
    }
}
