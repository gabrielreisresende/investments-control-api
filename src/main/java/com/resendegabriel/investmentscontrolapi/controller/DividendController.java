package com.resendegabriel.investmentscontrolapi.controller;

import com.resendegabriel.investmentscontrolapi.model.dto.DividendRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.DividendResponse;
import com.resendegabriel.investmentscontrolapi.model.dto.DividendUpdate;
import com.resendegabriel.investmentscontrolapi.service.DividendService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dividends")
public class DividendController {

    @Autowired
    private DividendService dividendService;

    @PostMapping
    public ResponseEntity<DividendResponse> save(@RequestBody @Valid DividendRequest dividendRequest) {
        log.info("[POST - DIVIDEND] - start");
        var response = dividendService.save(dividendRequest);
        log.info("[POST - DIVIDEND] - end");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{dividendId}")
    public ResponseEntity<DividendResponse> update(@PathVariable Long dividendId, @RequestBody @Valid DividendUpdate dividendUpdate) {
        log.info("[PUT - DIVIDEND] - start");
        var response = dividendService.update(dividendId, dividendUpdate);
        log.info("[PUT - DIVIDEND] - end");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{dividendId}")
    public ResponseEntity<DividendResponse> getById(@PathVariable Long dividendId) {
        log.info("[GET - DIVIDEND] - start");
        var response = dividendService.getById(dividendId);
        log.info("[GET - DIVIDEND] - end");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/asset")
    public ResponseEntity<List<DividendResponse>> getByAssetCode(@RequestParam String code) {
        log.info("[GET - DIVIDEND BY CODE] - start");
        var response = dividendService.getByAssetCode(code);
        log.info("[GET - DIVIDEND BY CODE] - end");
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{dividendId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long dividendId) {
        log.info("[DELETE - DIVIDEND] - start");
        dividendService.deleteById(dividendId);
        log.info("[DELETE - DIVIDEND] - end");
        return ResponseEntity.noContent().build();
    }
}
