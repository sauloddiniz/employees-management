package br.com.employeesmanagement.domain.model.validators;

import br.com.employeesmanagement.domain.exception.CpfOrCnpjInvalidException;
import br.com.employeesmanagement.domain.model.validators.impl.CnpjValidator;
import br.com.employeesmanagement.domain.model.validators.impl.CpfValidator;

public class DocumentValidatorFactory {
    public static DocumentValidator createValidator(String cpfOuCnpj) {
        final String cpfOuCnpjSemCharEspeciais = removePontosHifensBarras(cpfOuCnpj);
        return cpfOuCnpjSemCharEspeciais.length() < 14 ? new CpfValidator() : new CnpjValidator();
    }

    private static String removePontosHifensBarras(String value) {
        if (value == null || value.isEmpty()) {
            throw new CpfOrCnpjInvalidException("CPF ou CNPJ não pode ser vazio");
        }
        return value.replaceAll("[\\.\\-/]", "");
    }
}
