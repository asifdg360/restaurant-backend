package com.ar.rmsbackend.feature.customer;


import com.ar.rmsbackend.payload.response.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private Long id;

    private String address;

    private UserResponseDto userResponseDto;
}
