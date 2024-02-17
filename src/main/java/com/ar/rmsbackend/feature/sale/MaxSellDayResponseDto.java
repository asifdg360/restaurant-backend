package com.ar.rmsbackend.feature.sale;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MaxSellDayResponseDto {

    private LocalDate saleDate;
    private Double amount;
}
