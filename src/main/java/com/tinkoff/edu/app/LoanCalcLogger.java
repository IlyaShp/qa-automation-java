package com.tinkoff.edu.app;

public class LoanCalcLogger{
    public static void log(LoanRequest request) {
        System.out.println("calling request...");
    }
    public static void log(LoanResponse response) {
        System.out.println("calling response...");
    }
}
