package com.tinkoff.edu.service;

import static com.tinkoff.edu.app.enums.LoanResponseType.APPROVED;
import static com.tinkoff.edu.app.enums.LoanResponseType.DENIED;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.LoanRequest;
import com.tinkoff.edu.app.model.LoanResponse;
import com.tinkoff.edu.app.repository.LoanCalcRepository;

import java.util.UUID;

/**
 * API для обработки и создания заявки на кредит..
 */

public class LoanCalcServiceImp implements LoanCalcService {

	private final LoanCalcRepository repo; //Field DI

	public LoanCalcServiceImp(LoanCalcRepository repo) {
		this.repo = repo;
	}

	@Override
	public LoanResponse createRequest(LoanRequest request) {
		if ((request == null) || (request.getMonths() <= 0) || (request.getAmount() <= 0)) {
			System.out
					.println("Переданы не валидные значения для инициализации request || months || amount");
			throw new IllegalArgumentException();
		} else {
			if (request.getFio().length() > 100 || request.getFio().startsWith(" ") || request.getFio()
					.endsWith(" "))
			{
				System.out.println(
						"ФИО не может быть длиной более 100 символов и начинаться либо заканчиваться пробелами.");
				throw new IllegalArgumentException();
			}
		}
		LoanResponseType responseType = this.countResponseType(request);
		UUID responseId = UUID.randomUUID();
		LoanResponse loanResponse = new LoanResponse(responseType, responseId, request);
		repo.save(loanResponse);
		return loanResponse;
	}

	public LoanResponseType countResponseType(LoanRequest request) {
		switch (request.getType()) {
			case PERSON: {
				if (request.getAmount() <= 10000) {
					if (request.getMonths() <= 12) {
						return APPROVED;
					} else {
						return DENIED;
					}
				} else {
					if (request.getMonths() > 12) {
						return DENIED;
					} else {
						return APPROVED;
					}
				}
			}

			case OOO: {
				if (request.getAmount() <= 10000) {
					return DENIED;
				} else {
					if (request.getMonths() < 12) {
						return APPROVED;
					} else {
						return DENIED;
					}
				}
			}

			case IP:
				return DENIED;
		}
		return DENIED;
	}
}

