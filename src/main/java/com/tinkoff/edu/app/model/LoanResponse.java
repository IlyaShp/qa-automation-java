package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanResponseType;
import java.util.Objects;
import java.util.UUID;

/**
 * Response from the system
 */
public class LoanResponse {

  private final UUID responseId;
  private final LoanResponseType responseType;
  private final LoanRequest request;

  public LoanResponse(LoanResponseType responseType, UUID requestId, LoanRequest request) {
    this.responseType = responseType;
    this.responseId = requestId;
    this.request = request;
  }

  public LoanRequest getRequest() {
    return request;
  }

  public LoanResponseType getResponseType() {
    return responseType;
  }

  public UUID getResponseId() {
    return responseId;
  }

  @Override
  public String toString() {
    return "LoanResponse{" +
        "responseType=" + responseType +
        ", responseId=" + responseId +
        ", request=" + request +
        '}';
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    LoanResponse that = (LoanResponse) o;
    return getResponseId() == that.getResponseId() && getResponseType() == that.getResponseType();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getResponseType(), getResponseId());
  }
}
