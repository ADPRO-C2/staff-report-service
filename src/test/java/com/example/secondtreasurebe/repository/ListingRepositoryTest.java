package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.Listing;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ListingRepositoryTest {
    @Autowired
    private ListingRepository listingRepository;

    private static Listing listing;

    @BeforeAll
    static void setUp(){
        listing = new Listing();
        listing.setListingId("123456789");
        listing.setUserId(123456789);
        listing.setName("Dimas");
        listing.setDescription("sebuah deskripsi");
        listing.setPrice(123456789);
        listing.setStock(123456789);
        listing.setPhotoUrl("https://thisisphotourl.com");
        listing.setRateCondition(0);
    }

    void findListingTest(){
        assertEquals(0, listingRepository.findAll().size());
        this.listingRepository.save(listing);
        assertEquals(1, listingRepository.findAll().size());
        assertTrue(listingRepository.existsById("123456789"));
        this.listingRepository.deleteById(listing.getListingId());
    }

    void removeListingTest(){
        this.listingRepository.save(listing);
        assertEquals(1, listingRepository.findAll().size());
        this.listingRepository.deleteById(listing.getListingId());
        assertEquals(0, listingRepository.findAll().size());
    }
}
