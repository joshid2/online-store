package com.java.onlinestore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name="payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column
    private int orderId;
    @Column
    private double totalAmount;
    @Column
    private double paidAmount;
    @Column
    private double discount;
    @Column
    private PaymentStatus paymentStatus;
    @Column
    private UUID referenceId;
}
