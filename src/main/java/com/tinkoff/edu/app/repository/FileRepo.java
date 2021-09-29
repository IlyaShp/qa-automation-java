package com.tinkoff.edu.app.repository;

import com.tinkoff.edu.app.enums.LoanResponseType;
import com.tinkoff.edu.app.model.LoanResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.WRITE;

public class FileRepo implements LoanCalcRepository {

	Path path = Path.of("LoanCalcRepository.csv");

	public LoanResponseType getStatusByUUID(Object uuid) {
		if (uuid == null) {
			throw new IllegalArgumentException();
		}
		try {
			final List<String> fileLines = Files.readAllLines(path);
			for (String line : fileLines) {
				if (line.contains(uuid.toString())) {
					String[] responseParts = line.split(";");
					return LoanResponseType
							.valueOf(responseParts[responseParts.length - 1].replaceAll("\\s+", ""));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return LoanResponseType.ERROR;
		}
		return LoanResponseType.ERROR;
	}

	public boolean setStatusByUUID(Object uuid, LoanResponseType responseType) {
		if (uuid == null || responseType == null) {
			throw new IllegalArgumentException();
		}
		try {
			final List<String> newLines = Files.readAllLines(path).stream()
					.filter(e -> e.contains(uuid.toString()))
					.map(e -> {
						String[] responseParts = e.split(";");
						responseParts[responseParts.length - 1] = " " + responseType.toString();
						return String.join(";", responseParts);
					})
					.collect(Collectors.toList());
			Files.write(path, newLines, WRITE);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public UUID save(LoanResponse request) {
		if (request == null) {
			throw new IllegalArgumentException();
		}
		UUID uuid = UUID.randomUUID();
		try {
			Files.writeString(
					path,
					uuid.toString() + "; " + request.toString() + "; " +request.getResponseType().toString() + "\n",
					StandardOpenOption.APPEND,
					StandardOpenOption.CREATE,
					WRITE
			);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return uuid;
	}
}
