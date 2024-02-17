package com.ar.rmsbackend.feature.customer;

import com.ar.rmsbackend.generic.payload.request.IDto;
import com.ar.rmsbackend.payload.request.UserRequestDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto implements IDto {

    @Schema(example = "Dhaka")
    private String address;

    @Schema(example = "Asif")
    private String name;

    private UserRequestDto userRequestDto;

}
