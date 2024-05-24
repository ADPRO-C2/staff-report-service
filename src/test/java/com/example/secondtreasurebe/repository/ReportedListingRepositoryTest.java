package com.example.secondtreasurebe.repository;

import com.example.secondtreasurebe.model.Listing;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ReportedListingRepositoryTest {
    @Autowired
    private ReportedListingRepository reportedListingRepository;

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
        assertEquals(0, reportedListingRepository.findAll().size());
        this.reportedListingRepository.save(listing);
        assertEquals(1, reportedListingRepository.findAll().size());
        assertTrue(reportedListingRepository.existsById("123456789"));
        this.reportedListingRepository.deleteById(listing.getListingId());
    }

    void removeListingTest(){
        this.reportedListingRepository.save(listing);
        assertEquals(1, reportedListingRepository.findAll().size());
        this.reportedListingRepository.deleteById(listing.getListingId());
        assertEquals(0, reportedListingRepository.findAll().size());
    }
}
