package net.kem198.practice_rest_api_with_spring_boot.service;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import net.kem198.practice_rest_api_with_spring_boot.dto.GreetingDto;

@Service
public class GreetingService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    public GreetingDto processGreeting(String name) {

        GreetingDto greetingDto = new GreetingDto(counter.incrementAndGet(), String.format(template, name));
        return greetingDto;
    }
}
