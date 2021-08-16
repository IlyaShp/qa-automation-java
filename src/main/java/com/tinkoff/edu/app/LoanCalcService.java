package com.tinkoff.edu.app;

public class LoanCalcService{
    public static int createRequest() {
        return LoanCalcRepository.save();
    }
}
