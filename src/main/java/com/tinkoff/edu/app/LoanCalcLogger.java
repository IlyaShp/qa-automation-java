package com.tinkoff.edu.app;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanResponse;

public class LoanCalcLogger{
    public static void log(LoanRequest request) {
        System.out.println("calling request...");
    }
    public static void log(LoanResponse response) {
        System.out.println("calling response...");
    }
}
