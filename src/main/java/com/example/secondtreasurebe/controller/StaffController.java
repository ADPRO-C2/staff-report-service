package com.example.secondtreasurebe.controller;

import com.example.secondtreasurebe.model.TopUp;
import com.example.secondtreasurebe.service.TopUpService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("")
public class StaffController {
    @Autowired
    private TopUpService topUpService;

    @GetMapping("")
    public String getAllTopUps(Model model) {
        List<TopUp> topUps = topUpService.getAll();
        model.addAttribute("topUps", topUps);
        return "getAllTopUps";
    }
}