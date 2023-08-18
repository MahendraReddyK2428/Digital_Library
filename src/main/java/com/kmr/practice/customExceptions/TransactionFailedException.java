package com.kmr.practice.customExceptions;

public class TransactionFailedException extends RuntimeException {

	public TransactionFailedException(String message) {
		super(message);
	}
}
