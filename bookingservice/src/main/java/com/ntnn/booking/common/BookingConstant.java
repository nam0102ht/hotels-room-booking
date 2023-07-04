package com.ntnn.booking.common;

import java.util.HashMap;
import java.util.Map;

public class BookingConstant {
    public final static Map<String, String> PAYMENT_METHOD = new HashMap<>() {{
        put("UPI", "UPI");
        put("CARD", "CARD");
    }};
}
