package com.ar.rmsbackend.feature.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsResponseDto {

    private Long itemId;

    private String itemName;

    private Integer quantity;
}
