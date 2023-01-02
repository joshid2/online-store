package com.java.onlinestore.ResponseModel;

import com.java.onlinestore.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentResponse {
    private PaymentStatus status;
    private UUID referenceId;
}
