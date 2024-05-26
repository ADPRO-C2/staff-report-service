package com.example.secondtreasurebe.service;


import com.example.secondtreasurebe.model.Listing;
import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.repository.ReportedListingRepository;
import com.example.secondtreasurebe.repository.TopUpTransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TopUpTransactionServiceTest {
    @InjectMocks
    TopUpService topUpService;
    @Mock
    TopUpTransactionRepository topUpTransactionRepository;

    List<TopUpTransaction> topUpTransactions = new ArrayList<>();
    UUID paymentMethodId = UUID.randomUUID();
    UUID id = UUID.randomUUID();
    UUID paymentMethodId2 = UUID.randomUUID();
    UUID id2 = UUID.randomUUID();
    TopUpTransaction topUpTransaction = new TopUpTransaction();
    TopUpTransaction topUpTransaction2 = new TopUpTransaction();

    @BeforeEach
    void setUp() {
        topUpTransaction.setId(id);
        topUpTransaction.setUserId(123456789);
        topUpTransaction.setAmount(new BigDecimal("123456789123456789.123456789123456789"));
        topUpTransaction.setPaymentMethodId(paymentMethodId);
        topUpTransaction.setStatus(TopUpStatus.PENDING);

        topUpTransaction2.setId(id2);
        topUpTransaction2.setUserId(123456789);
        topUpTransaction2.setAmount(new BigDecimal("123456789123456789.123456789123456789"));
        topUpTransaction2.setPaymentMethodId(paymentMethodId2);
        topUpTransaction2.setStatus(TopUpStatus.PENDING);

        topUpTransactions.add(topUpTransaction);
        topUpTransactions.add(topUpTransaction2);
    }

    @AfterEach
    void removeAllTopUps() {
        topUpTransactions.clear();
    }

    @Test
    void testGetAllTopUpTransactions() {
        Mockito.when(topUpTransactionRepository.findAll()).thenReturn(topUpTransactions);
        ResponseEntity<List<TopUpTransaction>> response = topUpService.getAllTopUps();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals(id.toString(), response.getBody().get(0).getId().toString());
        assertEquals(id2.toString(), response.getBody().get(1).getId().toString());
    }

    @Test
    void testApproveTopUpTransaction() {
        Mockito.when(topUpTransactionRepository.existsById(id)).thenReturn(true);
        Mockito.when(topUpTransactionRepository.findById(id).get()).thenReturn(topUpTransaction);
        Mockito.when(topUpTransactionRepository.save(topUpTransaction)).thenReturn(topUpTransaction2);
        ResponseEntity<String> response = topUpService.approve(id.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRejectTopUpTransaction() {
        Mockito.when(topUpTransactionRepository.existsById(id)).thenReturn(true);
        Mockito.when(topUpTransactionRepository.findById(id).get()).thenReturn(topUpTransaction);
        Mockito.when(topUpTransactionRepository.save(topUpTransaction)).thenReturn(topUpTransaction2);
        ResponseEntity<String> response = topUpService.reject(id.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
