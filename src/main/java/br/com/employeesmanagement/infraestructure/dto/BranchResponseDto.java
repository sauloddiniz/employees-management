package br.com.employeesmanagement.infraestructure.dto;

import java.time.LocalDate;

public record BranchResponseDto(Long id, String nome, String cnpj, String cidade, String uf,
                                String tipo, boolean ativo, LocalDate dataCadastro,
                                LocalDate ultimaAtualizacao) {
}
