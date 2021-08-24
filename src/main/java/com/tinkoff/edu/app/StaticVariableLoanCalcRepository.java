package com.tinkoff.edu.app;

public class StaticVariableLoanCalcRepository implements LoanCalcRepository {
    private int requestId;
    private int responseId;

    /**
     * TODO persists request and response
     * @return Request Id and Request Id
     */
    @Override
    public int saveRequest(LoanRequest request) {
        return ++requestId;
    }
    @Override
    public int saveResponse(LoanResponse response){
        return ++responseId;
    }
}
