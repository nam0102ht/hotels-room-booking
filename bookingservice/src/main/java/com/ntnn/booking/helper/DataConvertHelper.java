package com.ntnn.booking.helper;

import com.ntnn.booking.entity.BookingInfoEntity;
import com.ntnn.booking.exception.TechnicalException;
import com.ntnn.booking.model.BookingRequest;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DataConvertHelper {

    public static BookingInfoEntity convertEntity(BookingRequest bookingRequest) {
        String roomNumbers = "";
        ArrayList<String> lst = getRandomNumbers(bookingRequest.getNumOfRooms());
        for (String s : lst) {
            roomNumbers = String.join(",", String.valueOf(s), roomNumbers);
        }
        roomNumbers = removeLastChar(roomNumbers);
        BookingInfoEntity bookingInfo = new BookingInfoEntity();
        bookingInfo.setFromDate(convertStringToDate(bookingRequest.getFromDate()));
        bookingInfo.setToDate(convertStringToDate(bookingRequest.getToDate()));
        bookingInfo.setNumOfRooms(bookingRequest.getNumOfRooms());
        bookingInfo.setAadharNumber(bookingRequest.getAadharNumber());
        bookingInfo.setRoomNumbers(roomNumbers);
        bookingInfo.setRoomPrice(calculatePrice(bookingRequest.getNumOfRooms(),
                convertStringToDate(bookingRequest.getFromDate()),
                convertStringToDate(bookingRequest.getToDate())));
        bookingInfo.setBookOn(new Date());
        return bookingInfo;
    }

    private static Integer calculatePrice(int numOfRooms, Date dateFrom, Date dateTo) {
        LocalDate from = dateFrom.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate to = dateTo.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate();
        long numOfDate = ChronoUnit.DAYS.between(from, to);
        return 1000 * numOfRooms * (int) numOfDate;
    }

    public static Date convertStringToDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.parse(date);
        } catch (ParseException ex) {
            throw new TechnicalException(ex.getMessage(), ex);
        }
    }

    public static ArrayList<String> getRandomNumbers(int count) {
        SecureRandom rand = new SecureRandom();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }
        return numberList;
    }

    private static String removeLastChar(String s) {
        return (s == null || s.length() == 0)
                ? null
                : (s.substring(0, s.length() - 1));
    }
}
