package com.ntnn.booking.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequest {
    private String toDate;
    private String fromDate;
    private String aadharNumber;
    private int numOfRooms;

}
