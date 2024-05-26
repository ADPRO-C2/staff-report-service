package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.Listing;
import com.example.secondtreasurebe.repository.ReportedListingRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ReportedListingServiceTest {
    @InjectMocks
    ReportedListingService reportedListingService;
    @Mock
    ReportedListingRepository reportedListingRepository;
    @Mock
    RestTemplate restTemplate = new RestTemplate();

    List<Listing> listings = new ArrayList<>();
    Listing listing = new Listing();
    Listing listing2 = new Listing();

    @BeforeEach
    void setUp() {
        listing.setListingId("123456789");
        listing.setUserId(123456789);
        listing.setName("Dimas");
        listing.setDescription("sebuah deskripsi");
        listing.setPrice(new BigDecimal("123456789"));
        listing.setStock(123456789);
        listing.setPhotoUrl("https://thisisphotourl.com");
        listing.setRateCondition(0);

        listing2.setListingId("222");
        listing2.setUserId(123456789);
        listing2.setName("Dimas");
        listing2.setDescription("sebuah deskripsi");
        listing2.setPrice(new BigDecimal("123456789"));
        listing2.setStock(123456789);
        listing2.setPhotoUrl("https://thisisphotourl.com");
        listing2.setRateCondition(0);

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
        ResponseEntity<String> response = reportedListingService.addReportedListing(listing.getListingId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testIgnoreReportedListing() {
        ResponseEntity<String> response = reportedListingService.ignoreReportedListing(listing.getListingId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
//
    @Test
    void testRemoveReportedListing() throws IOException {
        ResponseEntity<String> response = reportedListingService.removeListing(listing.getListingId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
