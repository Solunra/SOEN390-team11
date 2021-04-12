package com.soen390.team11.repository;

import com.soen390.team11.entity.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
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

    /**
     * average monthly expense
     * @return
     */
    @Query(
            value = "SELECT AVG(temp.COST) as average FROM (SELECT EXTRACT(MONTH FROM o.ordertime) as month, SUM(o.quantity*r.cost) as COST FROM orders o, rawmaterial r WHERE o.saleID = r.rawmaterialid GROUP BY month) AS temp",
            nativeQuery = true)
    Map<String, Object> averageCostByMonth();

    /**
     * monthly expense , that all admin order raw material
     * @return
     */
    @Query(
            value = "SELECT EXTRACT(MONTH FROM o.ordertime) as month, SUM(o.quantity*r.cost) as PRICE FROM orders o, rawmaterial r WHERE o.saleID = r.rawmaterialid GROUP BY month",
            nativeQuery = true)
    List<Map<String, Object>> groupByMonth();

    /**
     * total expense by year
     * @param year
     * @return
     */
    @Query(
            value = "SELECT SUM(o.quantity*r.cost) as total FROM orders o, rawmaterial r WHERE o.saleID = r.rawmaterialid AND EXTRACT(YEAR FROM o.ordertime) = :year",
            nativeQuery = true)
    Map<String, Object> totalIncome(@Param("year") Integer year);
}
