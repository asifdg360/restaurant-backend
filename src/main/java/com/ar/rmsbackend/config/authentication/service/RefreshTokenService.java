package com.ar.rmsbackend.config.authentication.service;

import com.ar.rmsbackend.config.authentication.model.RefreshToken;
import com.ar.rmsbackend.payload.request.RefreshTokenRequest;
import com.ar.rmsbackend.payload.response.RefreshTokenResponse;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Long userId);

    RefreshToken verifyRefreshTokenExpiration(RefreshToken refreshToken);

    RefreshTokenResponse tokenRefresh(RefreshTokenRequest request);
}
