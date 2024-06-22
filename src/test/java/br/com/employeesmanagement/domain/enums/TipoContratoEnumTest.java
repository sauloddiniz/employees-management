package br.com.employeesmanagement.domain.enums;

import br.com.employeesmanagement.domain.exception.TipoContratoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TipoContratoEnumTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve lancar excecao valor vazio e nulo valido no enum")
    void shouldLaunchExecutionValueEmpty(String contrato) {

        final String expectedMessage = "Tipo contrato não existente";

        try {
            TipoContratoEnum.fromString(contrato);
        } catch (Exception exception) {
            assertEquals(TipoContratoException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pessoa", "Juridica", "Cl", "Fisica"})
    @DisplayName("Deve lancar excecao valor nao valido no enum")
    void shouldLaunchExecutionValueNotValidInEnum(String contrato) {

        final String expectedMessage = "Tipo contrato não existente";

        try {
            TipoContratoEnum.fromString(contrato);
        } catch (Exception exception) {
            assertEquals(TipoContratoException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"PESSOA JURIDICA", "PESSOA JÚRIDICA", "pessoa juridica", "pessoa júridica"})
    @DisplayName("Deve criar o tipo de contrato Pessoa juridica")
    void mustCreateTheContractTypeJuridica(String contrato) {

        final TipoContratoEnum expectedTipoContrato = TipoContratoEnum.PESSOA_JURIDICA;

        TipoContratoEnum tipoContratoEnum = TipoContratoEnum.fromString(contrato);

        Assertions.assertEquals(expectedTipoContrato, tipoContratoEnum);
    }

    @ParameterizedTest
    @ValueSource(strings = {"CLT", "clt", "clT", "çlt"})
    @DisplayName("Deve criar o tipo de contrato Pessoa Fisica")
    void mustCreateTheContractTypeFisica(String contrato) {

        final TipoContratoEnum expectedTipoContrato = TipoContratoEnum.CLT;

        TipoContratoEnum tipoContratoEnum = TipoContratoEnum.fromString(contrato);

        Assertions.assertEquals(expectedTipoContrato, tipoContratoEnum);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Outsourcing", "OutSoUrcing", "OUTSOURCING"})
    @DisplayName("Deve criar o tipo de contrato outsourcing")
    void mustCreateTheContractTypeOutsourcing(String contrato) {

        final TipoContratoEnum expectedTipoContrato = TipoContratoEnum.OUTSOURCING;

        TipoContratoEnum tipoContratoEnum = TipoContratoEnum.fromString(contrato);

        Assertions.assertEquals(expectedTipoContrato, tipoContratoEnum);
    }
}
