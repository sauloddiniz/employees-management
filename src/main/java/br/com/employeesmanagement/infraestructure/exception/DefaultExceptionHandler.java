package br.com.employeesmanagement.infraestructure.exception;

import br.com.employeesmanagement.domain.exception.CpfOrCnpjInvalidException;
import br.com.employeesmanagement.domain.exception.SellerBadRequestException;

import br.com.employeesmanagement.domain.exception.SellerNotFoundException;
import br.com.employeesmanagement.domain.exception.TipoContratoException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SellerBadRequestException.class)
    public ResponseEntity<ErrorResponse> sellerBadRequest(SellerBadRequestException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(ClientApiFeingException.class)
    public ResponseEntity<ErrorResponse> clientBranchApiFeign(ClientApiFeingException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(TipoContratoException.class)
    public ResponseEntity<ErrorResponse> tipoContratoException(TipoContratoException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .detail("Contratos disponiveis: ['Outsourcing', 'Clt', 'Pessoa Física']")
                        .build());
    }

    @ExceptionHandler(CpfOrCnpjInvalidException.class)
    public ResponseEntity<ErrorResponse> cpfOuCnpjInvalidoException(CpfOrCnpjInvalidException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.BAD_REQUEST.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(SellerNotFoundException.class)
    public ResponseEntity<ErrorResponse> sellerNotFound(SellerNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @ExceptionHandler(BranchNotFoundException.class)
    public ResponseEntity<ErrorResponse> branchNotFound(BranchNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .message(exception.getMessage())
                        .httpStatus(HttpStatus.NOT_FOUND.value())
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .build());
    }

    @Builder
    @Getter
    private static class ErrorResponse {
        @Builder.Default
        private Instant timestamp = Instant.now();
        private String message;
        private Integer httpStatus;
        private String path;
        private String method;
        private String detail;
    }
}
