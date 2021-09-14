package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.model.LoanResponse;

public class VariableLoanCalcRepository implements LoanCalcRepository {

  private LoanResponse[] arrayRepository = new LoanResponse[15];
  private static int position = 0;

  @Override
  public LoanResponse[] save (LoanResponse response) {
    arrayRepository [position] = response;
    position++;
    return arrayRepository;
  }

  public LoanResponse[] getArrayRepository() {
    return arrayRepository;
  }

  public int getPosition() {
    return position;
  }

}
