package com.ar.rmsbackend.feature.customer;

import com.ar.rmsbackend.generic.service.AbstractService;
import com.ar.rmsbackend.mapper.UserMapper;
import com.ar.rmsbackend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends AbstractService<Customer, CustomerDto> implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository itemRepository, UserService userService, UserMapper userMapper) {
        super(itemRepository);
        this.customerRepository = itemRepository;
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @Override
    protected CustomerResponseDto convertToResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .userResponseDto(userMapper.convertToResponse(customer.getUser()))
                .build();
    }


    @Override
    @Transactional
    public Customer create(CustomerDto customerDto) {
        Customer customer = convertToEntity(customerDto);
        customer.setUser(userService.save(customerDto.getUserRequestDto()));
        return saveItem(customer);
    }

    @Override
    protected Customer convertToEntity(CustomerDto dto) {
        return mapToEntity(new Customer(), dto);
    }

    @Override
    protected Customer convertToEntity(CustomerDto dto, Customer entity) {
        return mapToEntity(entity, dto);
    }


    private Customer mapToEntity(Customer entity, CustomerDto dto) {
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        return entity;
    }
}
