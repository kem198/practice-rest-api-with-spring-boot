package net.kem198.practice_rest_api_with_spring_boot.service;

import net.kem198.practice_rest_api_with_spring_boot.utils.FizzBuzzUtils;

public class FizzBuzzService {
    public String processFizzBuzz(String numberString) {
        return FizzBuzzUtils.convert(Integer.parseInt(numberString));
    }
}
