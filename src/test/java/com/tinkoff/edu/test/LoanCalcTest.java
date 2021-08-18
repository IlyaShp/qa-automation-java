package com.tinkoff.edu.test;

import com.tinkoff.edu.app.LoanRequest;
import com.tinkoff.edu.app.LoanType;

import com.tinkoff.edu.app.LoanCalcController;

/**
 * Loan Calc Tests
 */

public class LoanCalcTest{
    public static void main(String... args) {
        LoanCalcController loanCalcController = new LoanCalcController();
        LoanRequest request = new LoanRequest(LoanType.IP, 10,1_000);
        int requestId = loanCalcController.createRequest(request);    //factual
        System.out.println(request);
        System.out.println(requestId + " must be 1");
    }
}