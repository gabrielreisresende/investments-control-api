package com.resendegabriel.investmentscontrolapi.controller;

import com.resendegabriel.investmentscontrolapi.controller.docs.wallet.DeleteWalletDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.wallet.GetWalletDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.wallet.WalletCreationDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.wallet.WalletUpdateDoc;
import com.resendegabriel.investmentscontrolapi.model.dto.wallet.WalletRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.wallet.WalletResponse;
import com.resendegabriel.investmentscontrolapi.service.WalletService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @WalletCreationDoc
    @PostMapping
    public ResponseEntity<WalletResponse> create(@RequestBody @Valid WalletRequest walletRequest) {
        log.info("[POST - CREATE WALLET] - start");
        var response = walletService.create(walletRequest);
        log.info("[POST - CREATE WALLET] - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @WalletUpdateDoc
    @PutMapping("/{id}")
    public ResponseEntity<WalletResponse> updateData(@PathVariable Long id, @RequestBody WalletRequest walletRequest) {
        log.info("[PÚT - UPDATE WALLET] - start");
        var response = walletService.updateData(id, walletRequest);
        log.info("[PUT - UPDATE WALLET] - end");
        return ResponseEntity.ok().body(response);
    }

    @GetWalletDoc
    @GetMapping("/{id}")
    public ResponseEntity<WalletResponse> getById(@PathVariable Long id) {
        log.info("[GET - WALLET] - start");
        var response = walletService.getById(id);
        log.info("[GET - WALLET] - end");
        return ResponseEntity.ok().body(response);
    }


    @DeleteWalletDoc
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("[DELETE - WALLET] - start");
        walletService.deleteById(id);
        log.info("[DELETE - WALLET] - end");
        return ResponseEntity.noContent().build();
    }
}
