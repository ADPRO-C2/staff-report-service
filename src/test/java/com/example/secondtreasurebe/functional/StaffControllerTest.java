package com.example.secondtreasurebe.functional;

import com.example.secondtreasurebe.controller.StaffController;
import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.repository.TopUpTransactionRepository;
import com.example.secondtreasurebe.service.TopUpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.transaction.Transaction;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class StaffControllerTest {
    @InjectMocks
    private StaffController staffController;
    @Mock
    private TopUpService topUpService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTopUpTransactions() throws Exception {
        UUID paymentMethodId = UUID.randomUUID();
        UUID id = UUID.randomUUID();

        TopUpTransaction topUpTransaction = new TopUpTransaction();
        topUpTransaction.setId(id);
        topUpTransaction.setUserId(123456789);
        topUpTransaction.setAmount(new BigDecimal("123456789123456789.123456789123456789"));
        topUpTransaction.setPaymentMethodId(paymentMethodId);
        topUpTransaction.setStatus(TopUpStatus.PENDING);
        List<TopUpTransaction> topUpTransactions = new ArrayList<>();
        topUpTransactions.add(topUpTransaction);
        ResponseEntity<List<TopUpTransaction>> returnValue = new ResponseEntity<>(topUpTransactions, HttpStatus.OK);

        Mockito.when(topUpService.getAllTopUps()).thenReturn(returnValue);
        ResponseEntity<List<TopUpTransaction>> response = staffController.getAllTopUps();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void acceptTopUpTransaction() throws Exception {
        UUID paymentMethodId = UUID.randomUUID();
        UUID id = UUID.randomUUID();
        TopUpTransaction topUpTransaction = new TopUpTransaction();
        topUpTransaction.setId(id);
        topUpTransaction.setUserId(123456789);
        topUpTransaction.setAmount(new BigDecimal("123456789123456789.123456789123456789"));
        topUpTransaction.setPaymentMethodId(paymentMethodId);
        topUpTransaction.setStatus(TopUpStatus.APPROVED);
        ResponseEntity<String> returnValue = new ResponseEntity<>("TopUp has been accepted", HttpStatus.OK);

        Mockito.when(topUpService.approve(id.toString())).thenReturn(returnValue);
        ResponseEntity<String> response = staffController.acceptTopUp(id.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void rejectTopUpTransaction() throws Exception {
        UUID paymentMethodId = UUID.randomUUID();
        UUID id = UUID.randomUUID();
        TopUpTransaction topUpTransaction = new TopUpTransaction();
        topUpTransaction.setId(id);
        topUpTransaction.setUserId(123456789);
        topUpTransaction.setAmount(new BigDecimal("123456789123456789.123456789123456789"));
        topUpTransaction.setPaymentMethodId(paymentMethodId);
        topUpTransaction.setStatus(TopUpStatus.APPROVED);
        ResponseEntity<String> returnValue = new ResponseEntity<>("TopUp has been rejected", HttpStatus.OK);

        Mockito.when(topUpService.reject(id.toString())).thenReturn(returnValue);
        ResponseEntity<String> response = staffController.rejectTopUp(id.toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
