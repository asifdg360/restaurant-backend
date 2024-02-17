package com.ar.rmsbackend.feature.item;

import com.ar.rmsbackend.generic.repository.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ItemRepository extends AbstractRepository<Item> {
    Optional<Item> findByCodeAndIsActiveTrue(String code);
}

