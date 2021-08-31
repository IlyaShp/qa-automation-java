package com.tinkoff.edu.app;

import com.tinkoff.edu.app.*;

public class IpNotFriendlyService extends OriginalLoanCalcService {
    public IpNotFriendlyService(LoanCalcRepository loanCalcRepository) {
        super(loanCalcRepository);
    }

    @Override
    public int createRequest(LoanRequest request) {
        if (request.getType().equals(LoanRequestType.IP))
            return -1;
            return super.createRequest(request);
    }
}