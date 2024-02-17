package com.ar.rmsbackend.feature.order;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {

    @Schema(example = "1")
    private Long itemId;

    @Schema(example = "2")
    private Integer quantity;
}
