package com.ar.rmsbackend.feature.order;

import com.ar.rmsbackend.feature.customer.CustomerService;
import com.ar.rmsbackend.feature.item.Item;
import com.ar.rmsbackend.feature.item.ItemService;
import com.ar.rmsbackend.generic.model.BaseEntity;
import com.ar.rmsbackend.generic.service.AbstractService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends AbstractService<Order, OrderDto> implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    private final ItemService itemService;

    private final CustomerService customerService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                            ItemService itemService, CustomerService customerService) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemService = itemService;
        this.customerService = customerService;
    }


    @Override
    protected OrderResponseDto convertToResponseDto(Order order) {

        return OrderResponseDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .customerName(order.getCustomer().getName())
                .orderDate(order.getOrderDate())
                .orderItemsList(convertToResponse(order.getOrderedItems()))
                .build();
    }

    @Override
    @Transactional
    public Order create(OrderDto orderDto) {
        Order order = saveItem(convertToEntity(orderDto));
        saveOrderedItemList(orderDto.itemDtoList, order);
        return order;
    }

    @Override
    protected Order convertToEntity(OrderDto dto) {
        return mapToEntity(new Order(), dto);
    }

    @Override
    protected Order convertToEntity(OrderDto dto, Order entity) {
        return mapToEntity(entity, dto);
    }


    private Order mapToEntity(Order entity, OrderDto dto) {
        entity.setCustomer(customerService.findById(dto.getCustomerId()));
        entity.setOrderDate(LocalDate.now());
        return entity;
    }


    private void saveOrderedItemList(List<OrderItemDto> itemDtoList, Order order) {

        List<OrderedItem> orderedItems = new ArrayList<>();

        List<Item> itemList = itemService.findAllByIds(itemDtoList.stream().map(OrderItemDto::getItemId)
                .collect(Collectors.toSet()));

        Map<Long, Item> itemMap = itemList.stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));

        itemDtoList.forEach(d -> {
            orderedItems.add(
                    OrderedItem.builder()
                            .item(itemMap.get(d.getItemId()))
                            .order(order)
                            .quantity(d.getQuantity())
                            .build());

        });
        orderItemRepository.saveAll(orderedItems);
    }


    public List<OrderItemsResponseDto> convertToResponse(List<OrderedItem> orderedItems) {

        List<OrderItemsResponseDto> orderItemsResponseDtos = new ArrayList<>();
        for (OrderedItem d : orderedItems) {
            orderItemsResponseDtos.add(covertToOrderItemsResponse(d));
        }
        return orderItemsResponseDtos;

    }

    private OrderItemsResponseDto covertToOrderItemsResponse(OrderedItem orderedItem) {
        return OrderItemsResponseDto.builder()
                .itemId(orderedItem.getItemId())
                .itemName(orderedItem.getItem().getName())
                .quantity(orderedItem.getQuantity())
                .build();
    }

    @Override
    public List<OrderResponseDto> getOrderListByOrderDate(LocalDate orderDate) {

        if (Objects.isNull(orderDate)) {
            orderDate = LocalDate.now();
        }
        List<Order> orders = orderRepository.findAllByOrderDateAndIsActive(orderDate, true);
        return orders.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderResponseDto> getOrderListByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findAllByCustomerId(customerId);
        return orders.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }
}
