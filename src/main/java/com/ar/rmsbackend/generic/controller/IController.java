package com.ar.rmsbackend.generic.controller;

import com.ar.rmsbackend.generic.payload.request.IDto;
import com.ar.rmsbackend.generic.payload.response.MessageResponse;
import com.ar.rmsbackend.generic.payload.response.PageData;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface IController<D extends IDto> {

//    PageData getAll(Boolean isActive, Pageable pageable);

    <T>T getSingle(Long id);

    ResponseEntity<MessageResponse> create(D d);

    ResponseEntity<MessageResponse> update(D d, Long id);

    ResponseEntity<MessageResponse> updateActiveStatus(@PathVariable Long id, Boolean isActive);

    PageData getAll(Boolean isActive, Pageable pageable);
}
