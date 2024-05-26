package com.example.secondtreasurebe.service;


import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TopUpTransactionServiceTest {
    @InjectMocks
    TopUpService topUpService;

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
        ResponseEntity<List<TopUpTransaction>> response = topUpService.getAllTopUps();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testApproveInvalidTopUpTransaction() throws IOException {
        ResponseEntity<String> response = topUpService.approve(id.toString());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testRejectInvalidTopUpTransaction() throws JsonProcessingException {
        ResponseEntity<String> response = topUpService.reject(id.toString());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
