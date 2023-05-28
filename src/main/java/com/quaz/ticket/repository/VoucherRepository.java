package com.quaz.ticket.repository;

import com.quaz.ticket.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import java.util.List;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    List<Voucher> findByCodeLike(@NonNull String code);

}