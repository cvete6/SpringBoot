package mk.finki.ukim.mk.lab.repository.InMemoryImpl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryOrderRepositoryImpl implements OrderRepository {

    @Override
    public List<Order> getAllOrders()
    {
        return new ArrayList<>(DataHolder.orders);
    }

    @Override
    public Order save(Order order) {
        System.out.println("[WP-Log] {save from InMemoryOrderRepositoryImpl}");
        DataHolder.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.empty();
    }

}
