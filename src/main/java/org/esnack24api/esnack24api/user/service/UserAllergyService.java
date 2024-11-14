package org.esnack24api.esnack24api.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.allergy.domain.AllergyEntity;
import org.esnack24api.esnack24api.allergy.repository.AllergyRepository;
import org.esnack24api.esnack24api.user.domain.UserAllergyEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.esnack24api.esnack24api.user.repository.UserAllergyRepository;
import org.esnack24api.esnack24api.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class UserAllergyService {

    private final UserAllergyRepository userAllergyRepository;
    private final UserRepository userRepository;
    private final AllergyRepository allergyRepository;

    public String registerPersonalAllergy(List<Long> anos, Long uno) {

        UserEntity user = userRepository.findById(uno).orElseThrow();

        anos.forEach(ano -> {

            AllergyEntity allergy = allergyRepository.findById(ano).orElseThrow();

            UserAllergyEntity userAllergyEntity = UserAllergyEntity.builder()
                    .user(user)
                    .allergy(allergy)
                    .build();

            userAllergyRepository.save(userAllergyEntity);
        });

        return "Successfully registered personal allergy";
    }
}
