package br.com.employeesmanagement.domain.model.validators;

import br.com.employeesmanagement.domain.exception.CpfOrCnpjInvalidException;
import br.com.employeesmanagement.domain.model.validators.impl.CnpjValidator;
import br.com.employeesmanagement.domain.model.validators.impl.CpfValidator;
import br.com.employeesmanagement.domain.model.validators.impl.CpfCnpjValidator;

public class CpfCnpjValidatorFactory {

    public static final String DOT_SLASH_HYPHEN_REGEX = "[\\.\\-/]";

    public static CpfCnpjValidator createValidator(String cpfOuCnpj) {
        final String cpfOuCnpjSemCharEspeciais = removePontosHifensBarras(cpfOuCnpj);
        return isIndividualCpf(cpfOuCnpjSemCharEspeciais) ? new CpfValidator() : new CnpjValidator();
    }

    private static String removePontosHifensBarras(String value) {
        if (isNotEmptyOrNotNull(value)) {
            throw new CpfOrCnpjInvalidException("CPF ou CNPJ não pode ser vazio");
        }
        return value.replaceAll(DOT_SLASH_HYPHEN_REGEX, "");
    }

    private static boolean isNotEmptyOrNotNull(String value) {
        return value == null || value.isEmpty();
    }

    private static boolean isIndividualCpf(String cpfOuCnpjSemCharEspeciais) {
        return cpfOuCnpjSemCharEspeciais.length() < 14;
    }
}
