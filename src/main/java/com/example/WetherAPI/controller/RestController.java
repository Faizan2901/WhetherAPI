package com.example.WetherAPI.controller;

import com.example.WetherAPI.service.WebServiceClientImlp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/whether")
public class RestController {

    @Autowired
    WebServiceClientImlp webServiceClientImlp;

    @GetMapping("/api")
    private String getWhether() throws URISyntaxException, IOException {
        webServiceClientImlp.getWhether("London");
        return "<h1>Hello</h1>";
    }

}
