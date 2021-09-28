package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanResponse;

public interface LoanCalcRepository {
  Object save(LoanResponse request);
}
