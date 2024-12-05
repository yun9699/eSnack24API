package org.esnack24api.esnack24api.exchangee_rate.domain;

import jakarta.persistence.*;
import lombok.*;
import org.esnack24api.esnack24api.admin.domain.AdminEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Entity
@Table(name = "tbl_exchange_rate")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"admin"})
public class ExchangeRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long erno;

    private String base_currency;

    private String target_currency;

    private BigDecimal rate;

    private Timestamp erupdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admno")
    private AdminEntity admin;
}
