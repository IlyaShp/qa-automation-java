package com.tinkoff.edu.app.model;

import com.tinkoff.edu.app.enums.LoanRequestType;

import java.util.UUID;

/**
 * Class, Type -> objects, instances
 */
public class LoanRequest {

	private final LoanRequestType type;
	private final String fio;
	private final int months; //stateful + immutable
	private final int amount;
	private final UUID requestId;

	public LoanRequest(LoanRequestType type, int months, int amount, String fio) {
		this.type = type;
		this.months = months;
		this.amount = amount;
		this.fio = fio;
		requestId = UUID.randomUUID();
	}

	public UUID getRequestId() {
		return requestId;
	}

	public LoanRequestType getType() {
		return type;
	}

	public int getMonths() {
		return months;
	}

	public int getAmount() {
		return amount;
	}

	public String getFio() {
		return fio;
	}

	@Override
	public String toString() {
		return "LoanRequest{" +
				"type=" + type +
				", fio='" + fio + '\'' +
				", months=" + months +
				", amount=" + amount +
				'}';
	}
}
