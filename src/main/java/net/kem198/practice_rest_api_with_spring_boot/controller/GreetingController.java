package net.kem198.practice_rest_api_with_spring_boot.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.kem198.practice_rest_api_with_spring_boot.dto.GreetingDto;

@RestController
@RequestMapping("/api/v1/greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public GreetingDto getContent(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("aaa");
        System.out.println("bbb");
        GreetingDto greetingDto = new GreetingDto(counter.incrementAndGet(), String.format(template, name));

        return greetingDto;
    }
}
