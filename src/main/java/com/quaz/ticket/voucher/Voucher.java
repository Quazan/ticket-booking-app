package com.quaz.ticket.voucher;

import com.quaz.ticket.persistence.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "vouchers")
public class Voucher extends AbstractEntity<Long> {

    @Column(name = "code", nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VoucherType type;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public BigDecimal apply(BigDecimal totalPrice) {
        return switch (type) {
            case PERCENTAGE -> calculatePercentage(totalPrice);
            case AMOUNT -> totalPrice.subtract(amount.max(BigDecimal.ZERO)).max(BigDecimal.ZERO);
        };
    }

    private BigDecimal calculatePercentage(BigDecimal totalPrice) {
        final var pct = BigDecimal.valueOf(100).subtract(amount.min(BigDecimal.valueOf(100).max(BigDecimal.ZERO)));
        return totalPrice.multiply(pct).scaleByPowerOfTen(-2);
    }
}