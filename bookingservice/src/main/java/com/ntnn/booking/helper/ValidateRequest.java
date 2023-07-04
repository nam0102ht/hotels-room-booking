package com.ntnn.booking.helper;

import com.google.common.base.Strings;
import com.ntnn.booking.common.BookingConstant;
import com.ntnn.booking.model.TransactionRequest;
import jakarta.validation.ValidationException;
import java.util.Objects;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidateRequest {
    public void validateTransactionRequest(TransactionRequest transactionRequest) {
        if (Strings.isNullOrEmpty(transactionRequest.getPaymentMode())) {
            throw new ValidationException("Invalid Mode of payment");
        }
        String mode = BookingConstant.PAYMENT_METHOD.getOrDefault(transactionRequest.getPaymentMode(), null);
        if (Objects.isNull(mode)) {
            throw new ValidationException("Invalid Mode of payment");
        }
    }
}
