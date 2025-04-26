package net.kem198.practice_rest_api_with_spring_boot.utils;

public class FizzBuzzUtils {
    public static String convert(int num) {
        if (num % 3 == 0 && num % 5 == 0) {
            return "FizzBuzz";
        }
        if (num % 3 == 0) {
            return "Fizz";
        }
        if (num % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(num);
    }
}
