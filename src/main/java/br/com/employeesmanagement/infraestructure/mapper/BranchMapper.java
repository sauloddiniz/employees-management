package br.com.employeesmanagement.infraestructure.mapper;

import br.com.employeesmanagement.domain.enums.TipoContratoEnum;
import br.com.employeesmanagement.domain.model.Branch;
import br.com.employeesmanagement.infraestructure.dto.BranchResponseDto;

public class BranchMapper {
    private BranchMapper() {}
    public static Branch fromResponseDto(BranchResponseDto requestDto) {
        return new Branch(requestDto.id(), requestDto.nome(), requestDto.cnpj(), requestDto.cidade(), requestDto.uf(),
                TipoContratoEnum.fromString(requestDto.tipo()), requestDto.ativo(), requestDto.dataCadastro(), requestDto.ultimaAtualizacao());
    }
}
