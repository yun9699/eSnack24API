package org.esnack24api.esnack24api.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.allergy.domain.AllergyEntity;
import org.esnack24api.esnack24api.allergy.repository.AllergyRepository;
import org.esnack24api.esnack24api.user.domain.UserAllergyEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.esnack24api.esnack24api.user.dto.UserAllergyRegisterDTO;
import org.esnack24api.esnack24api.user.repository.UserAllergyRepository;
import org.esnack24api.esnack24api.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class UserAllergyService {

    private final UserAllergyRepository userAllergyRepository;
    private final UserRepository userRepository;
    private final AllergyRepository allergyRepository;

    public String registerPersonalAllergy(UserAllergyRegisterDTO dto) {

        UserEntity user = userRepository.findById(dto.getUno()).orElseThrow();

        Arrays.stream(dto.getAnos()).forEach(ano -> {

            AllergyEntity allergy = allergyRepository.findById(ano)
                    .orElseThrow(() -> new NoSuchElementException("Allergy not found for ID: " + ano));


            UserAllergyEntity userAllergyEntity = UserAllergyEntity.builder()
                    .user(user)
                    .allergy(allergy)
                    .build();

            userAllergyRepository.save(userAllergyEntity);
        });

        return "Successfully registered personal allergy";
    }
}
