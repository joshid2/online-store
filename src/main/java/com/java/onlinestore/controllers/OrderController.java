package com.java.onlinestore.controllers;

import com.java.onlinestore.ResponseModel.OrderResponse;
import com.java.onlinestore.ResponseModel.PaymentResponse;
import com.java.onlinestore.model.Item;
import com.java.onlinestore.model.Order;
import com.java.onlinestore.model.Payment;
import com.java.onlinestore.model.PaymentStatus;
import com.java.onlinestore.requestModel.PaymentRequest;
import com.java.onlinestore.services.OrderService;
import com.java.onlinestore.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable("id") int id) {
        Optional<Order> order = orderService.getOrderById(id);

        return order.map(value -> ResponseEntity.ok(new OrderResponse("Order found", value))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OrderResponse("Order not found", null)));
    }

    @PostMapping("/placeOrder")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @PostMapping("/makePayment")
    public ResponseEntity<PaymentResponse> makePayment (@RequestBody PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setOrderId(paymentRequest.getOrderId());
        payment.setTotalAmount(paymentRequest.getTotalAmount());

        Payment result = paymentService.makePayment(payment);
        if (result.getPaymentStatus().equals(PaymentStatus.COMPLETED)) {
            return ResponseEntity.ok(new PaymentResponse(result.getPaymentStatus(), result.getReferenceId()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PaymentResponse(result.getPaymentStatus(), result.getReferenceId()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> removeOrderById(@PathVariable("id") int id) {
        Optional<Order> order = orderService.getOrderById(id);

        if (order.isPresent()) {
            orderService.removeOrderById(id);
            return ResponseEntity.ok(new OrderResponse("Order deleted", order.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OrderResponse("Order not found", null));
    }
}
