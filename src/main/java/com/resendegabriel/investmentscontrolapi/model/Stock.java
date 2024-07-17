package com.resendegabriel.investmentscontrolapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_STOCKS",
        indexes = {@Index(columnList = "code", name = "stock_code_idx"),
                @Index(columnList = "type", name = "stock_type_idx"),
                @Index(columnList = "sector", name = "stock_sector_idx")})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("stock")
    @Column(length = 20)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @JsonProperty("close")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal closePrice;

    @Column(length = 100)
    private String sector;

    @Column(nullable = false, length = 20)
    private String type;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        this.updatedAt = LocalDateTime.now().withNano(0);
    }
}