package com.example.secondtreasurebe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TopUpTransactionTest {
    TopUpTransaction topUpTransaction;
    UUID paymentMethodId = UUID.randomUUID();
    UUID id = UUID.randomUUID();


    @BeforeEach
    void setUp(){
        this.topUpTransaction = new TopUpTransaction();
        this.topUpTransaction.setId(id);
        this.topUpTransaction.setUserId(123456789);
        this.topUpTransaction.setAmount(new BigDecimal("123456789123456789.123456789123456789"));
        this.topUpTransaction.setPaymentMethodId(paymentMethodId);
        this.topUpTransaction.setStatus(TopUpStatus.PENDING);
    }

    @Test
    void testIdNotNull(){
        assertNotNull(this.topUpTransaction.getId());
    }
    @Test
    void testGetUserId(){
        assertEquals(123456789, this.topUpTransaction.getUserId());
    }
    @Test
    void testGetAmount(){
        assertEquals(new BigDecimal("123456789123456789.123456789123456789").toString(), this.topUpTransaction.getAmount().toString());
    }
    @Test
    void testGetPaymentMethodId(){
        assertEquals(paymentMethodId.toString(), this.topUpTransaction.getPaymentMethodId().toString());
    }
    @Test
    void testGetStatus(){
        assertEquals(TopUpStatus.PENDING, this.topUpTransaction.getStatus());
    }
}
