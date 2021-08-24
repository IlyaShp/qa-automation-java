package com.tinkoff.edu.app;

/**
 * Class, Type -> objects, instances
 */
public class LoanRequest {
    private final LoanRequestType type;
    private final int months; //stateful + immutable
    private final int amount;

    public LoanRequest(LoanRequestType type, int months, int amount){
        this.type = type;
        this.months = months;
        this.amount = amount;
    }

    public LoanRequestType getType() {
        return type;
    }

    public int getMonths() {
        return months;
    }

    public int getAmount() {
        return amount;
    }

    public String toString() {
        return "RQ: {" + this.type + ", " + this.getAmount() + " for " + this.getMonths() + "}";
    }

}
