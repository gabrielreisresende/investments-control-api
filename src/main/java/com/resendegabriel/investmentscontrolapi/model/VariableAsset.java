package com.resendegabriel.investmentscontrolapi.model;

import com.resendegabriel.investmentscontrolapi.model.dto.variableAsset.VariableAssetRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.variableAsset.VariableAssetUpdate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "TB_VARIABLE_ASSETS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariableAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private BigDecimal averageValue;

    @Transient
    private BigDecimal totalValue;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Stock stock;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Wallet wallet;

    @OneToMany(mappedBy = "variableAsset", cascade = CascadeType.REMOVE)
    private List<Dividend> dividends;

    @PostPersist
    @PostLoad
    @PostUpdate
    public void calculateTotalValue() {
        totalValue = stock.getClosePrice()
                .multiply(BigDecimal.valueOf(quantity));
    }

    public static VariableAsset fromRequest(VariableAssetRequest request, Stock stock, Wallet wallet) {
        return VariableAsset.builder()
                .quantity(request.quantity())
                .averageValue(request.averageValue())
                .stock(stock)
                .wallet(wallet)
                .build();
    }

    public void updateData(VariableAssetUpdate assetRequest) {
        quantity = assetRequest.quantity() != null ? assetRequest.quantity() : quantity;
        averageValue = assetRequest.averageValue() != null ? assetRequest.averageValue() : averageValue;
    }
}
