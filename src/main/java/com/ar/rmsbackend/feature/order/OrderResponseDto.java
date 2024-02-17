package com.ar.rmsbackend.feature.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long customerId;
    private String customerName;
    List<OrderItemsResponseDto> orderItemsList;
    private LocalDate orderDate;
}
