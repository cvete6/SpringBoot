package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getAllOrders();

    Order save(Order order);

    Optional<Order> findById(Long orderId);
}
