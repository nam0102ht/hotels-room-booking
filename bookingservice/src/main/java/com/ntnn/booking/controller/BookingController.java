package com.ntnn.booking.controller;

import com.ntnn.booking.entity.BookingInfoEntity;
import com.ntnn.booking.model.BookingRequest;
import com.ntnn.booking.model.TransactionRequest;
import com.ntnn.booking.service.BookingService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookingInfoEntity>> findAll(@RequestParam("page") int page, @RequestParam("offset") int offset) {
        return new ResponseEntity<>(bookingService.findAll(page, offset), HttpStatus.OK);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> save(@RequestBody BookingRequest request) {
        return new ResponseEntity<>(bookingService.saveBooking(request), HttpStatus.CREATED);
    }

    @PostMapping(path = "/{bookingId}/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookingInfoEntity> createTransaction(@PathVariable("bookingId") int bookingId, @RequestBody TransactionRequest request) {
        return new ResponseEntity<>(bookingService.createTransaction(request), HttpStatus.CREATED);
    }
}
