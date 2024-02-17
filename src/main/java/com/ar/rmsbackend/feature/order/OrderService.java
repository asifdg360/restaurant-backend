package com.ar.rmsbackend.feature.order;

import com.ar.rmsbackend.generic.service.IService;

import java.time.LocalDate;
import java.util.List;

public interface OrderService extends IService<Order, OrderDto> {


    List<OrderResponseDto> getOrderListByCustomerId(Long customerId);

    List<OrderResponseDto> getOrderListByOrderDate(LocalDate orderDate);
}
