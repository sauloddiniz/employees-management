package br.com.employeesmanagement.infraestructure.persistence.entity;

import br.com.employeesmanagement.domain.enums.TipoContratoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "SELLER")
public class SellerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "MATRICULA", unique = true)
    private String matricula;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;
    @Column(name = "CPF_CNPJ", unique = true)
    private String cpfOuCnpj;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "TIPO_CONTRATO")
    @Enumerated(EnumType.STRING)
    private TipoContratoEnum tipoContrato;
    @Column(name = "BRANCH_ID")
    private Long filialId;
}

