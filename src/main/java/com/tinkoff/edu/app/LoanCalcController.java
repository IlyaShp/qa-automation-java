package com.tinkoff.edu.app;

import static com.tinkoff.edu.app.LoanCalcLogger.log;


public class LoanCalcController {
    private final LoanCalcService loanCalcService; //Creator

    public LoanCalcController(LoanCalcService loanCalcService) {
        this.loanCalcService = loanCalcService;
    }

    /**
     * Validates and logs request and response
     */
    public LoanResponse createRequest(LoanRequest request) {

        if (request == null || request.getAmount() <= 0 || request.getMonths() <= 0)
            throw new IllegalArgumentException();

        if (request.getType() == LoanRequestType.PERSON && request.getAmount() < 1_000)
            return new LoanResponse(LoanResponseType.DENIED, loanCalcService.createRequest(request));

        if ((request.getType() == LoanRequestType.PERSON && request.getAmount() <= 10_000 && request.getMonths() <= 12)
                || (request.getType() == LoanRequestType.OOO && request.getAmount() > 10_000 && request.getMonths() < 12)) {
            return new LoanResponse(LoanResponseType.APPROVED, loanCalcService.createRequest(request));
        } else if ((request.getType() == LoanRequestType.PERSON && request.getAmount() > 10_000 && request.getMonths() > 12) ||
                (request.getType() == LoanRequestType.OOO && request.getAmount() <= 10_000) ||
                (request.getType() == LoanRequestType.OOO && request.getAmount() > 10_000 && request.getMonths() >= 12) ||
                (request.getType() == LoanRequestType.IP)
        ) {
            return new LoanResponse(LoanResponseType.DENIED, loanCalcService.createRequest(request));
        }

        if (request.getMonths() > 12) {
            return new LoanResponse(LoanResponseType.DENIED, loanCalcService.createRequest(request));
        } else {
            return new LoanResponse(LoanResponseType.APPROVED, loanCalcService.createRequest(request));
        }
    }

    public int LoanResponse(LoanResponse response) {
        if (response == null) throw new IllegalArgumentException();

        log(response);
        return loanCalcService.createResponse(response);
    }
}
