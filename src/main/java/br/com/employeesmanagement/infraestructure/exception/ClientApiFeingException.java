package br.com.employeesmanagement.infraestructure.exception;

public class ClientApiFeingException extends RuntimeException {
    public ClientApiFeingException(String message) {
        super(message);
    }
}
