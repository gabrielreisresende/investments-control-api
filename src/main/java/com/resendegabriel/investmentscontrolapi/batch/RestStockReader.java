package com.resendegabriel.investmentscontrolapi.batch;

import com.resendegabriel.investmentscontrolapi.batch.dto.ApiResponse;
import com.resendegabriel.investmentscontrolapi.entity.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class RestStockReader implements ItemReader<Stock> {

    private final String url;

    private final RestTemplate restTemplate;

    private int nextStock;

    private List<Stock> stocks;

    @Override
    public Stock read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (stocks == null) {
            stocks = fetchStocksApi();
        }
        Stock stock = null;

        if (nextStock < stocks.size()) {
            stock = stocks.get(nextStock);
            nextStock++;
        } else {
            nextStock = 0;
            stocks = null;
        }
        return stock;
    }

    private List<Stock> fetchStocksApi() {
        log.info("Reading Stocks Data from API");
        ResponseEntity<ApiResponse> response = restTemplate.getForEntity(this.url, ApiResponse.class);
        return Objects.requireNonNull(response.getBody()).getStocks();
    }
}
