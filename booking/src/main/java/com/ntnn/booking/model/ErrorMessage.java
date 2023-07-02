package com.ntnn.booking.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private int errorId;
    private String message;
}
