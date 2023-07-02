package com.ntnn.booking.controller;

import com.ntnn.booking.entity.TransactionDetailsEntity;
import com.ntnn.booking.model.TransactionRequest;
import com.ntnn.booking.service.TransactionService;
import java.util.concurrent.Callable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class PaymentsController {
    private final TransactionService transactionService;

    @GetMapping(path = "/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Callable<ResponseEntity<TransactionDetailsEntity>> getPayments(@PathVariable("transactionId") int transactionId) {
        return () -> ResponseEntity.ok().body(transactionService.findById(transactionId));
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> savePayments(@RequestBody TransactionRequest transactionRequest) {
        return new ResponseEntity<>(transactionService.saveTransaction(transactionRequest), HttpStatus.CREATED);
    }
}
