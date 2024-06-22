package br.com.employeesmanagement.domain.model;

import br.com.employeesmanagement.domain.model.validators.DocumentValidator;
import br.com.employeesmanagement.domain.enums.TipoContratoEnum;
import br.com.employeesmanagement.domain.exception.SellerBadRequestException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Seller {
    public static final String EMAIL_REGEX = ".+@.+\\..+";
    private String id;
    private String matricula;
    private String nome;
    private LocalDate dataNascimento;
    private String cpfOuCnpj;
    private String email;
    private TipoContratoEnum tipoContrato;
    private Branch filal;
    private DocumentValidator documentValidator;

    public Seller(DocumentValidator documentValidator, String nome, String dataNascimento, String cpfOuCnpj, String email, String tipoContrato) {
        this.documentValidator = documentValidator;
        validaNome(nome);
        this.nome = nome;
        this.dataNascimento = validaFormatoData(dataNascimento);
        this.cpfOuCnpj = documentValidator.valid(cpfOuCnpj);
        validaEmail(email);
        this.email = email;
        this.tipoContrato = validaContrato(tipoContrato);
        validaCnpjParaTipoDeContratoPessoaJuridica(this.cpfOuCnpj, this.tipoContrato);
    }

    public Seller(Long id, String nome, String matricula, LocalDate dataNascimento, String cpfOuCnpj, String email, TipoContratoEnum tipoContrato, Long filialId) {
        this.id = String.valueOf(id);
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.tipoContrato = tipoContrato;
        this.filal = new Branch(filialId);
    }

    public void createMatricula() {
        this.matricula =
                this.getId()
                        .concat("-")
                        .concat(this.tipoContrato.getTipoAbreviado());
    }

    private void validaNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new SellerBadRequestException("Nome não pode ser vazio");
        }
    }

    private LocalDate validaFormatoData(String dataNascimento) {
        if (dataNascimento != null && !dataNascimento.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                return LocalDate.parse(dataNascimento, formatter);
            } catch (DateTimeParseException exception) {
                throw new SellerBadRequestException("Formato de data inválido. O formato esperado é 'aaaa-mm-dd'");
            }
        }
        return null;
    }

    private void validaCnpjParaTipoDeContratoPessoaJuridica(String cpfOuCnpj, TipoContratoEnum tipoContrato) {
        if (tipoContrato.equals(TipoContratoEnum.PESSOA_JURIDICA) && cpfOuCnpj.length() < 14) {
            throw new SellerBadRequestException("Tipo de contrato não pode ser CPF");
        }
    }

    private TipoContratoEnum validaContrato(String tipoContrato) {
        if (tipoContrato == null || tipoContrato.isEmpty()) {
            throw new SellerBadRequestException("Tipo de contrato não pode ser vazio");
        }
        return TipoContratoEnum.fromString(tipoContrato);
    }

    private void validaEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new SellerBadRequestException("Email não pode ser vazio");
        }
        if (Boolean.FALSE.equals(isValidEmail(email))) {
            throw new SellerBadRequestException("Email deve ser valido");
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }

    public String getId() {
        return id;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public TipoContratoEnum getTipoContrato() {
        return tipoContrato;
    }
    public void setTipoContrato(TipoContratoEnum tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    public Branch getFilal() {
        return filal;
    }
    public void setFilal(Branch filal) {
        this.filal = filal;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }
}
