package com.ntnn.booking.helper;

import com.ntnn.booking.entity.TransactionDetailsEntity;
import com.ntnn.booking.model.TransactionRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConvertDataHelper {
    public static TransactionDetailsEntity convertDataHelper(TransactionRequest transactionRequest) {
        TransactionDetailsEntity entity = new TransactionDetailsEntity();
        entity.setBookingId(transactionRequest.getBookingId());
        entity.setPaymentMode(transactionRequest.getPaymentMode());
        entity.setUpiId(transactionRequest.getUpiId());
        entity.setCardNumber(transactionRequest.getCardNumber());
        return entity;
    }
}
