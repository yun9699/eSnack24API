package org.esnack24api.esnack24api.user.dto;

import lombok.Data;

@Data
public class TokenResponseDTO {

    private String email;

    private String accessToken;

    private String refreshToken;
}
