package com.canteen.repository;

import com.canteen.model.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderTable, Long> { }
