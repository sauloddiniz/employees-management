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

    TipoContratoEnum(String tipo, String tipoMatricula) {
        this.tipo = tipo;
        this.tipoAbreviado = tipoMatricula;
    }

    public String getTipoAbreviado() {
        return tipoAbreviado;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoContratoEnum fromString(String input) {

        if (input == null || input.isEmpty()) {
            throw new TipoContratoException("Tipo contrato não existente");
        }

        String normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll(NON_ASCII_REGEX, REPLACEMENT)
                .toLowerCase();

        for (TipoContratoEnum tipoContrato : TipoContratoEnum.values()) {
            String normalizedCode = Normalizer.normalize(tipoContrato.tipo, Normalizer.Form.NFD)
                    .replaceAll(NON_ASCII_REGEX, REPLACEMENT)
                    .toLowerCase();
            if (normalizedCode.equals(normalizedInput)) {
                return tipoContrato;
            }
        }
        throw new TipoContratoException("Tipo contrato não existente");
    }
}
