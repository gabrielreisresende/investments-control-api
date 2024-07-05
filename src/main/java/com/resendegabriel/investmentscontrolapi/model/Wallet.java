package com.resendegabriel.investmentscontrolapi.model;

import com.resendegabriel.investmentscontrolapi.model.dto.WalletRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    List<VariableAsset> variableAssets;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.REMOVE)
    List<FixedAsset> fixedAssets;

    public static Wallet fromRequest(WalletRequest walletRequest) {
        return Wallet.builder()
                .name(walletRequest.name())
                .build();
    }

    public void updateData(WalletRequest walletRequest) {
        this.name = walletRequest.name() != null ? walletRequest.name() : this.name;
    }
}
