package com.tinkoff.edu.app;

public class LoanCalcService{
    /**
     * TODO Loan calculation
     */
    public int createRequest(LoanRequest request){
        LoanCalcRepository loanCalcRepository = new LoanCalcRepository();
        return loanCalcRepository.saveRequest(request);
    }
    public int createResponse(LoanResponse response){
        LoanCalcRepository loanCalcRepository = new LoanCalcRepository();
        return loanCalcRepository.saveResponse(response);
    }
}
