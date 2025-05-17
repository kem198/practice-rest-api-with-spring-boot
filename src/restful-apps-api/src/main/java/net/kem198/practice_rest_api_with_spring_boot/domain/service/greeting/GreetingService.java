package net.kem198.practice_rest_api_with_spring_boot.domain.service.greeting;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import net.kem198.practice_rest_api_with_spring_boot.api.greeting.GreetingResource;

@Service
public class GreetingService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    public GreetingResource processGreeting(String name) {

        GreetingResource greetingDto = new GreetingResource(counter.incrementAndGet(), String.format(template, name));
        return greetingDto;
    }
}
