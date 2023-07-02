package com.ntnn.booking.service;

import com.ntnn.booking.entity.TransactionDetailsEntity;
import com.ntnn.booking.helper.ConvertDataHelper;
import com.ntnn.booking.model.TransactionRequest;
import com.ntnn.booking.repository.TransactionDetailsRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionDetailsRepository transactionDetailsRepository;

    public TransactionDetailsEntity findById(Integer id) {
        Optional<TransactionDetailsEntity> entity = transactionDetailsRepository.findById(id);
        return entity.orElse(null);
    }

    public Integer saveTransaction(TransactionRequest transactionRequest) {
        TransactionDetailsEntity transactionDetailsEntity = transactionDetailsRepository.save(ConvertDataHelper.convertDataHelper(transactionRequest));
        return transactionDetailsEntity.getTransactionId();
    }
}
