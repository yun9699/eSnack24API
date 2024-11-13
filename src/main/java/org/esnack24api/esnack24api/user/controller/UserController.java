package org.esnack24api.esnack24api.user.controller;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.user.dto.UserRegisterDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.esnack24api.esnack24api.user.dto.UserDTO;
import org.esnack24api.esnack24api.user.dto.TokenRequestDTO;
import org.esnack24api.esnack24api.user.dto.TokenResponseDTO;
import org.esnack24api.esnack24api.user.exception.UserExceptions;
import org.esnack24api.esnack24api.user.service.UserService;
import org.esnack24api.esnack24api.security.util.JWTUtil;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/login")
@Log4j2
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;

    @Value("${org.hyeong.accessTime}")
    private int accessTime;

    @Value("${org.hyeong.refreshTime}")
    private int refreshTime;

    @Value("${org.hyeong.alwaysNew}")
    private boolean alwaysNew;

    public UserController(UserService userService, JWTUtil jwtUtil) {

        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    private TokenResponseDTO generateTokenResponseDTO(UserDTO userDTO) {

        Map<String, Object> claimMap =
                Map.of("email", userDTO.getEmail());

        String accessTokenStr = jwtUtil.createToken(claimMap, accessTime);
        String refreshTokenStr = jwtUtil.createToken(claimMap, refreshTime);

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(accessTokenStr);
        tokenResponseDTO.setRefreshToken(refreshTokenStr);
        tokenResponseDTO.setEmail(userDTO.getEmail());
        tokenResponseDTO.setNew(userDTO.isNew());

        return tokenResponseDTO;
    }

    @PostMapping("makeToken")
    public ResponseEntity<TokenResponseDTO> makeToken(
            @RequestBody @Validated TokenRequestDTO tokenRequestDTO) {

        log.info("============================");
        log.info("makeToken");

        UserDTO userDTO =
                userService.authenticate(tokenRequestDTO.getEmail(), tokenRequestDTO.getPw());

        log.info("userDTO: " + userDTO);

        Map<String, Object> claimMap =
                Map.of("email", userDTO.getEmail());

        String accessToken = jwtUtil.createToken(claimMap, accessTime);
        String refreshToken = jwtUtil.createToken(claimMap, refreshTime);

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(accessToken);
        tokenResponseDTO.setRefreshToken(refreshToken);
        tokenResponseDTO.setEmail(userDTO.getEmail());
        tokenResponseDTO.setNew(userDTO.isNew());

        return ResponseEntity.ok(tokenResponseDTO);
    }

    @PostMapping(value = "refreshToken",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponseDTO> refreshToken(
            @RequestHeader("Authorization") String accessToken, String refreshToken) {

        if(accessToken == null || refreshToken == null) {
            throw UserExceptions.TOKEN_NOT_ENOUGH.get();
        }

        if(!accessToken.startsWith("Bearer ")) {
            throw UserExceptions.ACCESSTOKEN_TOO_SHORT.get();
        }

        String accessTokenStr = accessToken.substring("Bearer ".length());

        try {

            Map<String, Object> payload = jwtUtil.validateToken(accessTokenStr);

            String email = payload.get("email").toString();

            TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
            tokenResponseDTO.setAccessToken(accessTokenStr);
            tokenResponseDTO.setEmail(email);
            tokenResponseDTO.setRefreshToken(refreshToken);
            tokenResponseDTO.setNew(false);

            return ResponseEntity.ok(tokenResponseDTO);
        } catch(ExpiredJwtException ex) {

            try {

                Map<String, Object> payload = jwtUtil.validateToken(refreshToken);

                String email = payload.get("email").toString();
                String newAccessToken = null;
                String newRefreshToken = null;

                if(alwaysNew) {

                    Map<String, Object> claimMap = Map.of("email", email);
                    newAccessToken = jwtUtil.createToken(claimMap, accessTime);
                    newRefreshToken = jwtUtil.createToken(claimMap, refreshTime);
                }

                TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
                tokenResponseDTO.setAccessToken(newAccessToken);
                tokenResponseDTO.setRefreshToken(newRefreshToken);
                tokenResponseDTO.setEmail(email);
                tokenResponseDTO.setNew(false);

                return ResponseEntity.ok(tokenResponseDTO);
            } catch (ExpiredJwtException ex2) {

                throw UserExceptions.REQUIRE_SIGN_IN.get();
            }
        }
    }

    @RequestMapping("kakao")
    public ResponseEntity<TokenResponseDTO> kakaoToken(String accessToken) {

        UserDTO userDTO = userService.authKakao(accessToken);

        log.info("kakao_userDTO: " + userDTO);

        log.info("============______________=======" + generateTokenResponseDTO(userDTO));

        return ResponseEntity.ok(generateTokenResponseDTO(userDTO));
    }

    @RequestMapping("google")
    public ResponseEntity<TokenResponseDTO> googleToken(@RequestParam String accessToken) {

        UserDTO userDTO = userService.authGoogle(accessToken);

        log.info("Authenticated Google user: {}", userDTO.getEmail());

        return ResponseEntity.ok(generateTokenResponseDTO(userDTO));
    }

    @PutMapping("reg")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO, String email) {

        userService.registerUser(email, userRegisterDTO);

        return ResponseEntity.ok("User Info Register Complete");
    }
}
