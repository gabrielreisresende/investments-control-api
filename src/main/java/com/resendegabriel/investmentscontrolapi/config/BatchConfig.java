package com.resendegabriel.investmentscontrolapi.config;

import com.resendegabriel.investmentscontrolapi.batch.RestStockReader;
import com.resendegabriel.investmentscontrolapi.batch.StockWriter;
import com.resendegabriel.investmentscontrolapi.model.Stock;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BatchConfig {

    @Value("${stocks.api.url}")
    private String STOCKS_API_URL;

    @Bean
    public Job stockReaderJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("stockReadJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(chunkStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Stock> restStockReader() {
        return new RestStockReader(STOCKS_API_URL, new RestTemplate());
    }

    @StepScope
    @Bean
    public ItemWriter<Stock> writer() {
        return new StockWriter();
    }

    @Bean
    public Step chunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("stockReaderStep", jobRepository).<Stock, Stock>
                        chunk(10000, transactionManager)
                .reader(restStockReader())
                .writer(writer())
                .build();

    }
}
