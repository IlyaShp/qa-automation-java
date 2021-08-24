package com.tinkoff.edu.app;

public interface LoanCalcRepository {
    int saveRequest(LoanRequest request);
    int saveResponse(LoanResponse response);
}
