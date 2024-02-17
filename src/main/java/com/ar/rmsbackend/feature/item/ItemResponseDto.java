package com.ar.rmsbackend.feature.item;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponseDto {

    private Long id;

    private String name;

    private String code;

    private double price;
}
