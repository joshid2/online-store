package com.java.onlinestore.payment;

import com.java.onlinestore.model.Payment;

public interface PaymentGateway {
    public Payment makePayment(Payment payment);
}
