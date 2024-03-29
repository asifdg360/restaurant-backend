package com.ar.rmsbackend.controller;

import com.ar.rmsbackend.common.Routes.ApiConstants;
import com.ar.rmsbackend.common.Routes.Router;
import com.ar.rmsbackend.config.authentication.service.AuthService;
import com.ar.rmsbackend.config.authentication.service.RefreshTokenService;
import com.ar.rmsbackend.payload.request.LoginRequest;
import com.ar.rmsbackend.payload.request.RefreshTokenRequest;
import com.ar.rmsbackend.payload.response.JwtResponse;
import com.ar.rmsbackend.payload.response.RefreshTokenResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = ApiConstants.AUTHENTICATION)
public class AuthController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping(Router.LOGIN)
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Login api Hit");
        return new ResponseEntity<>(authService.authenticateUser(loginRequest), HttpStatus.OK);
    }


    @PostMapping(Router.REFRESH_TOKEN)
    public ResponseEntity<RefreshTokenResponse> tokenRefresh(@Valid @RequestBody RefreshTokenRequest request) {
        logger.info("Refresh token api Hit");
        return new ResponseEntity<>(refreshTokenService.tokenRefresh(request), HttpStatus.OK);
    }



}
