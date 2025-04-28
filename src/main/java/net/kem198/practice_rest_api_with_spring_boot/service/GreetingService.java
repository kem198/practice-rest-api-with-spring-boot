package net.kem198.practice_rest_api_with_spring_boot.service;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String processGreeting(int number) {
        return "{\"id\":1,\"content\":\"Hello, World!\"}";
    }
}
