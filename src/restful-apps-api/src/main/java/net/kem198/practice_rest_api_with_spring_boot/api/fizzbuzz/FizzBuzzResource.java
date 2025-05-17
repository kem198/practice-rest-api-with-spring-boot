package net.kem198.practice_rest_api_with_spring_boot.api.fizzbuzz;

import java.io.Serializable;

public class FizzBuzzResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
