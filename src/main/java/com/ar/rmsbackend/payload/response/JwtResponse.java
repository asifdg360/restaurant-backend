package com.ar.rmsbackend.payload.response;

import com.ar.rmsbackend.common.constant.ApplicationConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String token;
    private String refreshToken;
    private String type = ApplicationConstant.AUTHORIZATION_TYPE_BEARER;
    private List<String> roles;
}
