package com.ar.rmsbackend.feature.item;

import com.ar.rmsbackend.common.Routes.Router;
import com.ar.rmsbackend.generic.controller.AbstractController;
import com.ar.rmsbackend.generic.service.IService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Router.ITEM)
public class ItemController extends AbstractController<Item, ItemDto> {
    public ItemController(IService<Item, ItemDto> service) {
        super(service);
    }
}
