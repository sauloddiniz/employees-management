package br.com.employeesmanagement.domain.enums;

import br.com.employeesmanagement.domain.exception.TipoContratoException;

import java.text.Normalizer;

public enum TipoContratoEnum {
    OUTSOURCING("Outsourcing", "OUT"),
    CLT("Clt", "CLT"),
    PESSOA_JURIDICA("Pessoa Jurídica", "PJ");
    public static final String NON_ASCII_REGEX = "[^\\p{ASCII}]";
    public static final String REPLACEMENT = "";
    private final String tipo;
    private final String tipoAbreviado;

    TipoContratoEnum(String tipo, String tipoAbreviado) {
        this.tipo = tipo;
        this.tipoAbreviado = tipoAbreviado;
    }

    public String getTipoAbreviado() {
        return tipoAbreviado;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoContratoEnum fromString(String input) {

        if (isNotNullOrNotEmpty(input)) {
            throw new TipoContratoException("Tipo contrato não existente");
        }

        String normalizedInput = normalizeString(input);

        for (TipoContratoEnum tipoContrato : TipoContratoEnum.values()) {
            String normalizedCode = normalizeString(tipoContrato.tipo);
            if (normalizedCode.equals(normalizedInput)) {
                return tipoContrato;
            }
        }
        throw new TipoContratoException("Tipo contrato não existente");
    }

    private static String normalizeString(String input) {
        String normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll(NON_ASCII_REGEX, REPLACEMENT)
                .toLowerCase();
        return normalizedInput;
    }

    private static boolean isNotNullOrNotEmpty(String input) {
        return input == null || input.isEmpty();
    }
}
