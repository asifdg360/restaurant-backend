package com.ar.rmsbackend.feature.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderSearchDto {

    private LocalDate orderDate;
}
