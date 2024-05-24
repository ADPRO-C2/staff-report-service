package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.repository.TopUpTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TopUpService {
    @Autowired
    private TopUpTransactionRepository topUpTransactionRepository;

    public ResponseEntity<Object> getAllTopUps() {
        return new ResponseEntity<>(topUpTransactionRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> approve(String string_id) {
        UUID id = UUID.fromString(string_id);
        if (topUpTransactionRepository.existsById(id)){
            TopUpTransaction topUpTransaction = topUpTransactionRepository.findById(id).get();
            topUpTransaction.setStatus(TopUpStatus.APPROVED);
            topUpTransactionRepository.save(topUpTransaction);
            return new ResponseEntity<>("TopUp has been accepted", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("ERROR: Cannot accept TopUp", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> reject(String string_id) {
        UUID id = UUID.fromString(string_id);
        if (topUpTransactionRepository.existsById(id)){
            TopUpTransaction topUpTransaction = topUpTransactionRepository.findById(id).get();
            topUpTransaction.setStatus(TopUpStatus.REJECTED);
            topUpTransactionRepository.save(topUpTransaction);
            return new ResponseEntity<>("TopUp has been rejected", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("ERROR: Cannot reject TopUp", HttpStatus.NOT_FOUND);
        }
    }
}
