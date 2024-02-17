package com.ar.rmsbackend.feature.item;


import com.ar.rmsbackend.generic.model.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {

    private String name;

    private String code;

    private double price;
}
