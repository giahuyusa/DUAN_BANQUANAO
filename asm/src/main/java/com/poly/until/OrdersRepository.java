package com.poly.until;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poly.model.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
}

