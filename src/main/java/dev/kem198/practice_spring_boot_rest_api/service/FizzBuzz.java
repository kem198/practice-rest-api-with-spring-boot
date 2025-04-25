package dev.kem198.practice_spring_boot_rest_api.service;

public class FizzBuzz {

    public String convert(int num) {
        if (num == 3) {
            return "Fizz";
        }
        return String.valueOf(num);
    }

}
