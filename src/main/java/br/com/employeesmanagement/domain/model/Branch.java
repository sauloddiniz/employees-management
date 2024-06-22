package br.com.employeesmanagement.domain.model;

import br.com.employeesmanagement.domain.enums.TipoContratoEnum;

import java.time.LocalDate;

public class Branch {
    private Long id;
    private String nome;
    private String cnpj;
    private String cidade;
    private String uf;
    private TipoContratoEnum tipo;
    private boolean ativo;
    private LocalDate dataCadastro;
    private LocalDate ultimaAtualizacao;

    public Branch(Long id, String nome, String cnpj, String cidade, String uf, TipoContratoEnum tipo, boolean ativo, LocalDate dataCadastro, LocalDate ultimaAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.uf = uf;
        this.tipo = tipo;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Branch() {
    }

    public Branch(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public TipoContratoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoContratoEnum tipo) {
        this.tipo = tipo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDate ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}
