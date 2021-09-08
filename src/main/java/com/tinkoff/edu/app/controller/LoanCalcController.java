package com.tinkoff.edu.app.controller;

import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.service.LoanCalcService;


public class LoanCalcController {

  private final LoanCalcService loanCalcService; //Creator

  public LoanCalcController(LoanCalcService loanCalcService) {
    this.loanCalcService = loanCalcService;
  }

  /**
   * Validates and logs request and response
   */
  public LoanResponse createRequest(LoanRequest request) {
    try {
      return loanCalcService.createRequest(request);
    } catch (Exception message) {
      throw new IllegalArgumentException(message);
    }
  }
}
