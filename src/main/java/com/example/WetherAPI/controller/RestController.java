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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/weather")
public class RestController {

    @Autowired
    WebServiceClientImlp webServiceClientImlp;

    @GetMapping("/home")
    private String getHomePage(Model model){
        List<String> cities= Arrays.asList("Delhi", "Mumbai", "Kolkata", "Chennai", "Bengaluru", "Hyderabad","Ahmedabad","Pune");
        model.addAttribute("cities",cities);
        return "home-page";
    }
    @PostMapping("/api")
    private String getWhether(@RequestParam("selectedCity") String city,Model model) throws Exception {
        WeatherData weather=webServiceClientImlp.getWhether(city);
        System.out.println(weather.getTemp());

        SimpleDateFormat dateFormat=new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date=new Date();
        String dateString = dateFormat.format(date);

        model.addAttribute("dateString",dateString);
        model.addAttribute("city",city);
        model.addAttribute("weather",weather);

        return "dash-board";
    }

}
