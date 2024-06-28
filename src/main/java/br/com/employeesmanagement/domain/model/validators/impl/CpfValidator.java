package br.com.employeesmanagement.domain.model.validators.impl;

import br.com.employeesmanagement.domain.exception.CpfOrCnpjInvalidException;

public final class CpfValidator implements CpfCnpjValidator {
    @Override
    public String valid(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999") || cpf.length() != 11) {
            throw new CpfOrCnpjInvalidException("CPF e inválido");
        }

        if (cpf.length() != 11) {
            throw new CpfOrCnpjInvalidException("CPF e inválido");
        }

        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (10 - i);
        }

        int firstVerificationDigit = sum % 11;
        firstVerificationDigit = (firstVerificationDigit < 2) ? 0 : 11 - firstVerificationDigit;

        sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * (11 - i);
        }

        sum += firstVerificationDigit * 2;
        int secondVerificationDigit = sum % 11;
        secondVerificationDigit = (secondVerificationDigit < 2) ? 0 : 11 - secondVerificationDigit;

        if ((cpf.charAt(9) - '0') != firstVerificationDigit
                || (cpf.charAt(10) - '0') != secondVerificationDigit) {
            throw new CpfOrCnpjInvalidException("CPF e inválido");
        }
        return cpf;
    }
}
