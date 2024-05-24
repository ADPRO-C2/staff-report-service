package com.example.secondtreasurebe.functional;

import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.repository.TopUpTransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StaffControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private TopUpTransactionRepository topUpTransactionRepository;

    void getAllTopUpTransactions() throws Exception {
        ResultActions response = mockMvc.perform(//
                get("/staff/top-up-transaction")
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    void acceptTopUpTransaction() throws Exception {
        UUID paymentMethodId = UUID.randomUUID();
        TopUpTransaction topUpTransaction = new TopUpTransaction();
        String id = topUpTransaction.getId().toString();

        topUpTransaction.setUserId(123456789);
        topUpTransaction.setAmount(new BigDecimal("123456789123456789.123456789123456789"));
        topUpTransaction.setPaymentMethodId(paymentMethodId);
        topUpTransaction.setStatus(TopUpStatus.PENDING);

        topUpTransactionRepository.save(topUpTransaction);

        ResultActions response = mockMvc.perform(//
                get("/staff/top-up-transaction/accept/{id}", id)
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));

        topUpTransactionRepository.deleteById(topUpTransaction.getId());
    }

    void rejectTopUpTransaction() throws Exception {
        UUID paymentMethodId = UUID.randomUUID();
        TopUpTransaction topUpTransaction = new TopUpTransaction();
        String id = topUpTransaction.getId().toString();

        topUpTransaction.setUserId(123456789);
        topUpTransaction.setAmount(new BigDecimal("123456789123456789.123456789123456789"));
        topUpTransaction.setPaymentMethodId(paymentMethodId);
        topUpTransaction.setStatus(TopUpStatus.PENDING);

        topUpTransactionRepository.save(topUpTransaction);

        ResultActions response = mockMvc.perform(//
                get("/staff/top-up-transaction/reject/{id}", id)
        );

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));

        topUpTransactionRepository.deleteById(topUpTransaction.getId());
    }
}
