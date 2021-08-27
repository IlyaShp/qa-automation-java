package com.tinkoff.edu.app;

public class OriginalLoanCalcService implements LoanCalcService {
    /**
     * A a = new B();
     * a.m();
     */
    private final LoanCalcRepository loanCalcRepository;

    /**
     * Constructor DI
     *
     * @param loanCalcRepository
     */
    public OriginalLoanCalcService(LoanCalcRepository loanCalcRepository) {
        this.loanCalcRepository = loanCalcRepository;
    }

    /**
     * TODO Loan calculation
     *
     * @return
     */
    @Override
    public int createRequest(LoanRequest request) {
        return loanCalcRepository.saveRequest(request);
    }

    @Override
    public int createResponse(LoanResponse response) {
        return loanCalcRepository.saveResponse(response);
    }
}
