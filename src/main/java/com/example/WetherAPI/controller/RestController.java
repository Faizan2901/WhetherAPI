package com.example.WetherAPI.controller;

import com.example.WetherAPI.bean.WeatherData;
import com.example.WetherAPI.service.WebServiceClientImlp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/whether")
public class RestController {

    @Autowired
    WebServiceClientImlp webServiceClientImlp;

    @GetMapping("/home")
    private String getHomePage(Model model){
        List<String> cities= Arrays.asList("Delhi", "Mumbai", "Kolkata", "Chennai", "Bengaluru", "Hyderabad");
        model.addAttribute("cities",cities);
        return "home-page";
    }
    @PostMapping("/api")
    private String getWhether(@RequestParam("selectedCity") String city) throws Exception {
        WeatherData weather=webServiceClientImlp.getWhether(city);
        System.out.println(weather.getMain().getTemp());
        return "<h1>Hello</h1>";
    }

}
