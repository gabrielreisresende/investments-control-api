package com.resendegabriel.investmentscontrolapi.model;

import com.resendegabriel.investmentscontrolapi.model.auth.User;
import com.resendegabriel.investmentscontrolapi.model.dto.wallet.WalletRequest;
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
@Table(name = "TB_WALLETS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Transient
    private BigDecimal totalValue;

    @Transient
    private BigDecimal variableAssetsValue;

    @Transient
    private BigDecimal fixedAssetsValue;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    private List<VariableAsset> variableAssets;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    private List<FixedAsset> fixedAssets;

    @ManyToOne
    private User user;

    @PostLoad
    @PostUpdate
    @PostPersist
    private void calculateTotalValues() {
        this.variableAssetsValue = variableAssets != null ?
                BigDecimal.valueOf(variableAssets.stream()
                        .map(VariableAsset::getTotalValue)
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum())
                : BigDecimal.ZERO;

        this.fixedAssetsValue = fixedAssets != null?
                BigDecimal.valueOf(fixedAssets.stream()
                        .map(FixedAsset::getCurrentValue)
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum())
                : BigDecimal.ZERO;

        this.totalValue = variableAssetsValue.add(fixedAssetsValue);
    }

    public static Wallet fromRequest(WalletRequest walletRequest, User user) {
        return Wallet.builder()
                .name(walletRequest.name())
                .user(user)
                .build();
    }

    public void updateData(WalletRequest walletRequest) {
        this.name = walletRequest.name() != null ? walletRequest.name() : this.name;
    }
}
