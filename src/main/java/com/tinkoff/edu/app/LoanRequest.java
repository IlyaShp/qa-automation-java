package com.tinkoff.edu.app;

import org.apache.maven.surefire.shade.org.apache.commons.lang3.ObjectUtils;

/**
 * Class, Type -> objects, instances
 */
public class LoanRequest {
    private LoanRequestType type;
    private int months; //stateful + immutable
    private int amount;

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
