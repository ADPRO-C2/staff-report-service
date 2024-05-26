package com.example.secondtreasurebe.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.antlr.v4.runtime.misc.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListingTest {
    Listing listing;

    @BeforeEach
    void setUp(){
        this.listing = new Listing();
        this.listing.setListingId("123456789");
        this.listing.setUserId(123456789);
        this.listing.setName("Dimas");
        this.listing.setDescription("sebuah deskripsi");
        this.listing.setPrice(new BigDecimal("123456789"));
        this.listing.setStock(123456789);
        this.listing.setPhotoUrl("https://thisisphotourl.com");
        this.listing.setRateCondition(0);
    }

    @Test
    void testGetListingId(){
        assertEquals("123456789", this.listing.getListingId());
    }
    @Test
    void testGetUserId(){
        assertEquals(123456789, this.listing.getUserId());
    }
    @Test
    void testGetName(){
        assertEquals("Dimas", this.listing.getName());
    }
    @Test
    void testGetDescription(){
        assertEquals("sebuah deskripsi", this.listing.getDescription());
    }

    @Test
    void testGetPrice(){
        assertEquals("123456789", this.listing.getPrice().toString());
    }
    @Test
    void testGetStock(){
        assertEquals(123456789, this.listing.getStock());
    }
    @Test
    void testPhotoUrl(){
        assertEquals("https://thisisphotourl.com", this.listing.getPhotoUrl());
    }
    @Test
    void testGetRateCondition(){
        assertEquals(0, this.listing.getRateCondition());
    }

    @Test
    public void testValidateValidValues() {
        listing = new Listing();
        listing.setPrice(BigDecimal.valueOf(100));
        listing.setStock(10);
        listing.setRateCondition(1);
        listing.validate();
    }
}
