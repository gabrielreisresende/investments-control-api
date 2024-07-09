package com.resendegabriel.investmentscontrolapi.model;

import com.resendegabriel.investmentscontrolapi.model.dto.divedend.DividendRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.divedend.DividendUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "TB_DIVIDENDS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dividend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private BigDecimal value;

    private BigDecimal dividendYield;

    @ManyToOne
    private VariableAsset variableAsset;

    @PrePersist
    @PreUpdate
    private void postLoad() {
        dividendYield = value
                .divide(variableAsset.getAverageValue(), RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }

    public static Dividend fromRequest(DividendRequest dividendRequest, VariableAsset variableAsset) {
        return Dividend.builder()
                .date(dividendRequest.date())
                .value(dividendRequest.value())
                .variableAsset(variableAsset)
                .build();
    }

    public void updateData(DividendUpdate dividendUpdate) {
        date = dividendUpdate.date() != null ? dividendUpdate.date() : date;
        value = dividendUpdate.value() != null ? dividendUpdate.value() : value;
    }
}
