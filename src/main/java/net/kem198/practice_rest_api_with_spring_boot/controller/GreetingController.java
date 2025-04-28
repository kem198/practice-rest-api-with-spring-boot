package net.kem198.practice_rest_api_with_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.kem198.practice_rest_api_with_spring_boot.dto.GreetingDto;
import net.kem198.practice_rest_api_with_spring_boot.service.GreetingService;

@RestController
@RequestMapping("/api/greeting/v1")
public class GreetingController {
    @Autowired
    private GreetingService GreetingService;

    @GetMapping("/hello")
    public GreetingDto getContent(@RequestParam(value = "name", defaultValue = "World") String name) {
        GreetingDto greetingDto = GreetingService.processGreeting(name);

        return greetingDto;
    }
}
