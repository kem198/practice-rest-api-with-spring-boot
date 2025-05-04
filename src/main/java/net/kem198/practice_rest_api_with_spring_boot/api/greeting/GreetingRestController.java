package net.kem198.practice_rest_api_with_spring_boot.api.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.kem198.practice_rest_api_with_spring_boot.domain.service.greeting.GreetingService;

@RestController
@RequestMapping("/api/greeting/v1")
public class GreetingRestController {
    @Autowired
    private GreetingService GreetingService;

    @GetMapping("/hello")
    public GreetingResource getContent(@RequestParam(value = "name", defaultValue = "World") String name) {
        GreetingResource greetingResource = GreetingService.processGreeting(name);

        return greetingResource;
    }
}
