package com.tinkoff.edu;

import com.tinkoff.edu.app.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AppTest {
    private LoanRequest request;
    private LoanCalcController sut;

    @BeforeEach
    public void init() {
        //region Fixture / Arrange / Given
        //endregion
    }

    @Test
    @DisplayName("Case_#000001")
    void shouldGet1WhenFirstRequest() {
        //region Fixture / Arrange / Given
        VariableLoanCalcRepository repo = new VariableLoanCalcRepository();
        request = new LoanRequest(LoanRequestType.IP, 9, 1_000);
        sut = new LoanCalcController(new OriginalLoanCalcService(repo));
        //endregion
        assumeTrue(repo.getRequestId() == 0);

        //region Act / When
        LoanResponse response = sut.createRequest(this.request);
        //endregion

        //region Assert / Then
//        assertTrue(false);
//        assertFalse(true);
        assertEquals(1, response.getRequestId(), "error: requestId != 1");
//        assertNull(1); assertNotNull(0);
        //endregion
    }

    @Test
    @DisplayName("Case_#000002")
    void shouldGetIncrementedIdWhenAnyCall() {
        //region Fixture / Arrange / Given
        final int NON_DEFAULT_ANY_ID = 2;
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(NON_DEFAULT_ANY_ID)));
        request = new LoanRequest(LoanRequestType.IP, 9, 1_000);
        //endregion

        //region Act / When

        //endregion

        //region Assert / Then
        assertEquals(3, sut.createRequest(request).getRequestId());
        //endregion
    }

    @Test
    @DisplayName("Case_#000003")
    public void shouldGetDeniedWhenValidRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        int approvingMonths = 12;
        request = new LoanRequest(LoanRequestType.IP, approvingMonths, 1_000);

        LoanResponse loanResponse = sut.createRequest(this.request);

        assertEquals(new LoanResponse(LoanResponseType.DENIED, 1), loanResponse);
    }


    @Test
    @DisplayName("Case_#000004")
    public void shouldGetErrorWhenApplyNullRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = null;
        assertThrows(IllegalArgumentException.class, () -> {
            sut.createRequest(request);
        });

    }

    @Test
    @DisplayName("Case_#000005")
    public void shouldGetErrorWhenApplyNegativeAmountRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        int negativeAmount = -2000;
        request = new LoanRequest(LoanRequestType.IP, negativeAmount, 1_000);
        assertThrows(IllegalArgumentException.class, () -> {
            sut.createRequest(request);
        });

    }

    @Test
    @DisplayName("Case_#000006")
    public void shouldGetErrorWhenApplyZeroAmountRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        int zeroAmount = 0;
        request = new LoanRequest(LoanRequestType.IP, zeroAmount, 1_000);
        assertThrows(IllegalArgumentException.class, () -> {
            sut.createRequest(request);
        });

    }

    @Test
    @DisplayName("Case_#000007")
    public void shouldGetErrorWhenApplyNegativeMonthsRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        int negativeMonths = -3;
        request = new LoanRequest(LoanRequestType.IP, negativeMonths, 1_000);
        assertThrows(IllegalArgumentException.class, () -> {
            sut.createRequest(request);
        });

    }

    @Test
    @DisplayName("Case_#000008")
    public void shouldGetErrorWhenApplyZeroMonthsRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        int zeroMonths = 0;
        request = new LoanRequest(LoanRequestType.IP, zeroMonths, 1_000);
        assertThrows(IllegalArgumentException.class, () -> {
            sut.createRequest(request);
        });
    }

    @Test
    @DisplayName("Case_#000009")
    public void shouldGetApproveWhenPersonLessTwelveLessTenRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.PERSON, 11, 9_999);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000010")
    public void shouldGetApproveWhenPersonLessTwelveEqualsTenRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.PERSON, 11, 10_000);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000011")
    public void shouldGetApproveWhenPersonEqualsTwelveLessTenRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.PERSON, 12, 9_999);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000012")
    public void shouldGetApproveWhenPersonEqualsTwelveEqualsTenRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.PERSON, 12, 10_000);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000013")
    public void shouldGetDenyWhenPersonMoreTenMoreTwelveRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.PERSON, 13, 10_001);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000014")
    public void shouldGetDenyWhenOooForAnyLessTenRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.OOO, 12, 9_999);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000015")
    public void shouldGetDenyWhenOooForAnyEqualsTenRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.OOO, 12, 10_000);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000016")
    public void shouldGetApproveWhenOooLessTwelveMoreTenRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.OOO, 11, 10_001);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000017")
    public void shouldGetDenyWhenOooEqualsTwelveMoreTenRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.OOO, 12, 10_001);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000018")
    public void shouldGetDenyWhenOooMoreTwelveMoreTenRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.OOO, 13, 10_001);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000019")
    public void shouldGetDenyWhenIpRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.IP, 11, 9_000);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000020")
    public void shouldGetDenyWhenPersonLessOneThousandRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.PERSON, 11, 999);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.DENIED, 1), loanResponse);
    }

    @Test
    @DisplayName("Case_#000021")
    public void shouldGetDenyWhenPersonMoreOneThousandRequest() {
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(0)));
        request = new LoanRequest(LoanRequestType.PERSON, 11, 1_000);
        LoanResponse loanResponse = sut.createRequest(this.request);
        assertEquals(new LoanResponse(LoanResponseType.APPROVED, 1), loanResponse);
    }

}
