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
@RequestMapping("staff/")
public class StaffController {
    @Autowired
    private TopUpService topUpService;

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

    @GetMapping("topup")
    @ResponseBody
    public ResponseEntity<Object> getAllTOpUps() {
        return topUpService.getAllUnspecifiedTopUps();
    }

    @PostMapping("topup/accept/{id}")
    @ResponseBody
    public ResponseEntity<Object> acceptTopUp(@PathVariable("id") int id) {
        return topUpService.accept(id);
    }

    @PostMapping("topup/reject/{id}")
    @ResponseBody
    public ResponseEntity<Object> rejectTopUp(@PathVariable("id") int id) {
        return topUpService.reject(id);
    }
}