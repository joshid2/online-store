package com.java.onlinestore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private int id;

    @Column
    private int customerId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_details_fk", referencedColumnName = "order_id")
    private List<OrderDetails> items;
}
