package com.resendegabriel.investmentscontrolapi.batch.dto;

import com.resendegabriel.investmentscontrolapi.model.Stock;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiResponse {

    private List<Stock> stocks;
}
