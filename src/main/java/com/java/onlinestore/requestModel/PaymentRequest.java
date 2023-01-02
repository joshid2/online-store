package com.java.onlinestore.requestModel;

import com.java.onlinestore.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentRequest {
    private int orderId;
    private double totalAmount;
}
