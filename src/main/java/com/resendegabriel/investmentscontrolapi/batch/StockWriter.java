package com.resendegabriel.investmentscontrolapi.batch;

import com.resendegabriel.investmentscontrolapi.model.Stock;
import com.resendegabriel.investmentscontrolapi.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StockWriter implements ItemWriter<Stock> {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public void write(Chunk<? extends Stock> chunk) throws Exception {
        log.info("Deleting old stocks data");
        stockRepository.deleteAll();
        log.info("Writing {} stocks data", chunk.getItems().size());
        stockRepository.saveAll(chunk.getItems());
    }
}
