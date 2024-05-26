package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.model.Listing;
import com.example.secondtreasurebe.model.TopUpTransaction;
import com.example.secondtreasurebe.service.ReportedListingService;
import com.example.secondtreasurebe.service.TopUpService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("staff/")
public class StaffController {
    @Autowired
    private TopUpService topUpService;
    @Autowired
    private ReportedListingService reportedListingService;

//    @GetMapping("")
//    public String loginPage(Model model) {
//        LoginDTO loginDTO = new LoginDTO();
//        model.addAttribute("loginDTO", loginDTO);
//        return "login page";
//    }
//
//    @PostMapping("")
//    public String authenticationProcessor(@ModelAttribute LoginDTO loginDTO, Model model){
//        if (personService.existsByLoginDTO(loginDTO)){
//            int id = personService.getIdByLoginDTO(loginDTO);
//            return String.format("redirect:%d", id);
//        }
//        return "redirect:";
//    }

    @GetMapping("top-up-transaction")
    @ResponseBody
    public ResponseEntity<List<TopUpTransaction>> getAllTopUps() {
        return topUpService.getAllTopUps();
    }

    @PostMapping("top-up-transaction/accept/{id}")
    @ResponseBody
    public ResponseEntity<String> acceptTopUp(@PathVariable("id") String id) throws IOException {
        return topUpService.approve(id);
    }

    @PostMapping("top-up-transaction/reject/{id}")
    @ResponseBody
    public ResponseEntity<String> rejectTopUp(@PathVariable("id") String id) throws JsonProcessingException {
        return topUpService.reject(id);
    }


    @GetMapping("reported-listing")
    @ResponseBody
    public ResponseEntity<List<Listing>> getAllReportedListings() {
        return reportedListingService.getAllReportedListing();
    }

    @PostMapping("reported-listing/add/{id}")
    @ResponseBody
    public ResponseEntity<String> addReportedListing(@PathVariable("id") String id) {
        return reportedListingService.addReportedListing(id);
    }

    @DeleteMapping("reported-listing/ignore/{id}")
    @ResponseBody
    public ResponseEntity<String> ignoreReportedListing(@PathVariable("id") String id) {
        return reportedListingService.ignoreReportedListing(id);
    }
//
//    @DeleteMapping("reported-listing/remove/{id}")
//    @ResponseBody
//    public ResponseEntity<String> removeListing(@PathVariable("id") String id) throws IOException {
//        return reportedListingService.removeListing(id);
//    }
}