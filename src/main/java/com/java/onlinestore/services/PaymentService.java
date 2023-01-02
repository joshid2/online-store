package com.java.onlinestore.services;

import com.java.onlinestore.model.Payment;
import com.java.onlinestore.payment.PaymentGateway;
import com.java.onlinestore.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentGateway paymentGateway;
    public Payment makePayment(Payment payment){
        payment = paymentGateway.makePayment(payment);
        return paymentRepository.save(payment);
    }
}
