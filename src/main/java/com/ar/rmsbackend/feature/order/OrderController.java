package com.ar.rmsbackend.feature.order;

import com.ar.rmsbackend.common.Routes.Router;
import com.ar.rmsbackend.generic.controller.AbstractController;
import com.ar.rmsbackend.generic.service.IService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(Router.ORDER)
public class OrderController extends AbstractController<Order, OrderDto> {

    private final OrderService orderService;

    public OrderController(IService<Order, OrderDto> service, OrderService orderService) {
        super(service);
        this.orderService = orderService;
    }

    @GetMapping(Router.GET_ORDER_LIST_BY_DATE)
    public ResponseEntity<List<OrderResponseDto>> getOrderListByOrderDate(@RequestParam(name = "order-date",
            required = false) LocalDate orderDate) {
        return new ResponseEntity<>(orderService.getOrderListByOrderDate(orderDate), HttpStatus.OK);
    }

    @GetMapping(Router.GET_ORDER_LIST_BY_CUSTOMER)
    public ResponseEntity<List<OrderResponseDto>> getOrderListByCustomer(@RequestParam(name = "customer-id") Long customerId) {
        return new ResponseEntity<>(orderService.getOrderListByCustomerId(customerId), HttpStatus.OK);
    }
}
