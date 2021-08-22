package com.tinkoff.edu.test;

import com.tinkoff.edu.app.*;

/**
 * Loan Calc Tests
 */

public class LoanCalcTest{
    public static void main(String... args) {
        LoanCalcController loanCalcController = new LoanCalcController(new OriginalLoanCalcService(new StaticVariableLoanCalcRepository()));
        LoanRequest request = new LoanRequest(LoanRequestType.IP, 10,1_000);
        LoanResponse response = new LoanResponse(LoanResponseType.APPROVED);
        int requestId = loanCalcController.createRequest(request);
        int responseId = loanCalcController.createResponse(response);
        System.out.println(request);
        System.out.println(response);
        System.out.println(requestId + " must be 1");
        System.out.println(responseId + " must be 1");
    }
}