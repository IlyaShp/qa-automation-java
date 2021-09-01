package com.tinkoff.edu;

import com.tinkoff.edu.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AppTest {
    int tenAmount = 10_000;
    int oneThousendAmount = 1_000;
    int zero = 0;
    int one = 1;
    int nineMonths = 9;
    int twelveMonths = 12;
    int thirteenMonths = 13;
    int nineThousendAmount = 9_000;
    int elevenThousendAmount = 11_000;
    private LoanCalcController sut;

    public LoanRequest loanRequestTest(LoanRequestType type, int months, int amount) {
        return new LoanRequest(type, months, amount);
    }

    public LoanCalcController loanCalcControllerTest(int requestId) {
        LoanCalcController loanCalcController = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(requestId)));
        return loanCalcController;
    }

    @BeforeEach
    public void init() {
    }

    @Test
    @DisplayName("Case_#000001")
    void shouldGet1WhenFirstRequest() {
        VariableLoanCalcRepository repo = new VariableLoanCalcRepository();
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.OOO, nineMonths, tenAmount);
        sut = new LoanCalcController(new OriginalLoanCalcService(repo));
        assumeTrue(repo.getRequestId() == zero);
        LoanResponse response = sut.createRequest(loanRequest);
        assertEquals(one, response.getRequestId(), "error: requestId != 1");
    }

    @Test
    @DisplayName("Case_#000002")
    void shouldGetIncrementedIdWhenAnyCall() {
        LoanCalcController loanCalcController = loanCalcControllerTest(2);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.IP, nineMonths, oneThousendAmount);
        assertEquals(3, loanCalcController.createRequest(loanRequest).getRequestId(), "error: requestId != 3");
    }

    @Test
    @DisplayName("Case_#000003")
    public void shouldGetDeniedWhenValidRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.OOO, twelveMonths, oneThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, one), loanResponse, "error: not DENIED");
    }

    @Test
    @DisplayName("Case_#000004")
    public void shouldGetErrorWhenApplyNullRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(null, zero, zero);
        assertThrows(IllegalArgumentException.class, () -> {
            loanCalcController.createRequest(loanRequest);
        });
    }

    @Test
    @DisplayName("Case_#000005")
    public void shouldGetErrorWhenApplyNegativeAmountRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.IP, nineMonths, -2_000);
        assertThrows(IllegalArgumentException.class, () -> {
            loanCalcController.createRequest(loanRequest);
        });
    }

    @Test
    @DisplayName("Case_#000006")
    public void shouldGetErrorWhenApplyZeroAmountRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.IP, nineMonths, zero);
        assertThrows(IllegalArgumentException.class, () -> {
            loanCalcController.createRequest(loanRequest);
        });
    }

    @Test
    @DisplayName("Case_#000007")
    public void shouldGetErrorWhenApplyNegativeMonthsRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.IP, -3, tenAmount);
        assertThrows(IllegalArgumentException.class, () -> {
            loanCalcController.createRequest(loanRequest);
        });
    }

    @Test
    @DisplayName("Case_#000008")
    public void shouldGetErrorWhenApplyZeroMonthsRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.IP, zero, oneThousendAmount);
        assertThrows(IllegalArgumentException.class, () -> {
            loanCalcController.createRequest(loanRequest);
        });
    }

    @Test
    @DisplayName("Case_#000009")
    public void shouldGetApproveWhenPersonLessTwelveLessTenRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.PERSON, nineMonths, nineThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000010")
    public void shouldGetApproveWhenPersonLessTwelveEqualsTenRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.OOO, nineMonths, elevenThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000011")
    public void shouldGetApproveWhenPersonEqualsTwelveLessTenRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.PERSON, twelveMonths, nineThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000012")
    public void shouldGetApproveWhenPersonEqualsTwelveEqualsTenRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.PERSON, twelveMonths, tenAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000013")
    public void shouldGetDenyWhenPersonMoreTenMoreTwelveRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.PERSON, thirteenMonths, elevenThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000014")
    public void shouldGetDenyWhenOooForAnyLessTenRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.OOO, twelveMonths, nineThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000015")
    public void shouldGetDenyWhenOooForAnyEqualsTenRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.OOO, twelveMonths, tenAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000016")
    public void shouldGetApproveWhenOooLessTwelveMoreTenRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.OOO, nineMonths, elevenThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000017")
    public void shouldGetDenyWhenOooEqualsTwelveMoreTenRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.OOO, twelveMonths, elevenThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000018")
    public void shouldGetDenyWhenOooMoreTwelveMoreTenRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.OOO, thirteenMonths, elevenThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000019")
    public void shouldGetDenyWhenIpRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.IP, nineMonths, nineThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000020")
    public void shouldGetDenyWhenPersonLessOneThousandRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.PERSON, nineMonths, 999);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, one), loanResponse);
    }

    @Test
    @DisplayName("Case_#000021")
    public void shouldGetDenyWhenPersonMoreOneThousandRequest() {
        LoanCalcController loanCalcController = loanCalcControllerTest(zero);
        LoanRequest loanRequest = loanRequestTest(LoanRequestType.PERSON, nineMonths, oneThousendAmount);
        LoanResponse loanResponse = loanCalcController.createRequest(loanRequest);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, one), loanResponse);
    }
}
