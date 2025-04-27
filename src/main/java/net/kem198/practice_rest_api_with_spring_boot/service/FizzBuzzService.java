package net.kem198.practice_rest_api_with_spring_boot.service;

import org.springframework.stereotype.Service;

import net.kem198.practice_rest_api_with_spring_boot.utils.FizzBuzzUtils;

@Service
public class FizzBuzzService {
    public String processFizzBuzz(int number) {
        return FizzBuzzUtils.convert(number);
    }
}
