package org.esnack24api.esnack24api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@MapperScan("org.esnack24api.esnack24api.**.mapper")
public class ESnack24ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESnack24ApiApplication.class, args);
    }

}
