package br.com.employeesmanagement.infraestructure.dto;

import br.com.employeesmanagement.domain.model.Branch;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SellerResponseDto(String matricula, String nome, String dataNascimento, String email,
                                String cpfOuCnpj, String tipoContratacao, Branch filial) {
}
