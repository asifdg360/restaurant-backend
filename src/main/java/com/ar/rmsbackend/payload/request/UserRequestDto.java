package com.ar.rmsbackend.payload.request;

import com.ar.rmsbackend.generic.payload.request.IDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto implements IDto {

    @Schema(example = "asif")
    private String username;

    @Schema(example = "asif@tn.com")
    private String email;

    @Schema(example = "1234")
    private String password;
}
