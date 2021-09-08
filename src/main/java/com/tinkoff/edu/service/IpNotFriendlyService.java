package com.tinkoff.edu.service;

import com.tinkoff.edu.app.repository.LoanCalcRepository;

public class IpNotFriendlyService extends LoanCalcServiceImp {

  public IpNotFriendlyService(LoanCalcRepository repo) {
    super(repo);
  }
}