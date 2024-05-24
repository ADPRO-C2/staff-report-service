package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.service.ReportedListingService;
import com.example.secondtreasurebe.service.TopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> getAllTopUps() {
        return topUpService.getAllTopUps();
    }

    @PostMapping("top-up-transaction/accept/{id}")
    @ResponseBody
    public ResponseEntity<Object> acceptTopUp(@PathVariable("id") String id) {
        return topUpService.accept(id);
    }

    @PostMapping("top-up-transaction/reject/{id}")
    @ResponseBody
    public ResponseEntity<Object> rejectTopUp(@PathVariable("id") String id) {
        return topUpService.reject(id);
    }


    @GetMapping("reported-listing")
    @ResponseBody
    public ResponseEntity<Object> getAllReportedListings() {
        return reportedListingService.getAllReportedListing();
    }

    @PostMapping("reported-listing/add/{id}")
    @ResponseBody
    public ResponseEntity<Object> addReportedListing(@PathVariable("id") String id) {
        return reportedListingService.addReportedListing(id);
    }

    @DeleteMapping("reported-listing/ignore/{id}")
    @ResponseBody
    public ResponseEntity<Object> ignoreReportedListing(@PathVariable("id") String id) {
        return reportedListingService.ignoreReportedListing(id);
    }

    @DeleteMapping("reported-listing/remove/{id}")
    @ResponseBody
    public ResponseEntity<Object> removeListing(@PathVariable("id") String id) {
        return reportedListingService.removeListing(id);
    }
}