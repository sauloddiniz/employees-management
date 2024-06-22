package br.com.employeesmanagement.infraestructure.dto;
public record SellerRequestDto(String nome, String dataNascimento, String cpfOuCnpj, String email,
                               String tipoContrato) {
}
