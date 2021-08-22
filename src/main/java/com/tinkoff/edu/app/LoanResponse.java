package com.tinkoff.edu.app;

/**
 * Response from the system
 */
public class LoanResponse {
    private final LoanResponseType type;

    public LoanResponse(LoanResponseType type) {
        this.type = type;
    }

    public String toString(){
        return "Response to a loan request: {" + this.type + "}";
    }

}
