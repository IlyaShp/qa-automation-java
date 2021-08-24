package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;

public class LoanCalcController {
    private LoanCalcService loanCalcService; //Creator

    public LoanCalcController(LoanCalcService loanCalcService){
        this.loanCalcService = loanCalcService;
    }

    /**
     * Validates and logs request and response
     */
    public int createRequest(LoanRequest request) {
        // param validation
        // log request
        log(request);
        return loanCalcService.createRequest(request);
    }

    public int createResponse(LoanResponse response) {

        log(response);
        return loanCalcService.createResponse(response);
    }
}
