package com.andressa.flights.controller;

import com.andressa.flights.model.FlightInfo;
import com.andressa.flights.service.FlightInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final FlightInfoService service;

    private final String appName;

    @Autowired
    public HomeController(FlightInfoService service,
                          @Value("${spring.application.name}") String appName) {
        this.service = service;
        this.appName = appName;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    @GetMapping("/flights")
    public String getAllFlights(Model model) {
        model.addAttribute("flights", service.getAllFlights());
        return "flights";
    }

    @GetMapping("/flight/add")
    public String addFlights(Model model) {
        model.addAttribute("flight", new FlightInfo());
        return "flightform";
    }

}
