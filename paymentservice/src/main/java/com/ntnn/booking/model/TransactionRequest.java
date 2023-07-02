package com.ntnn.booking.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {
    private String paymentMode;
    private int bookingId;
    private String upiId;
    private String cardNumber;
}
