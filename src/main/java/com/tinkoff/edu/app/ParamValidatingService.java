package com.tinkoff.edu.app;

public abstract class ParamValidatingService {
    int prop;
    public int createRequest(LoanRequest request) {
        if (request == null) throw new IllegalArgumentException();
        return 0;
    }
    public int createResponse(LoanResponse response) {
        if (response == null) throw new IllegalArgumentException();
        return 0;
    }
}
