package com.ar.rmsbackend.controller;

import com.ar.rmsbackend.common.Routes.ApiConstants;
import com.ar.rmsbackend.common.Routes.Router;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = ApiConstants.TEST)
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(Router.PING)
    public String checkPing() {
        logger.info("PING api Hit");
        return "Hello RMS API";
    }
}
