package com.ar.rmsbackend.config.authentication.service;

import com.ar.rmsbackend.payload.request.LoginRequest;
import com.ar.rmsbackend.payload.response.JwtResponse;

public interface AuthService {

    JwtResponse authenticateUser(LoginRequest loginRequest);
}
