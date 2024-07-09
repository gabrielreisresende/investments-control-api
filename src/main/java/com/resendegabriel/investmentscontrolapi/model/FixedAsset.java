package com.resendegabriel.investmentscontrolapi.model;

import com.resendegabriel.investmentscontrolapi.model.dto.fixedAsset.FixedAssetRequest;
import com.resendegabriel.investmentscontrolapi.model.dto.fixedAsset.FixedAssetUpdate;
import com.resendegabriel.investmentscontrolapi.model.enums.FixedAssetType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "TB_FIXED_ASSETS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixedAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FixedAssetType fixedAssetType;

    private BigDecimal initialValue;

    private BigDecimal currentValue;

    private LocalDate valid;

    @Transient
    private BigDecimal rentability;

    @ManyToOne
    private Wallet wallet;

    @PostLoad
    @PostUpdate
    @PostPersist
    public void postLoad() {
        if (currentValue.compareTo(initialValue) > 0) {
            rentability = currentValue.subtract(initialValue)
                    .divide(initialValue, RoundingMode.HALF_UP);
        } else {
            rentability = BigDecimal.ZERO;
        }
    }

    public static FixedAsset fromRequest(FixedAssetRequest assetRequest, Wallet wallet) {
        return FixedAsset.builder()
                .fixedAssetType(FixedAssetType.valueOf(assetRequest.fixedAssetType()))
                .initialValue(assetRequest.initialValue())
                .currentValue(assetRequest.currentValue())
                .valid(assetRequest.valid())
                .wallet(wallet).
                build();
    }

    public void updateData(FixedAssetUpdate fixedAssetUpdate) {
        currentValue = fixedAssetUpdate.currentValue() != null ? fixedAssetUpdate.currentValue() : currentValue;
        valid = fixedAssetUpdate.valid() != null ? fixedAssetUpdate.valid() : valid;
    }
}
