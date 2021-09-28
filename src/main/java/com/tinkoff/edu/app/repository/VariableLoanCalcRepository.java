package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.LoanResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VariableLoanCalcRepository implements LoanCalcRepository {

  private Map<UUID, LoanResponse> mapRepository = new HashMap ();
  private static int position = 0;

  public int getPosition() {
    return position;
  }

  public Map<UUID, LoanResponse> getMapRepository() {
    return mapRepository;
  }

  public LoanResponseType getStatusByUUID (Object uuid){
    if (uuid == null) throw new IllegalArgumentException();
    LoanResponse response = mapRepository.get(uuid);
    if (response == null) return LoanResponseType.ERROR;
    return response.getResponseType();
  }

  public LoanResponse[] getRequestByLoanType(LoanRequestType loanType) {
    Collection<LoanResponse> values = mapRepository.values();

    return values.stream()
        .filter(e -> e.getRequest().getType() == loanType)
        .toArray(LoanResponse[]::new);
  }

  public boolean setStatusByUUID(Object uuid, LoanResponseType responseType){
    if (uuid == null || responseType == null) throw new IllegalArgumentException();
    LoanResponse loanResponse = mapRepository.get(uuid);
    loanResponse.setResponseType(responseType);
    return true;
  }

  @Override
  public UUID save(LoanResponse response) {
    if (response == null) throw new IllegalArgumentException();
    UUID uuid = UUID.randomUUID();
    mapRepository.put(uuid, response);
    return uuid;
  }
}
