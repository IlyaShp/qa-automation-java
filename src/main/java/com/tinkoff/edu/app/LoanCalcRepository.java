package com.tinkoff.edu.app;

public class LoanCalcRepository{
    private int requestId;
    private int responseId;
    /**
     * TODO persists request and response
     * @return Request Id and Request Id
     */
    public int saveRequest(LoanRequest request) {
        return ++requestId;
    }
    public int saveResponse(LoanResponse response){
        return ++responseId;
    }
}
