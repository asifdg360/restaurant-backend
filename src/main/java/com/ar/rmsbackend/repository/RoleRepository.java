package com.ar.rmsbackend.repository;

import com.ar.rmsbackend.generic.repository.AbstractRepository;
import com.ar.rmsbackend.model.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends AbstractRepository<Role> {

    Optional<Role> findByNameIgnoreCase(String name);
}
