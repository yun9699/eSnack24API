package org.esnack24api.esnack24api.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.user.dto.UserRegisterDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.esnack24api.esnack24api.common.exception.CommonExceptions;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.esnack24api.esnack24api.user.dto.UserDTO;
import org.esnack24api.esnack24api.user.exception.UserExceptions;
import org.esnack24api.esnack24api.user.repository.UserRepository;

import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDTO authenticate(String email, String password) {

        Optional<UserEntity> result = userRepository.findByUemail(email);

        UserEntity user = result.orElseThrow(() -> UserExceptions.BAD_AUTH.get());

        boolean match = passwordEncoder.matches(password, user.getUpw());

        if(!match) {
            throw CommonExceptions.READ_ERROR.get();
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(email);
        userDTO.setPw(user.getUpw());

        return userDTO;
    }

    public void registerUser(String email, UserRegisterDTO userRegisterDTO) {

        UserEntity result = userRepository.findByUemail(email).orElseThrow();

        result.setUsername(userRegisterDTO.getUsername());
        result.setUbirth(userRegisterDTO.getBirth());
        result.setUaddress(userRegisterDTO.getAddress());
        result.setUcallnumber(userRegisterDTO.getCallNumber());
        result.setUgender(userRegisterDTO.getGender());

        userRepository.save(result);

    }


    public UserDTO authKakao(String accessToken) {

        log.info("-------------authKakaoService-------------");

        String email = getEmailFromKakaoAccessToken(accessToken);

        log.info("email: " + email);

        return returnMember(email);
    }

    public UserDTO authGoogle(String accessToken) {
        log.info("-------------authGoogleService-------------");

        String email = getEmailFromGoogleAccessToken(accessToken);
        log.info("email: " + email);

        return returnMember(email);
    }


    private UserDTO returnMember(String email) {

        Optional<UserEntity> result = userRepository.findByUemail(email);

        UserEntity userEntity = new UserEntity();
        UserDTO userDTO = new UserDTO();

        if(result.isPresent()) {

            userEntity = result.get();
            userDTO.setEmail(userEntity.getUemail());
            userDTO.setPw(userEntity.getUpw());
            userDTO.setUsername(userEntity.getUsername());
            userDTO.setAddress(userEntity.getUaddress());
            userDTO.setGender(userDTO.getGender());
            userDTO.setBirth(userDTO.getBirth());
            userDTO.setCallNumber(userDTO.getCallNumber());
            userDTO.setNew(false);

            return userDTO;
        }

        String pw = UUID.randomUUID().toString();
        UserEntity newMember = UserEntity.builder()
                .uemail(email)
                .upw(pw)
                .build();
        userRepository.save(newMember);

        userDTO.setEmail(email);
        userDTO.setPw(pw);
        userDTO.setNew(true);

        return userDTO;
    }


    private String getEmailFromKakaoAccessToken(String accessToken) {

        String kakaoGetUserURL = "https://kapi.kakao.com/v2/user/me";

        if(accessToken == null) {
            throw new RuntimeException("Access token is null");
        }
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(kakaoGetUserURL).build();
        ResponseEntity<LinkedHashMap> response =
                restTemplate.exchange(uriBuilder.toString(), HttpMethod.GET, entity, LinkedHashMap.class);

        log.info("---------------------------Kakao response = ");
        log.info(response);

        LinkedHashMap<String, LinkedHashMap> bodyMap = response.getBody();

        log.info("++++++++++++++++++++++++++");
        log.info(bodyMap);

        LinkedHashMap<String, String> kakaoAccount = bodyMap.get("kakao_account");

        log.info("kakaoAccount: " + kakaoAccount);

        return kakaoAccount.get("email");
    }

    private String getEmailFromGoogleAccessToken(String accessToken) {
        String googleUserInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo"; // Google 사용자 정보 URL로 수정
        if (accessToken == null) {
            throw new RuntimeException("Access token is null");
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<LinkedHashMap> response = restTemplate.exchange(
                googleUserInfoUrl, HttpMethod.GET, entity, LinkedHashMap.class); // Google API URL 호출로 수정

        log.info("Google user info response: " + response);

        LinkedHashMap<String, Object> bodyMap = response.getBody();
        if (bodyMap == null || !bodyMap.containsKey("email")) {
            throw new RuntimeException("Failed to fetch email from Google");
        }

        return (String) bodyMap.get("email"); // Google API 응답에서 이메일 정보 추출
    }
}
