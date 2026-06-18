package com.eagleFlight.airlines.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String boasVindas() {
        return "Bem-vindo à Eagle Flight Airlines API! Linha aérea autorizada.";
    }
}
