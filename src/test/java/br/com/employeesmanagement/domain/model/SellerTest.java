package br.com.employeesmanagement.domain.model;

import br.com.employeesmanagement.domain.enums.TipoContratoEnum;
import br.com.employeesmanagement.domain.exception.CpfOrCnpjInvalidException;
import br.com.employeesmanagement.domain.exception.SellerBadRequestException;
import br.com.employeesmanagement.domain.model.validators.DocumentValidatorFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SellerTest {

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve ocorrer excecao nome invalido")
    void invalidNameExceptionShouldOccur(String nome) {

        final String expectedMessage = "Nome não pode ser vazio";

        final String cpfOuCnpj = "642.393.620-08";
        final String email = "brucewayne@batman.com";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "Clt";

        try {
            new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);
            fail();
        } catch (Exception exception) {
            assertEquals(SellerBadRequestException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1986-27-27", "198-02-07", "1986-13-01", "1986-01-36"})
    @DisplayName("Deve ocorrer excecao com data no formato invalido")
    void exceptionShouldOccurWithDateInInvalidFormat(String dataNascimento) {

        final String expectedMessage = "Formato de data inválido. O formato esperado é 'aaaa-mm-dd'";

        final String nome = "Bruce Wayne";
        final String cpfOuCnpj = "642.393.620-08";
        final String email = "brucewayne@batman.com";
        final String tipoContrato = "Clt";

        try {
            new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);
            fail();
        } catch (Exception exception) {
            assertEquals(SellerBadRequestException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve cria vendedor se dataNascimento nulo ou vazio")
    void exceptionShouldOccurWithDateInInvalidFormatT(String dataNascimento) {


        final String nome = "Bruce Wayne";
        final String cpfOuCnpj = "642.393.620-08";
        final String email = "brucewayne@batman.com";
        final String tipoContrato = "Clt";

        Seller seller = new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);

        assertNull(seller.getDataNascimento());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve ocorrer excecao cpfOuCnpj vazio")
    void emptyCpfOuCnpjExceptionMustOccur(String cpfOuCnpj) {

        final String expectedMessage = "CPF ou CNPJ não pode ser vazio";

        final String nome = "Bruce Wayne";
        final String email = "brucewayne@batman.com";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "Clt";

        try {
            new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);
            fail();
        } catch (Exception exception) {
            assertEquals(CpfOrCnpjInvalidException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"123.789.654-86", "111.222.333-87", "222.222.222-2"})
    @DisplayName("Deve ocorrer excecao cpf invalido")
    void invalidCpfExceptionShouldOccur(String cpf) {

        final String expectedMessage = "CPF e inválido";

        final String nome = "Bruce Wayne";
        final String email = "brucewayne@batman.com";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "Clt";

        try {
            new Seller(DocumentValidatorFactory.createValidator(cpf), nome, dataNascimento, cpf, email, tipoContrato);
            fail();
        } catch (Exception exception) {
            assertEquals(CpfOrCnpjInvalidException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"33.104.676/0001-05", "87.176.626/0001-05", "00.000.000/0000-00"})
    @DisplayName("Deve ocorrer excecao cnpj invalido")
    void invalidCnpjExceptionShouldOccur(String cnpj) {

        final String expectedMessage = "CNPJ e inválido";

        final String nome = "Bruce Wayne";
        final String email = "brucewayne@batman.com";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "Clt";

        try {
            new Seller(DocumentValidatorFactory.createValidator(cnpj), nome, dataNascimento, cnpj, email, tipoContrato);
            fail();
        } catch (Exception exception) {
            assertEquals(CpfOrCnpjInvalidException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve ocorrer excecao se email vazio")
    void emptyEmailExceptionMustOccur(String email) {

        final String expectedMessage = "Email não pode ser vazio";

        final String nome = "Bruce Wayne";
        final String cpfOuCnpj = "642.393.620-08";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "Clt";

        try {
            new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);
            fail();
        } catch (Exception exception) {
            assertEquals(SellerBadRequestException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Deve ocorrer excecao se tipo contrato vazio")
    void emptyTipoContratoExceptionMustOccur(String tipoContrato) {

        final String expectedMessage = "Tipo de contrato não pode ser vazio";

        final String nome = "Bruce Wayne";
        final String cpfOuCnpj = "642.393.620-08";
        final String dataNascimento = "1986-02-07";
        final String email = "brucewayne@batman.com";


        try {
            new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);
            fail();
        } catch (Exception exception) {
            assertEquals(SellerBadRequestException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"bruceway.com.br", "bruceway@com"})
    @DisplayName("Deve ocorrer excecao se email invalido")
    void invalidEmailExceptionMustOccur(String email) {

        final String expectedMessage = "Email deve ser valido";

        final String nome = "Bruce Wayne";
        final String cpfOuCnpj = "642.393.620-08";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "Clt";

        try {
            new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);
            fail();
        } catch (Exception exception) {
            assertEquals(SellerBadRequestException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @Test
    @DisplayName("Deve ocorrer excecao se tipo cadastro CNPJ utilizando cpf")
    void exceptionShouldOccurIfTypeCNPJ_RegistrationUsingEmail() {

        final String expectedMessage = "Tipo de contrato não pode ser CPF";

        final String nome = "Bruce Wayne";
        final String email = "brucewayne@batemail.com";
        final String cpfOuCnpj = "642.393.620-08";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "Pessoa Juridica";

        try {
            new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);
            fail();
        } catch (Exception exception) {
            assertEquals(SellerBadRequestException.class, exception.getClass());
            assertEquals(expectedMessage, exception.getMessage());
        }
    }

    @Test
    @DisplayName("Deve cria vendedor utilizando CPF com sucesso")
    void mustCreateSellerUsingCPF_Successfully() {

        final TipoContratoEnum expectedTipoContrato = TipoContratoEnum.CLT;
        final String expectedCpf = "64239362008";
        final LocalDate expectedDataNascimento = LocalDate.parse("1986-02-07");

        final String nome = "Bruce Wayne";
        final String email = "brucewayne@batemail.com";
        final String cpfOuCnpj = "642.393.620-08";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "clt";

        Seller seller = new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);

        assertEquals(expectedTipoContrato, seller.getTipoContrato());
        assertEquals(expectedCpf, seller.getCpfOuCnpj());
        assertEquals(expectedDataNascimento, seller.getDataNascimento());
    }

    @Test
    @DisplayName("Deve cria vendedor utilizando CNPJ com sucesso")
    void mustCreateSellerUsingCNPJ_Successfully() {

        final TipoContratoEnum expectedTipoContrato = TipoContratoEnum.PESSOA_JURIDICA;
        final String expectedCpf = "98284403000104";
        final LocalDate expectedDataNascimento = LocalDate.parse("1986-02-07");

        final String nome = "Bruce Wayne";
        final String email = "brucewayne@batemail.com";
        final String cpfOuCnpj = "98.284.403/0001-04";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "Pessoa juridica";

        Seller seller = new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);

        assertEquals(expectedTipoContrato, seller.getTipoContrato());
        assertEquals(expectedCpf, seller.getCpfOuCnpj());
        assertEquals(expectedDataNascimento, seller.getDataNascimento());
    }

    @Test
    @DisplayName("Deve cria vendedor utilizando outsourcing com sucesso")
    void mustCreateSellerUsingOutsourcing_Successfully() {

        final TipoContratoEnum expectedTipoContrato = TipoContratoEnum.OUTSOURCING;
        final String expectedCpf = "85803580007";
        final LocalDate expectedDataNascimento = LocalDate.parse("1986-02-07");

        final String nome = "Bruce Wayne";
        final String email = "brucewayne@batemail.com";
        final String cpfOuCnpj = "858.035.800-07";
        final String dataNascimento = "1986-02-07";
        final String tipoContrato = "outsourcing";

        Seller seller = new Seller(DocumentValidatorFactory.createValidator(cpfOuCnpj), nome, dataNascimento, cpfOuCnpj, email, tipoContrato);

        assertEquals(expectedTipoContrato, seller.getTipoContrato());
        assertEquals(expectedCpf, seller.getCpfOuCnpj());
        assertEquals(expectedDataNascimento, seller.getDataNascimento());
    }

}
