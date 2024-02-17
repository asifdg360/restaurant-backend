package com.ar.rmsbackend.feature.sale;


import com.ar.rmsbackend.generic.payload.request.IDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaleDto implements IDto {

    private Long orderId;

    private LocalDate sellDate;

    private Double totalPrice;

}
