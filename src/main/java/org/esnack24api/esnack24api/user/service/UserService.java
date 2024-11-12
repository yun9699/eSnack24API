package org.esnack24api.esnack24api.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import org.esnack24api.esnack24api.user.domain.UserRole;
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

        Optional<UserEntity> result = userRepository.findById(email);

        UserEntity member = result.orElseThrow(() -> UserExceptions.BAD_AUTH.get());

        String enPw = member.getPw();

        boolean match = passwordEncoder.matches(password, enPw);

        if(!match) {
            throw CommonExceptions.READ_ERROR.get();
        }

        UserDTO memberDTO = new UserDTO();
        memberDTO.setEmail(email);
        memberDTO.setPw(enPw);
        memberDTO.setRole(member.getRole().toString());

        return memberDTO;
    }

    private UserDTO returnMember(String email) {

        Optional<UserEntity> result = userRepository.findById(email);

        UserEntity memberEntity = new UserEntity();
        UserDTO memberDTO = new UserDTO();

        if(result.isPresent()) {

            memberEntity = result.get();
            memberDTO.setEmail(memberEntity.getEmail());
            memberDTO.setPw(memberEntity.getPw());
            memberDTO.setRole(memberEntity.getRole().toString());

            return memberDTO;
        }

        String pw = UUID.randomUUID().toString();
        UserEntity newMember = UserEntity.builder()
                .email(email)
                .pw(pw)
                .role(UserRole.USER)
                .build();
        userRepository.save(newMember);

        memberDTO.setEmail(email);
        memberDTO.setPw(pw);
        memberDTO.setRole(newMember.getRole().toString());

        return memberDTO;
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
