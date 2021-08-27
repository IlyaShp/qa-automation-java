package com.tinkoff.edu.app;

public class VariableLoanCalcRepository implements LoanCalcRepository {
    private int requestId;
    private int responseId;

    public VariableLoanCalcRepository(int requestId) {
        this.requestId = requestId;
    }

    public VariableLoanCalcRepository() {
        this(0);
    }

    public int getRequestId() {
        return requestId;
    }

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
