package com.ntnn.booking.controller;

import java.util.concurrent.Callable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/booking")
public class ServiceController {
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Callable<ResponseEntity<String>> getPayments() {
        return () -> ResponseEntity.ok().body("Hello world at booking service");
    }
}
