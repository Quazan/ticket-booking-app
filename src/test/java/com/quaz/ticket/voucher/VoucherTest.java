package com.quaz.ticket.voucher;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

class VoucherTest {

    @Test
    void givenVoucherWithPercentageWhenAppliedThenCorrectValueIsCalculated() {
        //given
        final var voucher = new Voucher();
        voucher.setCode("10_PERCENT");
        voucher.setType(VoucherType.PERCENTAGE);
        voucher.setAmount(BigDecimal.TEN);

        //when
        final var result = voucher.apply(BigDecimal.valueOf(100));

        //then
        assertEquals(BigDecimal.valueOf(90).setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    void givenVoucherWith0PercentageWhenAppliedThenOriginalPriceIsReturned() {
        //given
        final var voucher = new Voucher();
        voucher.setCode("0_PERCENT");
        voucher.setType(VoucherType.PERCENTAGE);
        voucher.setAmount(BigDecimal.ZERO);

        //when
        final var result = voucher.apply(BigDecimal.valueOf(100));

        //then
        assertEquals(BigDecimal.valueOf(100).setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    void givenVoucherWith100PercentageZeroWhenAppliedThen0IsReturned() {
        //given
        final var voucher = new Voucher();
        voucher.setCode("100_PERCENT");
        voucher.setType(VoucherType.PERCENTAGE);
        voucher.setAmount(BigDecimal.valueOf(100));

        //when
        final var result = voucher.apply(BigDecimal.valueOf(100));

        //then
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    void givenVoucherWithGreaterThen100PercentageZeroWhenAppliedThen0IsReturned() {
        //given
        final var voucher = new Voucher();
        voucher.setCode("200_PERCENT");
        voucher.setType(VoucherType.PERCENTAGE);
        voucher.setAmount(BigDecimal.valueOf(100));

        //when
        final var result = voucher.apply(BigDecimal.valueOf(100));

        //then
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    void givenVoucherWithAmountWhenAppliedThenCorrectValueIsCalculated() {
        //given
        final var voucher = new Voucher();
        voucher.setCode("10_AMOUNT");
        voucher.setType(VoucherType.AMOUNT);
        voucher.setAmount(BigDecimal.TEN);

        //when
        final var result = voucher.apply(BigDecimal.valueOf(100));

        //then
        assertEquals(BigDecimal.valueOf(90), result);
    }

    @Test
    void givenVoucherWithAmountLargerThanPriceWhenAppliedThenZeroIsReturned() {
        //given
        final var voucher = new Voucher();
        voucher.setCode("10_AMOUNT");
        voucher.setType(VoucherType.AMOUNT);
        voucher.setAmount(BigDecimal.TEN);

        //when
        final var result = voucher.apply(BigDecimal.valueOf(5));

        //then
        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void givenVoucherWithNegativeAmountLargerThanPriceWhenAppliedThenOriginalPriceIsReturned() {
        //given
        final var voucher = new Voucher();
        voucher.setCode("MINUS_5_AMOUNT");
        voucher.setType(VoucherType.AMOUNT);
        voucher.setAmount(BigDecimal.TEN.negate());

        //when
        final var result = voucher.apply(BigDecimal.valueOf(5));

        //then
        assertEquals(BigDecimal.valueOf(5), result);
    }
}