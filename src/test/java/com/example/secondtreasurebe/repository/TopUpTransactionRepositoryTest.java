package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.Listing;
import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TopUpTransactionRepositoryTest {
    @Autowired
    private TopUpTransactionRepository topUpTransactionRepository;

    private static TopUpTransaction topUpTransaction;
    private static UUID paymentMethodId = UUID.randomUUID();

    @BeforeAll
    static void setUp(){
        topUpTransaction = new TopUpTransaction();
        topUpTransaction.setUserId(123456789);
        topUpTransaction.setAmount(new BigDecimal("123456789123456789.123456789123456789"));
        topUpTransaction.setPaymentMethodId(paymentMethodId);
        topUpTransaction.setStatus(TopUpStatus.PENDING);
    }

    void findTopUpTest(){
        assertEquals(0, topUpTransactionRepository.findAll().size());
        this.topUpTransactionRepository.save(topUpTransaction);
        assertEquals(1, topUpTransactionRepository.findAll().size());
        assertTrue(topUpTransactionRepository.existsById(paymentMethodId));
        this.topUpTransactionRepository.deleteById(paymentMethodId);
    }

    void removeListingTest(){
        this.topUpTransactionRepository.save(topUpTransaction);
        assertEquals(1, topUpTransactionRepository.findAll().size());
        this.topUpTransactionRepository.deleteById(paymentMethodId);
        assertEquals(0, topUpTransactionRepository.findAll().size());
    }
}
