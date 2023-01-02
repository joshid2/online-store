package com.java.onlinestore.util;

import com.java.onlinestore.model.PaymentStatus;

import java.util.Random;
import java.util.UUID;

public class RandomPaymentGenerator {

    private static final Random random = new Random();
    public static PaymentStatus getPaymentStatus(){
        PaymentStatus[] statusValues = PaymentStatus.values();
        return statusValues[random.nextInt(statusValues.length)];
    }

    public static UUID getReferenceId(){
        return UUID.randomUUID();
    }

    public static double getDiscount(){
        return random.nextDouble(0,10);
    }
}
