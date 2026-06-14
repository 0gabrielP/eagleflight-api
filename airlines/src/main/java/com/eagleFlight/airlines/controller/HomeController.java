package com.eagleFlight.airlines.controller;

import com.eagleFlight.airlines.repository.passageiroRepository;
import com.eagleFlight.airlines.repository.viagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @RestController
    public class viagemController {

        @Autowired
        private passageiroRepository passageiroRepository;

        @Autowired
        private viagemRepository viagemRepository;

        @GetMapping
        public String boasVindas() {
            return "Bem vindo à API da Flight Eagle Airlines!";
        }
    }
}
