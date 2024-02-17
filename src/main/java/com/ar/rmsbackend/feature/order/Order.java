package com.ar.rmsbackend.feature.order;


import com.ar.rmsbackend.feature.customer.Customer;
import com.ar.rmsbackend.generic.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order extends BaseEntity {

    @ManyToOne()
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "customer_id", insertable = false, updatable = false)
    private Long customerId;

    @OneToMany(mappedBy = "order")
    private List<OrderedItem> orderedItems;

    private LocalDate orderDate;

}
