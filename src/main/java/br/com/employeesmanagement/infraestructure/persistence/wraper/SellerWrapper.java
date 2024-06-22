package br.com.employeesmanagement.infraestructure.persistence.wraper;

import br.com.employeesmanagement.domain.model.Seller;
import br.com.employeesmanagement.domain.model.validators.DocumentValidator;
import br.com.employeesmanagement.infraestructure.dto.SellerResponseDto;
import br.com.employeesmanagement.infraestructure.dto.SellerRequestDto;
import br.com.employeesmanagement.infraestructure.persistence.entity.SellerEntity;

import java.util.Optional;

public class SellerWrapper {
    private SellerWrapper() {
    }

    public static SellerEntity toEntity(Seller seller) {
        return SellerEntity.builder()
                .id(Optional.ofNullable(seller.getId()).map(Long::valueOf).orElse(null))
                .cpfOuCnpj(seller.getCpfOuCnpj())
                .dataNascimento(seller.getDataNascimento())
                .email(seller.getEmail())
                .filialId(seller.getFilal().getId())
                .nome(seller.getNome())
                .tipoContrato(seller.getTipoContrato())
                .matricula(seller.getMatricula())
                .build();
    }

    public static Seller fromEntity(SellerEntity sellerEntity) {
        return new Seller(sellerEntity.getId(), sellerEntity.getNome(), sellerEntity.getMatricula(),
                sellerEntity.getDataNascimento(), sellerEntity.getCpfOuCnpj(), sellerEntity.getEmail(),
                sellerEntity.getTipoContrato(), sellerEntity.getFilialId());
    }

    public static Seller fromDto(DocumentValidator documentValidator, SellerRequestDto requestDto) {
        return new Seller(documentValidator, requestDto.nome(), requestDto.dataNascimento(), requestDto.cpfOuCnpj(),
                requestDto.email(), requestDto.tipoContrato());

    }

    public static SellerResponseDto toResponseDto(Seller seller) {
        return new SellerResponseDto(seller.getMatricula(), seller.getNome(),
                Optional.ofNullable(seller.getDataNascimento()).map(Object::toString).orElse(null),
                seller.getEmail(), seller.getCpfOuCnpj(),
                seller.getTipoContrato().getTipo(), seller.getFilal());
    }
}
