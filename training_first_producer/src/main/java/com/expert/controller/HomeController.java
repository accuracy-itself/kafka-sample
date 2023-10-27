package com.expert.controller;

import com.expert.model.Message;
import com.expert.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class HomeController {
    @Autowired
    private ProducerService producerService;

    @GetMapping("/generate")
    public String generate(@RequestParam String message,
                           @RequestParam Integer age,
                           @RequestParam String key) throws ExecutionException, InterruptedException {
        producerService.produce(new Message(message, age), key);
        return "OK";
    }
}