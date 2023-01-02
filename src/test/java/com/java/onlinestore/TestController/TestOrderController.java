package com.java.onlinestore.TestController;


import com.java.onlinestore.controllers.OrderController;
import com.java.onlinestore.model.Order;
import com.java.onlinestore.model.OrderDetails;
import com.java.onlinestore.model.Payment;
import com.java.onlinestore.requestModel.PaymentRequest;
import com.java.onlinestore.services.OrderService;
import com.java.onlinestore.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.java.onlinestore.model.PaymentStatus.COMPLETED;
import static com.java.onlinestore.util.UtilityFunction.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {TestOrderController.class})
@ContextConfiguration
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.java.onlinestore")
public class TestOrderController {

    List<Order> orderList;
    Order order;

    @Autowired
    MockMvc mockMvc;

    @Mock
    OrderService orderService;

    @Mock
    PaymentService paymentService;

    @InjectMocks
    OrderController orderController;

    @BeforeEach
    public void setUp() {
     mockMvc= MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testGetAllOrder() throws Exception {

        orderList=new ArrayList<>();
        List<OrderDetails> items=new ArrayList<>();
        items.add(new OrderDetails(1,1,1));
        items.add(new OrderDetails(2,5,4));
        orderList.add(new Order(1,2,items));
        orderList.add(new Order(2,4,items));

        when(orderService.getAllOrder()).thenReturn(orderList);
        this.mockMvc.perform(get("/order"))
                .andDo(print());

    }

    @Test
    void testGetOrderById() throws Exception{
        List<OrderDetails> items=new ArrayList<>();
        items.add(new OrderDetails(1,1,1));
        items.add(new OrderDetails(2,5,4));

        Optional<Order>orders=Optional.of(new Order(2,4,items));
        when(orderService.getOrderById(2)).thenReturn(orders);

        this.mockMvc.perform(get("/order/{id}", 2))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testPlaceOrder() throws Exception {

        List<OrderDetails> items=new ArrayList<>();
        items.add(new OrderDetails(1,1,1));
        Order currentOrder=new Order(4,7,items);
        when(orderService.placeOrder(currentOrder)).thenReturn(currentOrder);

        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/order/placeOrder")
                        .content(asJsonString(currentOrder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testMakePayment() throws Exception
    {
        PaymentRequest paymentRequest = new PaymentRequest(100, 250);

        Payment payment=new Payment();
        payment.setOrderId(paymentRequest.getOrderId());
        payment.setTotalAmount(paymentRequest.getTotalAmount());

        Payment paymentAfterGateway=new Payment(1,100,250.00,225.00,10,COMPLETED, UUID.randomUUID());

        when(paymentService.makePayment(payment)).thenReturn(paymentAfterGateway);
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/order/makePayment")
                        .content(asJsonString(paymentRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());


    }

    @Test
    void testRemoveOrderById() throws Exception
    {
        List<OrderDetails> items=new ArrayList<>();
        items.add(new OrderDetails(1,1,1));
        items.add(new OrderDetails(2,5,4));

        Optional<Order>orders=Optional.of(new Order(2,4,items));
        when(orderService.getOrderById(2)).thenReturn(orders);

        this.mockMvc.perform( MockMvcRequestBuilders.delete("/order/{id}", 2))
                .andExpect(status().isOk())
                .andDo(print());
        verify(orderService, times(1)).removeOrderById(2);


    }
}
