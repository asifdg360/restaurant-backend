package com.ar.rmsbackend.feature.customer;

import com.ar.rmsbackend.common.Routes.Router;
import com.ar.rmsbackend.generic.controller.AbstractController;
import com.ar.rmsbackend.generic.service.IService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Router.CUSTOMER)
public class CustomerController extends AbstractController<Customer, CustomerDto> {
    public CustomerController(IService<Customer, CustomerDto> service) {
        super(service);
    }

}
