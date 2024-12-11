package org.esnack24api.esnack24api.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.user.domain.AddressEntity;
import org.esnack24api.esnack24api.user.domain.UserEntity;
import org.esnack24api.esnack24api.user.dto.AddressRegisterDTO;
import org.esnack24api.esnack24api.user.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class AddressService {

    private final AddressRepository addressRepository;

    public String registerAddress(AddressRegisterDTO dto) {

        UserEntity user = new UserEntity();
        user.setUno(dto.getUno());

        AddressEntity address = AddressEntity.builder()
                .country(dto.getCountry())
                .address_line1(dto.getAddress_line1())
                .address_line2(dto.getAddress_line2())
                .zipcode(dto.getZipcode())
                .is_primary(dto.is_primary())
                .state(dto.getState())
                .city(dto.getCity())
                .phonenumber(dto.getPhonenumber())
                .user(user)
                .build();

        addressRepository.save(address);

        return "Address Registered Successfully";
    }

    public AddressEntity getCartAddress(Long uno) {
        UserEntity user = new UserEntity();
        user.setUno(uno);

        return addressRepository.findByUno_cartAddress(uno)
                .orElseThrow(() -> new RuntimeException("Cannot find the existing delivery address in the cart"));
    }
}
