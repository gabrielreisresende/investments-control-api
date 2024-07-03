package com.resendegabriel.investmentscontrolapi.controller;

import com.resendegabriel.investmentscontrolapi.controller.docs.StockByCodeDoc;
import com.resendegabriel.investmentscontrolapi.controller.docs.StocksListDoc;
import com.resendegabriel.investmentscontrolapi.entity.Stock;
import com.resendegabriel.investmentscontrolapi.entity.dto.StockResponse;
import com.resendegabriel.investmentscontrolapi.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @StocksListDoc
    @GetMapping("/list")
    public ResponseEntity<Page<StockResponse>> findAllByStockType(@RequestParam(required = false) String type,
                                                                  @RequestParam(required = false) String sector,
                                                                  @PageableDefault(size = 10, sort = "code") Pageable pageable) {
        log.info("[GET - STOCKS LIST] - start");
        var stocksResponse = stockService.findAllWithFilters(type, sector, pageable);
        log.info("[GET - STOCKS LIST] - end");
        return ResponseEntity.ok().body(stocksResponse);
    }

    @StockByCodeDoc
    @GetMapping("/code/{code}")
    public ResponseEntity<Stock> findByCode(@PathVariable String code) {
        log.info("[GET - STOCK BY CODE] - start");
        var stockResponse = stockService.findByCode(code);
        log.info("[GET - STOCKS BY CODE] - end");
        return ResponseEntity.ok().body(stockResponse);
    }
}
