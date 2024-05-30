package com.example.secondtreasurebe.service;

import com.example.secondtreasurebe.model.Listing;
import com.example.secondtreasurebe.model.ReportedListing;
import com.example.secondtreasurebe.repository.ReportedListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@Service
public class ReportedListingService {
    @Autowired
    private ReportedListingRepository reportedListingRepository;

    public ResponseEntity<List<ReportedListing>> getAllReportedListing() {
        return new ResponseEntity<>(reportedListingRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<String> addReportedListing(String id) {
        String uri = "http://34.142.129.98/api/listing/" + id;
        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        Listing listing;
        try {
            listing = restTemplate.getForObject(uri, Listing.class);
        }
        catch (Exception e) {
            return new ResponseEntity<>("ERROR: Cannot add the listing", HttpStatus.NOT_FOUND);
        }

        ReportedListing reportedListing = new ReportedListing();
        reportedListing.setId("");
        if (reportedListing.getId().isEmpty()) {
            String currentId = UUID.randomUUID().toString();
            reportedListing.setId(currentId);
        }
        reportedListing.setListingId(listing.getListingId());
        reportedListing.setUserId(listing.getUserId());
        reportedListing.setName(listing.getName());
        reportedListing.setDescription(listing.getDescription());
        reportedListing.setPrice(listing.getPrice());
        reportedListing.setStock(listing.getStock());
        reportedListing.setPhotoUrl(listing.getPhotoUrl());
        reportedListing.setRateCondition(listing.getRateCondition());
        reportedListingRepository.save(reportedListing);
        return new ResponseEntity<>("The listing has been added", HttpStatus.OK);
    }

    public ResponseEntity<String> ignoreReportedListing(String id) {
        reportedListingRepository.deleteById(id);
        return new ResponseEntity<>("The listing has been ignored", HttpStatus.OK);
    }

    public ResponseEntity<String> removeListing(String id) throws IOException {
        reportedListingRepository.deleteById(id);
        URL url = new URL(String.format("http://34.142.129.98/api/delete-listing/{}", id));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        return new ResponseEntity<>("The listing has been removed", HttpStatus.OK);
    }
}
