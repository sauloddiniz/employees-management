package br.com.employeesmanagement.domain.exception;

public class CpfOrCnpjInvalidException extends RuntimeException {
    public CpfOrCnpjInvalidException(String message) {
        super(message);
    }
}
