package com.resendegabriel.investmentscontrolapi.controller;

import com.resendegabriel.investmentscontrolapi.controller.docs.variableAsset.AddVariableAssetToWalletDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.variableAsset.DeleteVariableAssetDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.variableAsset.GetVariableAssetDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.variableAsset.UpdateVariableAssetDoc;
import com.resendegabriel.investmentscontrolapi.model.dto.variableAsset.VariableAssetRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.variableAsset.VariableAssetResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.variableAsset.VariableAssetUpdate;
import com.resendegabriel.investmentscontrolapi.service.VariableAssetService;
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
@RequestMapping("/assets/variable")
public class VariableAssetController {

    @Autowired
    private VariableAssetService variableAssetService;

    @AddVariableAssetToWalletDoc
    @PostMapping
    public ResponseEntity<VariableAssetResponse> addVariableAssetToWallet(@RequestBody @Valid VariableAssetRequest assetRequest) {
        log.info("[POST - VARIABLE ASSET] - start");
        var response = variableAssetService.addVariableAssetToWallet(assetRequest);
        log.info("[POST - VARIABLE ASSET] - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @UpdateVariableAssetDoc
    @PutMapping("/{assetId}")
    public ResponseEntity<VariableAssetResponse> updateVariableAsset(@PathVariable Long assetId, @RequestBody VariableAssetUpdate assetUpdate) {
        log.info("[PUT - VARIABLE ASSET] - start");
        var response = variableAssetService.updateVariableActive(assetId, assetUpdate);
        log.info("[PUT - VARIABLE ASSET] - end");
        return ResponseEntity.ok().body(response);
    }

    @GetVariableAssetDoc
    @GetMapping("/{assetId}")
    public ResponseEntity<VariableAssetResponse> getById(@PathVariable Long assetId) {
        log.info("[GET - VARIABLE ASSET] - start");
        var response = variableAssetService.getById(assetId);
        log.info("[GET - VARIABLE ASSET] - end");
        return ResponseEntity.ok().body(response);
    }

    @DeleteVariableAssetDoc
    @DeleteMapping("/{assetId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long assetId) {
        log.info("[DELETE - VARIABLE ASSET] - start");
        variableAssetService.deleteById(assetId);
        log.info("[DELETE - VARIABLE ASSET] - end");
        return ResponseEntity.noContent().build();
    }
}
