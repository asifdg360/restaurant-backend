package com.ar.rmsbackend.feature.order;

import com.ar.rmsbackend.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto implements IDto {

    @Schema(example = "1")
    private Long customerId;

//    @Schema(example = "")
//    private LocalDate orderDate;

    List<OrderItemDto> itemDtoList;

}
