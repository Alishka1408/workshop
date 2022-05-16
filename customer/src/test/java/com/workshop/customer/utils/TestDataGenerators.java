package com.workshop.customer.utils;

import java.util.Random;

public class TestDataGenerators {
    public static String getRandomDigits(int var) {
        Random randNumber = new Random();
        return String.valueOf(randNumber.nextInt(var) + 1);
    }
}