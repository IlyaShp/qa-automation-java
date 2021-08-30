package com.tinkoff.edu;

import com.tinkoff.edu.app.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class AppTest {
    private LoanRequest request;
    private LoanCalcController sut;

    @BeforeEach
    public void init() {
        //region Fixture / Arrange / Given
        request = new LoanRequest(LoanRequestType.IP, 10,1_000);
        //endregion
    }

    @Test
    @DisplayName("Case_#000001")
    void shouldGet1WhenFirstRequest(){
        //region Fixture / Arrange / Given
        VariableLoanCalcRepository repo = new VariableLoanCalcRepository();
        sut = new LoanCalcController(new OriginalLoanCalcService(repo));
        //endregion
        assumeTrue(repo.getRequestId() == 0);

        //region Act / When
        int requestId = sut.createRequest(request);
        //endregion

        //region Assert / Then
//        assertTrue(false);
//        assertFalse(true);
        assertEquals(requestId,1, "error: requestId != 1");
//        assertNull(1); assertNotNull(0);
        //endregion
    }

    @Test
    @DisplayName("Case_#000002")
    void shouldGetIncrementedIdWhenAnyCall() {
        //region Fixture / Arrange / Given
        final int NON_DEFAULT_ANY_ID = 2;
        sut = new LoanCalcController(new OriginalLoanCalcService(new VariableLoanCalcRepository(NON_DEFAULT_ANY_ID)));
        //endregion

        //region Act / When

        //endregion

        //region Assert / Then
        assertEquals(3, sut.createRequest(request));
        //endregion
    }

}
