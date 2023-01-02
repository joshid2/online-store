package com.java.onlinestore.TestService;

import com.java.onlinestore.model.Order;
import com.java.onlinestore.model.OrderDetails;
import com.java.onlinestore.repository.ItemRepository;
import com.java.onlinestore.repository.OrderDetailsRepository;
import com.java.onlinestore.repository.OrderRepository;
import com.java.onlinestore.services.OrderService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sun.tools.javac.main.Option.O;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {TestOrderService.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOrderService {

    @Mock
    OrderRepository orderRepository;

    @Mock
    OrderDetailsRepository orderDetailsRepository;

    @InjectMocks
    OrderService orderService;


    @Test
    public void TestGetAllOrder() {

        List<Order> order = new ArrayList<>();

        List<OrderDetails> items = new ArrayList<>();
        items.add(new OrderDetails(1, 1, 1));
        items.add(new OrderDetails(2, 1, 1));
        items.add(new OrderDetails(3, 1, 1));
        order.add(new Order(101, 102, items));
        order.add(new Order(102, 103, items));

        when(orderRepository.findAll()).thenReturn(order);
        assertEquals(2, orderService.getAllOrder().size());
        assertEquals(order, orderService.getAllOrder());

    }

    @Test
    public void TestGetOrderById() {

        Order order = new Order(0, 101, List.of(new OrderDetails(0, 0, 0)));
        when(orderRepository.findById(0)).thenReturn(Optional.of(order));
        Optional<Order> result = orderService.getOrderById(0);
        assertTrue(result.isPresent());
        assertEquals(101, result.get().getCustomerId());

    }

    public void TestPlaceOrder() {
        List<OrderDetails> items = new ArrayList<>();

        items.add(new OrderDetails(1, 1, 1));
        items.add(new OrderDetails(2, 1, 1));

        Order order = new Order(103, 104, items);

        when(orderRepository.save(order)).thenReturn(order);
        assertEquals(104, orderService.placeOrder(order).getCustomerId());
    }

    public void TestRemoveOrderById() {

        orderService.removeOrderById(0);


        verify(orderRepository, times(1)).deleteById(0);

    }

}
