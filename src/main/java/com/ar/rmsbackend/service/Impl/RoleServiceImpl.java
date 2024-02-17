package com.ar.rmsbackend.service.Impl;

import com.ar.rmsbackend.common.Utils.Helper;
import com.ar.rmsbackend.common.constant.ApplicationConstant;
import com.ar.rmsbackend.common.constant.ErrorId;
import com.ar.rmsbackend.common.exception.RmsServerException;
import com.ar.rmsbackend.generic.service.AbstractService;
import com.ar.rmsbackend.model.Role;
import com.ar.rmsbackend.model.User;
import com.ar.rmsbackend.payload.request.RoleRequestDto;
import com.ar.rmsbackend.payload.response.RoleResponse;
import com.ar.rmsbackend.payload.response.UserResponseDto;
import com.ar.rmsbackend.repository.RoleRepository;
import com.ar.rmsbackend.service.RoleService;
import com.ar.rmsbackend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends AbstractService<Role, RoleRequestDto> implements RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;

    public RoleServiceImpl(RoleRepository roleRepository, UserService userService) {
        super(roleRepository);
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    protected RoleResponse convertToResponseDto(Role role) {
        RoleResponse response = new RoleResponse();
        response.setRoleName(role.getName());
        response.setId(role.getId());

        Set<UserResponseDto> userResponseSet = role.getUserSet().stream().map(user -> {
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setId(user.getId());
            responseDto.setUsername(user.getUsername());
            return responseDto;
        }).collect(Collectors.toSet());

        response.setUsers(userResponseSet);
        return response;
    }

    @Override
    protected Role convertToEntity(RoleRequestDto roleRequestDto) {
        return mapToEntity(roleRequestDto, new Role());
    }

    @Override
    protected Role convertToEntity(RoleRequestDto roleRequestDto, Role entity) {
        return mapToEntity(roleRequestDto, entity);
    }

    private Role mapToEntity(RoleRequestDto dto, Role entity) {
        entity.setName(dto.getName().trim());
        Set<Long> userIds = dto.getUserIds();
        Set<User> userSet = entity.getUserSet();
        if (!CollectionUtils.isEmpty(userSet)) {
            Iterator<User> userIterator = userSet.iterator();

            while (userIterator.hasNext()) {
                User user = userIterator.next();

                //Removing userIds from dto if those user has already in role
                if (userIds.contains(user.getId())) {
                    userIds.remove(user.getId());
                } else {
                    userIterator.remove(); // removing those permitted user whose user id not contains in dto userIds
                }
            }

            return setUserToRole(entity, userIds, userSet);

        }


        return setUserToRole(entity, userIds, new HashSet<>());
    }

    private Role setUserToRole(Role entity, Set<Long> userIds, Set<User> userSet) {
        if (!CollectionUtils.isEmpty(userIds) && Objects.nonNull(entity.getId())) {
            Set<User> newUserSet = userService.findAllUserByIdIn(userIds, ApplicationConstant.ACTIVE_TRUE);
            newUserSet.forEach(user -> user.setRole(entity));
            userSet.addAll(newUserSet);
        }
        entity.setUserSet(userSet);
        return entity;
    }

    public void validateClientData(RoleRequestDto dto, Role entity) {
        if (Objects.nonNull(dto.getName()) && !dto.getName().trim().isBlank()) {
            Optional<Role> optionalRole = roleRepository.findByNameIgnoreCase(dto.getName().trim());

            if (optionalRole.isPresent()) {
                Role role = optionalRole.get();
                if (Objects.nonNull(entity) && role.equals(entity)) {
                    return;
                }
                if (dto.getName().trim().equalsIgnoreCase(role.getName())) {
                    throw RmsServerException.badRequest(Helper.createDynamicCode(ErrorId.DUPLICITY,
                            ApplicationConstant.ROLE_NAME_DUPLICATE_MSG));
                }
            }

        }
    }
}
