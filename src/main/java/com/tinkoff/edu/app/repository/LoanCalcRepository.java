package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanResponse;

public interface LoanCalcRepository {

  LoanResponse[] save(LoanResponse response);
}
