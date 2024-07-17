package com.resendegabriel.investmentscontrolapi.service;

import com.resendegabriel.investmentscontrolapi.model.Wallet;
import com.resendegabriel.investmentscontrolapi.model.dto.wallet.WalletRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.wallet.WalletResponse;
import com.resendegabriel.investmentscontrolapi.repository.WalletRepository;
import com.resendegabriel.investmentscontrolapi.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;

    public WalletResponse create(WalletRequest walletRequest) {
        var user = userService.findById(walletRequest.userId());
        var wallet = Wallet.fromRequest(walletRequest, user);
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

    public List<WalletResponse> getByUserId(Long userId) {
        var wallets = walletRepository.findAllByUserId(userId);

        if (wallets.isEmpty()) {
            throw new NoSuchElementException("Nenhuma carteira de investimentos para esse usuario de id: " + userId);
        }

        return WalletResponse.fromEntityList(wallets);
    }

    protected Wallet findById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Nenhuma carteira  de investimentos com esse id. Id " + id));
    }
}
