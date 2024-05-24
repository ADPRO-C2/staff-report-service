package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UnverifiedTopUpTransactionRepositoryTest {
    @Autowired
    private UnverifiedTopUpTransactionRepository unverifiedTopUpTransactionRepository;

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
        assertEquals(0, unverifiedTopUpTransactionRepository.findAll().size());
        this.unverifiedTopUpTransactionRepository.save(topUpTransaction);
        assertEquals(1, unverifiedTopUpTransactionRepository.findAll().size());
        assertTrue(unverifiedTopUpTransactionRepository.existsById(paymentMethodId));
        this.unverifiedTopUpTransactionRepository.deleteById(paymentMethodId);
    }

    void removeListingTest(){
        this.unverifiedTopUpTransactionRepository.save(topUpTransaction);
        assertEquals(1, unverifiedTopUpTransactionRepository.findAll().size());
        this.unverifiedTopUpTransactionRepository.deleteById(paymentMethodId);
        assertEquals(0, unverifiedTopUpTransactionRepository.findAll().size());
    }
}
