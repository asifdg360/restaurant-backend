package com.ar.rmsbackend.feature.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponseDto {

    private Long id;
    private Long orderId;
    private LocalDate saleDate;
    private Double totalPrice;
}
