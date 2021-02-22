package com.soen390.team11.repository;

import com.soen390.team11.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, String> {

}
