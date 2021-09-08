package com.tinkoff.edu.app.logger;

import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;

public class LoanCalcLogger {

  public static void log(LoanRequest request) {
    System.out.println("calling request...");
  }

  public static void log(LoanResponse response) {
    System.out.println("calling response...");
  }
}
