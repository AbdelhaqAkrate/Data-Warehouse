package com.clustered.DataWarehouse.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Deal {

    @Id
    private String id;
    @Column(nullable = false, length = 3)
    private String fromCurrency;
    @Column(nullable = false, length = 3)
    private String toCurrency;
    @Column(nullable = false)
    private LocalDateTime dealTimestamp;
    @Column(nullable = false)
    private Double amount;
}
