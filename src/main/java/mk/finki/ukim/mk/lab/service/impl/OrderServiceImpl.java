package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(String pizzaType, String clientName, String address) {
        System.out.println("[WP-Log] placeOrder from OrderServiceImpl");
        Order order= new Order(pizzaType, clientName,address,new Random().nextLong());
        return this.orderRepository.save(order);
    }
}
