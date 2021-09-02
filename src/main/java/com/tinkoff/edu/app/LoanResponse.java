package com.tinkoff.edu.app;

import java.util.Objects;

/**
 * Response from the system
 */
public class LoanResponse {
    private LoanResponseType responseType;
    private int requestId;

    public LoanResponse(LoanResponseType responseType, int requestId) {
        this.responseType = responseType;
        this.requestId = requestId;
    }

    public LoanResponseType getResponseType() {
        return responseType;
    }

    public int getRequestId() {
        return requestId;
    }

    public String toString() {
        return "Response to a loan request: {" + this.responseType + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanResponse that = (LoanResponse) o;
        return getRequestId() == that.getRequestId() && getResponseType() == that.getResponseType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResponseType(), getRequestId());
    }
}
