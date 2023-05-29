package com.quaz.ticket.entity;

import com.quaz.ticket.enums.VoucherType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "vouchers")
public class Voucher extends AbstractPersistable<Long> {

    @Column(name = "code", nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VoucherType type;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public BigDecimal apply(BigDecimal totalPrice) {
        return switch (type) {
            case PERCENTAGE -> totalPrice.multiply(amount.divide(new BigDecimal(100)));
            case AMOUNT -> totalPrice.subtract(amount);
        };
    }
}