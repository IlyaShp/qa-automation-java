package com.tinkoff.edu.app;

public class LoanCalcRepository{
    private int requestId;
    /**
     * TODO persists request
     * @return Request Id
     */
    public int save(LoanRequest request) {
        return ++requestId;
    }
}
