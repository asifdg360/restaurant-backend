package com.ar.rmsbackend.feature.order;

import com.ar.rmsbackend.generic.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface OrderRepository extends AbstractRepository<Order> {

    List<Order> findAllByOrderDateAndIsActive(LocalDate orderDate, Boolean isActive);

    List<Order> findAllByCustomerId(Long customerId);
}

