package com.soen390.team11.repository;

import com.soen390.team11.entity.Orders;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import java.time.OffsetDateTime;
import java.util.Optional;

public interface OrdersRepository extends CrudRepository<Orders, String> {
    Optional<Orders> findByOrderID(String s);
    Iterable<Orders> findAll();
    Iterable<Orders> findAllByTimeBefore(OffsetDateTime offsetDateTime);
}
