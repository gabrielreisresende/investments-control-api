package com.resendegabriel.investmentscontrolapi.controller;

import com.resendegabriel.investmentscontrolapi.controller.docs.fixedAsset.AddFixedAssetToWalletDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.fixedAsset.DeleteFixedAssetDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.fixedAsset.GetFixedAssetDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.fixedAsset.UpdateFixedAssetDoc;
import com.resendegabriel.investmentscontrolapi.model.dto.FixedAssetRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.FixedAssetResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.FixedAssetUpdate;
import com.resendegabriel.investmentscontrolapi.service.FixedAssetService;
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
@RequestMapping("/assets/fixed")
public class FixedAssetController {

    @Autowired
    private FixedAssetService fixedAssetService;

    @AddFixedAssetToWalletDoc
    @PostMapping
    public ResponseEntity<FixedAssetResponse> addFixedAssetToWallet(@RequestBody @Valid FixedAssetRequest fixedAssetRequest) {
        log.info("[POST - FIXED ASSET] - start");
        var response = fixedAssetService.addFixedAssetToWallet(fixedAssetRequest);
        log.info("[POST - FIXED ASSET] - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @UpdateFixedAssetDoc
    @PutMapping("/{assetId}")
    public ResponseEntity<FixedAssetResponse> updateFixedAsset(@PathVariable Long assetId, @RequestBody FixedAssetUpdate fixedAssetUpdate) {
        log.info("[PUT - FIXED ASSET] - start");
        var response = fixedAssetService.updateFixedAsset(assetId, fixedAssetUpdate);
        log.info("[PUT - FIXED ASSET] - end");
        return ResponseEntity.ok().body(response);
    }

    @GetFixedAssetDoc
    @GetMapping("/{assetId}")
    public ResponseEntity<FixedAssetResponse> getById(@PathVariable Long assetId) {
        log.info("[GET - FIXED ASSET] - start");
        var response = fixedAssetService.getById(assetId);
        log.info("[GET - FIXED ASSET] - end");
        return ResponseEntity.ok().body(response);
    }

    @DeleteFixedAssetDoc
    @DeleteMapping("/{assetId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long assetId) {
        log.info("[DELETE - FIXED ASSET] - start");
        fixedAssetService.deleteById(assetId);
        log.info("[DELETE - FIXED ASSET] - end");
        return ResponseEntity.noContent().build();
    }
}
