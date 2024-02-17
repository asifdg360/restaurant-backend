package com.ar.rmsbackend.feature.item;

import com.ar.rmsbackend.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto implements IDto {

    @Schema(example = "Chicken Burger")
    private String name;

    @Schema(example = "B01")
    private String code;

    @Schema(example = "100")
    private double price;
}
