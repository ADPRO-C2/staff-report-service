package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.Listing;
import com.example.secondtreasurebe.model.TopUpStatus;
import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.repository.ReportedListingRepository;
import com.example.secondtreasurebe.repository.TopUpTransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReportedListingServiceTest {
    @InjectMocks
    ReportedListingService reportedListingService;
    @Mock
    ReportedListingRepository reportedListingRepository;

    List<Listing> listings = new ArrayList<>();
    Listing listing = new Listing();
    Listing listing2 = new Listing();

    @BeforeEach
    void setUp() {
        this.listing.setListingId("123456789");
        this.listing.setUserId(123456789);
        this.listing.setName("Dimas");
        this.listing.setDescription("sebuah deskripsi");
        this.listing.setPrice(new BigDecimal("123456789"));
        this.listing.setStock(123456789);
        this.listing.setPhotoUrl("https://thisisphotourl.com");
        this.listing.setRateCondition(0);

        this.listing2.setListingId("222");
        this.listing2.setUserId(123456789);
        this.listing2.setName("Dimas");
        this.listing2.setDescription("sebuah deskripsi");
        this.listing2.setPrice(new BigDecimal("123456789"));
        this.listing2.setStock(123456789);
        this.listing2.setPhotoUrl("https://thisisphotourl.com");
        this.listing2.setRateCondition(0);

        listings.add(listing);
        listings.add(listing2);
    }

    @AfterEach
    void removeAllListings() {
        listings.clear();
    }

    @Test
    void testGetAllListings() {
        Mockito.when(reportedListingRepository.findAll()).thenReturn(listings);
        ResponseEntity<List<Listing>> response = reportedListingService.getAllReportedListing();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("123456789", response.getBody().get(0).getListingId());
        assertEquals("222", response.getBody().get(1).getListingId());
    }

    @Test
    void testAddReportedListing() {
        Mockito.when(reportedListingRepository.existsById(listing.getListingId())).thenReturn(true);
        Mockito.when(reportedListingRepository.findById(listing.getListingId()).get()).thenReturn(listing);
        Mockito.when(reportedListingRepository.save(listing)).thenReturn(listing);
        ResponseEntity<String> response = reportedListingService.addReportedListing(listing.getListingId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testIgnoreReportedListing() {
        Mockito.when(reportedListingRepository.existsById(listing.getListingId())).thenReturn(true);
        ResponseEntity<String> response = reportedListingService.ignoreReportedListing(listing.getListingId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testRemoveReportedListing() {
        Mockito.when(reportedListingRepository.existsById(listing.getListingId())).thenReturn(true);
        ResponseEntity<String> response = reportedListingService.removeListing(listing.getListingId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
