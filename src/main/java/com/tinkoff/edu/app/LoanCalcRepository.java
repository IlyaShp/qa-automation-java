package com.tinkoff.edu.app;

public interface LoanCalcRepository {
    int MODE = 0;
    int saveRequest(LoanRequest request);
    int saveResponse(LoanResponse response);
}
