package com.java.onlinestore.payment;

import com.java.onlinestore.model.Payment;
import com.java.onlinestore.util.RandomPaymentGenerator;
import org.springframework.stereotype.Service;

@Service
public class RandomPaymentImpl implements PaymentGateway{
    @Override
    public Payment makePayment(Payment payment) {
        payment.setPaymentStatus(RandomPaymentGenerator.getPaymentStatus());
        payment.setReferenceId(RandomPaymentGenerator.getReferenceId());
        payment.setDiscount(RandomPaymentGenerator.getDiscount());
        payment.setPaidAmount(calculateDiscountedAmount(payment.getTotalAmount(), payment.getDiscount()));
        return payment;
    }
    private double calculateDiscountedAmount(double actualAmount, double discount){
        return actualAmount - (actualAmount*discount/100);
    }
}
