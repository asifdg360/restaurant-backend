package com.ar.rmsbackend.feature.order;


import com.ar.rmsbackend.feature.item.Item;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_id", insertable = false, updatable = false)
    private Long itemId;

    @ManyToOne
    private Order order;

    @Column(name = "order_id", insertable = false, updatable = false)
    private Long orderId;

    private Integer quantity;

}
