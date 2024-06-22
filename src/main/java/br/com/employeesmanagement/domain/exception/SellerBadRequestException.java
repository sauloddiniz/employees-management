package br.com.employeesmanagement.domain.exception;

public class SellerBadRequestException extends RuntimeException {
    public SellerBadRequestException(String message) {
        super(message);
    }
}
