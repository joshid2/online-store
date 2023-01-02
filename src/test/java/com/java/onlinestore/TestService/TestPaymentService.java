package com.java.onlinestore.TestService;

import com.java.onlinestore.model.Payment;
import com.java.onlinestore.payment.PaymentGateway;
import com.java.onlinestore.repository.PaymentRepository;
import com.java.onlinestore.requestModel.PaymentRequest;
import com.java.onlinestore.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static com.java.onlinestore.model.PaymentStatus.COMPLETED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TestPaymentService.class})
public class TestPaymentService {

    @Mock
    PaymentRepository paymentRepository;
    @Mock
    PaymentGateway paymentGateway;

    @InjectMocks
    PaymentService paymentService;

    //PaymentRequest paymentRequest;

    @Test
    public void testMakePayment() {

        Payment payment=new Payment(1,100,250.00,0,0,null,null);
        Payment paymentAfterGateway=new Payment(1,100,250.00,225.00,10,COMPLETED,UUID.randomUUID());

        when(paymentGateway.makePayment(payment)).thenReturn(paymentAfterGateway);
        when(paymentRepository.save(paymentAfterGateway)).thenReturn(paymentAfterGateway);
        assertEquals(COMPLETED,paymentService.makePayment(payment).getPaymentStatus());

    }
}
