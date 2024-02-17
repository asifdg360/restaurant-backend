package com.ar.rmsbackend.feature.customer;


import com.ar.rmsbackend.generic.model.BaseEntity;
import com.ar.rmsbackend.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @OneToOne()
    private User user;

    @Column(name = "user_id ",insertable = false, updatable = false)
    private Long userId;

    private String name;

    private String address;

}
