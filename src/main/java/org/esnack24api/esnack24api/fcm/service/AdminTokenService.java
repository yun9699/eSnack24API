package org.esnack24api.esnack24api.fcm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.esnack24api.esnack24api.fcm.dto.AdminTokenFCMDTO;
import org.esnack24api.esnack24api.fcm.mapper.AdminTokenFCMMappaer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class AdminTokenService {

    private final AdminTokenFCMMappaer adminTokenFCMMappaer;

    public List<String> getFCMToken() {

        log.info("Getting FCM token");

        return adminTokenFCMMappaer.getFCMToken();

    }


}
