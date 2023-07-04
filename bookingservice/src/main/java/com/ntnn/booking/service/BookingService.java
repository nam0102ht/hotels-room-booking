package com.ntnn.booking.service;

import com.ntnn.booking.entity.BookingInfoEntity;
import com.ntnn.booking.exception.TechnicalException;
import com.ntnn.booking.helper.DataConvertHelper;
import com.ntnn.booking.helper.ValidateRequest;
import com.ntnn.booking.model.BookingRequest;
import com.ntnn.booking.model.TransactionRequest;
import com.ntnn.booking.repository.BookingRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RestTemplate restTemplate;
    private final Validator validator;

    @Value("${infrastructure.downstream}")
    private String downstream;

    public List<BookingInfoEntity> findAll(int page, int offset) {
        Pageable pageRequest = PageRequest.of(page, offset);
        return bookingRepository.findAll(pageRequest).getContent();
    }

    public BookingInfoEntity saveBooking(BookingRequest bookingRequest) {
        BookingInfoEntity entity = DataConvertHelper.convertEntity(bookingRequest);
        return bookingRepository.save(entity);
    }

    public BookingInfoEntity createTransaction(TransactionRequest transactionRequest) {
        ValidateRequest.validateTransactionRequest(transactionRequest);
        Optional<BookingInfoEntity> bookingInfo = bookingRepository.findById(transactionRequest.getBookingId());
        if (bookingInfo.isEmpty()) {
            throw new ValidationException("Invalid Booking Id");
        }
        HttpEntity<TransactionRequest> request = new HttpEntity<>(transactionRequest);
        ResponseEntity<Integer> res = restTemplate.postForEntity(downstream, request, Integer.class);
        log.info("Booking confirmed for user with aadhaar number: {} | Here are the booking details: {}", bookingInfo.get().getAadharNumber(), bookingInfo.get().toString());
        if (!res.getStatusCode().is2xxSuccessful()) {
            throw new TechnicalException("Bad request", new Throwable());
        }
        Integer transactionId = res.getBody();
        if (transactionId == null) {
            throw new TechnicalException("Response of Transaction is null", new Throwable("Response of Transaction is null"));
        }
        bookingInfo.get().setTransactionId(transactionId);

        Set<ConstraintViolation<BookingInfoEntity>> entityViolations = validator.validate(bookingInfo.get());
        if (!entityViolations.isEmpty()) {
            String errorMessage = "Invalid " + entityViolations.iterator().next().getMessage();
            throw new ValidationException(errorMessage);
        }

        BookingInfoEntity entity = bookingRepository.save(bookingInfo.get());

        return entity;
    }
}
