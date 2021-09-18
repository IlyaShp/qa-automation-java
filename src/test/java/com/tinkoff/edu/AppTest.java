package com.tinkoff.edu;

import com.tinkoff.edu.app.controller.LoanCalcController;
import com.tinkoff.edu.app.enums.LoanRequestType;
import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.VariableLoanCalcRepository;
import com.tinkoff.edu.service.LoanCalcServiceImp;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
class AppTest {

	private final String fio = "Иванов Иван Иванович";
	private LoanCalcController sut;

	@BeforeEach
	public void init() {
		sut = new LoanCalcController(new LoanCalcServiceImp(new VariableLoanCalcRepository()));
	}

	@Test
	@DisplayName("Проверка, что requestId не пустой")
	void shouldGet1WhenFirstRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.OOO, 9, 10_000, fio);
		LoanResponse response = sut.createRequest(loanRequest);
		assertNotEquals(null, response.getRequest().getRequestId(), "Request пустой");
	}

	@Test
	@DisplayName("Проверка, что при добавлении нового request значение массива заполняется")
	void MassiveSize() {
		int positionExpected = new VariableLoanCalcRepository().getPosition();
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.OOO, 9, 10_000, fio);
		sut.createRequest(loanRequest);
		int positionActual = new VariableLoanCalcRepository().getPosition();
		assertEquals(++positionExpected, positionActual, "Массив не заполняется");
	}

	@Test
	@DisplayName("Проверка на ошибку выхода за длину массива")
	void xMassiveSizeError() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.OOO, 9, 10_000, fio);
		assertThrows(IllegalArgumentException.class, () -> sut.createRequest(loanRequest));
	}

	@Test
	@DisplayName("При создании request co значением null получаем ошибку")
	void shouldGetErrorWhenApplyNullRequest() {
		LoanRequest loanRequest = new LoanRequest(null, 0, 0, null);
		assertThrows(IllegalArgumentException.class, () -> sut.createRequest(loanRequest));
	}

	@Test
	@DisplayName("ФИО не может быть длиной более 100 символов")
	void shouldGetErrorWhenFioMore100Symbols() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.PERSON, 9, 9_000,
				"kdljahdlfhslfkashflakhfalkfhlkfhalkfnjajnalkjnakcnlkjdddfsdsdncjdshfglkshslkdhgslhgslfhglsjhgljdhgsldsdfs");
		assertThrows(IllegalArgumentException.class, () -> sut.createRequest(loanRequest));
	}

	@Test
	@DisplayName("ФИО не может начинаться на пробел")
	void shouldGetErrorWhenFioStartingWhitespace() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.PERSON, 9, 9_000,
				" Ivanov Ivan");
		assertThrows(IllegalArgumentException.class, () -> sut.createRequest(loanRequest));
	}

	@Test
	@DisplayName("ФИО не может начинаться на пробел")
	void shouldGetErrorWhenFioFinishingWhitespace() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.PERSON, 9, 9_000,
				"Ivanov Ivan ");
		assertThrows(IllegalArgumentException.class, () -> sut.createRequest(loanRequest));
	}

	@Test
	@DisplayName("Если в request отрицательная сумма")
	public void shouldGetErrorWhenApplyNegativeAmountRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.IP, 10, -2_000, fio);
		assertThrows(IllegalArgumentException.class, () -> sut.createRequest(loanRequest));
	}

	@Test
	@DisplayName("Если в request сумма равна нулю")
	public void shouldGetErrorWhenApplyZeroAmountRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.IP, 10, 0, fio);
		assertThrows(IllegalArgumentException.class, () -> sut.createRequest(loanRequest));
	}

	@Test
	@DisplayName("Если в request отрицательное число месяцев")
	void shouldGetErrorWhenApplyNegativeMonthsRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.IP, -3, 10_000, fio);
		assertThrows(IllegalArgumentException.class, () -> sut.createRequest(loanRequest));
	}

	@Test
	@DisplayName("Если в request количество месяцев равно нулю")
	public void shouldGetErrorWhenApplyZeroMonthsRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.IP, 0, 10_000, fio);
		assertThrows(IllegalArgumentException.class, () -> sut.createRequest(loanRequest));
	}

	@Test
	@DisplayName("Одобрено, если Person берёт меньше чем на 12 месяцев на сумму меньше чем 10_000")
	public void shouldGetApproveWhenPersonLessTwelveLessTenRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.PERSON, 9, 9_000, fio);
		LoanResponse response = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.APPROVED, response.getResponseType(), "ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Одобрено, если ООО берёт меньше чем на 12 месяцев на сумму 10_000")
	public void shouldGetApproveWhenPersonLessTwelveEqualsTenRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.PERSON, 9, 10_000, fio);
		LoanResponse response = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.APPROVED, response.getResponseType(), "ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Одобрено, если Person берёт на 12 месяцев на сумму меньше чем 10_000")
	public void shouldGetApproveWhenPersonEqualsTwelveLessTenRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.PERSON, 12, 9_000, fio);
		LoanResponse response = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.APPROVED, response.getResponseType(), "ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Одобрено, если Person берёт на 12 месяцев на сумму 10_000")
	void shouldGetApproveWhenPersonEqualsTwelveEqualsTenRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.PERSON, 12, 10_000, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.APPROVED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Отказано, если Person берёт больше чем на 12 месяцев на сумму больше чем 10_000")
	public void shouldGetDenyWhenPersonMoreTwelveMoreTwelveRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.PERSON, 13, 11_000, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.DENIED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Отказано, если OOO берёт на сумму меньше чем 10_000")
	public void shouldGetDenyWhenOooForAnyLessTenRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.OOO, 11, 9_000, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.DENIED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Отказано, если OOO берёт на сумму 10_000")
	public void shouldGetDenyWhenOooForAnyEqualsTenRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.OOO, 11, 10_000, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.DENIED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Одобрено, если OOO берёт меньше чем на 12 месяцев на сумму больше чем 10_000")
	public void shouldGetApproveWhenOooLessTwelveMoreTenRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.OOO, 11, 11_000, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.APPROVED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Отказано, если OOO берёт на 12 месяцев на сумму больше чем 10_000")
	public void shouldGetDenyWhenOooEqualsTwelveMoreTenRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.OOO, 12, 11_000, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.DENIED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Отказано, если OOO берёт больше чем на 12 месяцев на сумму больше чем 10_000")
	public void shouldGetDenyWhenOooMoreTwelveMoreTenRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.OOO, 13, 11_000, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.DENIED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Отказано, если IP запрашивает кредит")
	public void shouldGetDenyWhenIpRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.IP, 9, 9_000, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.DENIED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Отказано, если Person берёт меньше чем на 1_000")
	public void shouldGetDenyWhenPersonLessOneThousandRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.IP, 9, 900, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.DENIED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}

	@Test
	@DisplayName("Отказано, если Person берёт меньше чем на 1_000")
	public void shouldGetDenyWhenPersonMoreOneThousandRequest() {
		LoanRequest loanRequest = new LoanRequest(LoanRequestType.IP, 9, 900, fio);
		LoanResponse loanResponse = sut.createRequest(loanRequest);
		assertEquals(LoanResponseType.DENIED, loanResponse.getResponseType(),
				"ResponseType не соответствует ожидаемому");
	}
}
