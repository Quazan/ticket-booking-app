package com.quaz.ticket.repository;

import com.quaz.ticket.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    List<Voucher> findByCodeLike(@NonNull String code);

}