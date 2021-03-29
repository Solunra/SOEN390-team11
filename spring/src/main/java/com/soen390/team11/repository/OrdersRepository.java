package com.soen390.team11.repository;

import com.soen390.team11.entity.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository for Orders
 */
@Repository
public interface OrdersRepository extends CrudRepository<Orders, String> {
    /**
     * Finds Order by their ID
     * @param s the Order ID
     * @return The Order as an Optional Object
     */
    Optional<Orders> findByOrderID(String s);

    /**
     * Gets all Orders
     * @return Iterable of all Orders
     */
    Iterable<Orders> findAll();

    /**
     * Finds all orders before a time
     *
     * @param offsetDateTime The time
     * @return Iterable of all orders
     */
    Iterable<Orders> findAllByTimeBefore(OffsetDateTime offsetDateTime);

    /**
     * find all order by order time between start and end date
     * @param start
     * @param end
     * @return
     */
    List<Orders> findAllByOrdertimeBetween(OffsetDateTime start, OffsetDateTime end);
}
