package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

public class LoanCalcController {
    /**
     * Validates and logs request and response
     */
    public int createRequest(LoanRequest request) {
        LoanCalcService loanCalcService = new LoanCalcService();
        // param validation
        // log request
        log(request);
        return loanCalcService.createRequest(request);
    }

    public int createResponse(LoanResponse response) {
        LoanCalcService loanCalcService = new LoanCalcService();
        log(response);
        return loanCalcService.createResponse(response);
    }
}
