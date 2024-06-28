package br.com.employeesmanagement.domain.model.validators.impl;

public sealed interface CpfCnpjValidator permits CnpjValidator, CpfValidator {
    String valid(String cpfOuCnpj);
}
