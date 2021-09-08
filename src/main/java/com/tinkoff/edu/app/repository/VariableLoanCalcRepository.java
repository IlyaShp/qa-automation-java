package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanResponse;

public class VariableLoanCalcRepository implements LoanCalcRepository {

  private LoanResponse[] arrayRepository = new LoanResponse[1];
  private static int position = 0;

  @Override
  public LoanResponse[] save (LoanResponse response) {
    arrayRepository [position] = response;
    position++;
    return arrayRepository;

  }
}
