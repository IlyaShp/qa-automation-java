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
        boolean exceptionForRequest = request == null || request.getAmount() <= 0 || request.getMonths() <= 0;
        boolean personAmountLessOneThousand = request.getType() == LoanRequestType.PERSON && request.getAmount() < 1_000;
        boolean personLessTenLessTwelve = request.getType() == LoanRequestType.PERSON && request.getAmount() <= 10_000 && request.getMonths() <= 12;
        boolean oooMoreTenLessTwelve = request.getType() == LoanRequestType.OOO && request.getAmount() > 10_000 && request.getMonths() < 12;
        boolean personMoreTenMoreTwelve = request.getType() == LoanRequestType.PERSON && request.getAmount() > 10_000 && request.getMonths() > 12;
        boolean oooLessTen = request.getType() == LoanRequestType.OOO && request.getAmount() <= 10_000;
        boolean oooMoreTenMoreTwelve = request.getType() == LoanRequestType.OOO && request.getAmount() > 10_000 && request.getMonths() >= 12;
        boolean ipType = request.getType() == LoanRequestType.IP;

        if (exceptionForRequest) throw new IllegalArgumentException();
        if (personAmountLessOneThousand)
            return new LoanResponse(LoanResponseType.DENIED, loanCalcService.createRequest(request));

        if (personLessTenLessTwelve || oooMoreTenLessTwelve) {
            return new LoanResponse(LoanResponseType.APPROVED, loanCalcService.createRequest(request));
        } else if (personMoreTenMoreTwelve || oooLessTen || oooMoreTenMoreTwelve || ipType
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
