package com.ntnn.booking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
public class TransactionDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    @Column(name = "paymentMode")
    private String paymentMode;

    @Column(name = "bookingId", columnDefinition = "integer default 0")
    private Integer bookingId;

    @Column(name = "upiId")
    private String upiId;

    @Column(name = "cardNumber")
    private String cardNumber;
}
