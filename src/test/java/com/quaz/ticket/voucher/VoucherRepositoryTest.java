package com.quaz.ticket.voucher;

import com.quaz.ticket.testcontainers.EnableTestcontainers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;

@SpringBootTest
@EnableTestcontainers
class VoucherRepositoryTest {

    @Autowired
    private VoucherRepository voucherRepository;

    @Test
    void givenVoucherWhenQueriedByCorrectCodeThenVoucherIsReturned() {
        //given

        //when
        final var foundVoucher = voucherRepository.findFirstByCodeLike("TICKET_50");

        //then
        assertThat(foundVoucher, is(notNullValue()));
        assertThat(foundVoucher.getCode(), is("TICKET_50"));
        assertThat(foundVoucher.getType(), is(VoucherType.PERCENTAGE));
        assertThat(foundVoucher.getAmount(), is(BigDecimal.valueOf(50)));
    }

    @Test
    void givenVoucherWhenQueriedByIncorrectCodeThenNullIsReturned() {
        //given

        //when
        final var foundVoucher = voucherRepository.findFirstByCodeLike("NOT_EXISTING_CODE");

        //then
        assertThat(foundVoucher, is(nullValue()));
    }

}