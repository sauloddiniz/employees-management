package br.com.employeesmanagement.infraestructure.controller;

import br.com.employeesmanagement.domain.enums.TipoContratoEnum;
import br.com.employeesmanagement.domain.model.validators.DocumentValidator;
import br.com.employeesmanagement.domain.model.validators.DocumentValidatorFactory;
import br.com.employeesmanagement.infraestructure.client.BranchClientRequest;
import br.com.employeesmanagement.infraestructure.dto.BranchResponseDto;
import br.com.employeesmanagement.infraestructure.persistence.entity.SellerEntity;
import br.com.employeesmanagement.infraestructure.persistence.repository.SellerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchClientRequest branchClientRequest;

    @SpyBean
    private DocumentValidatorFactory documentValidatorFactory;

    @SpyBean
    private SellerRepository sellerRepository;

    @Test
    @DisplayName("Deve obter sucesso ao cadastrar novo vendedor e criar matricula")
    void mustBeSuccessfulWhenRegisteringNewSalesperson() throws Exception {

        final String url = "/branch/1/seller";
        final String jsonCompleteSeller = """
                {
                    "cpfOuCnpj" : "31.679.832/0001-00",
                    "nome" : "Saulo Dias",
                    "email" : "sauloddiniz@gmail.com",
                    "dataNascimento" : "1986-02-07",
                    "tipoContrato" : "Pessoa Juridica"
                }
                """;

        when(branchClientRequest.getBranch(Mockito.anyLong()))
                .thenReturn(getBranchResponseDtoResponseEntity());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post(url)
                        .content(jsonCompleteSeller)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        header().exists("Location"))
                .andExpect(
                        header().string("Location", "http://localhost/branch/1/seller/1-PJ"))
                .andExpect(
                        status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("Deve obter sucesso ao obter vendedor")
    void mustBeSuccessfulWhenGetSalesperson() throws Exception {

        final String url = "/branch/1/seller/1-PJ";
        Optional<SellerEntity> sellerEntity = Optional.of(new SellerEntity(1L, "1-PJ", "Bruce",
                LocalDate.parse("2020-01-01"), "31679832000100", "bruce@bat.com.br",
                TipoContratoEnum.PESSOA_JURIDICA, 1L));

        String jsonResponse = """
                    {"matricula":"1-PJ","nome":"Bruce","dataNascimento":"2020-01-01","email":"bruce@bat.com.br","cpfOuCnpj":"31679832000100","tipoContratacao":"Pessoa Jurídica",
                    "filial":{"id":1,"nome":"Casas Bahia","cnpj":"98.284.403/0001-04","cidade":"Coronel Fabriciano","uf":"MG","tipo":"PESSOA_JURIDICA","ativo":true,"dataCadastro":"2020-01-01","ultimaAtualizacao":"2021-01-01"}}
                """;

        when(branchClientRequest.getBranch(Mockito.anyLong()))
                .thenReturn(getBranchResponseDtoResponseEntity());
        doReturn(sellerEntity).when(sellerRepository)
                .findByMatricula(anyString());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        content().json(jsonResponse))
                .andExpect(
                        status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Deve lancar excecao usuario nao encontrado, id da filial diferente com do vendedor")
    void shouldThrowExceptionUserNotFoundBranchIdDifferentFromSeller() throws Exception {

        final String url = "/branch/1/seller/1-PJ";
        Optional<SellerEntity> sellerEntity = Optional.of(new SellerEntity(1L, "1-PJ", "Bruce",
                LocalDate.parse("2020-01-01"), "31679832000100", "bruce@bat.com.br",
                TipoContratoEnum.PESSOA_JURIDICA, 2L));

        when(branchClientRequest.getBranch(Mockito.anyLong()))
                .thenReturn(getBranchResponseDtoResponseEntity());
        doReturn(sellerEntity).when(sellerRepository)
                .findByMatricula(anyString());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("Deve obter sucesso ao atualizar vendedor")
    void mustBeSuccessfulWhenUpdateSalesperson() throws Exception {

        final String url = "/branch/1/seller/1-PJ";
        Optional<SellerEntity> sellerEntity = Optional.of(new SellerEntity(1L, "1-PJ", "Bruce",
                LocalDate.parse("2020-01-01"), "31679832000100", "bruce@bat.com.br",
                TipoContratoEnum.PESSOA_JURIDICA, 1L));

        final String jsonToUpdate = """
                {
                    "cpfOuCnpj" : "858.035.800-07",
                    "nome" : "Saulo Dias",
                    "email" : "sauloddiniz@gmail.com",
                    "dataNascimento" : "1986-02-07",
                    "tipoContrato" : "Outsourcing"
                }
                """;

        when(branchClientRequest.getBranch(Mockito.anyLong()))
                .thenReturn(getBranchResponseDtoResponseEntity());
        doReturn(sellerEntity).when(sellerRepository)
                .findByMatricula(anyString());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put(url)
                        .content(jsonToUpdate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Deve obter sucesso ao remover vendedor")
    void mustBeSuccessfulWhenDeleteSalesperson() throws Exception {

        final String url = "/branch/1/seller/1-PJ";

        Optional<SellerEntity> sellerEntity = Optional.of(new SellerEntity(1L, "1-PJ", "Bruce",
                LocalDate.parse("2020-01-01"), "31679832000100", "bruce@bat.com.br",
                TipoContratoEnum.PESSOA_JURIDICA, 1L));

        when(branchClientRequest.getBranch(anyLong()))
                .thenReturn(getBranchResponseDtoResponseEntity());
        doReturn(sellerEntity).when(sellerRepository)
                .findByMatricula(anyString());
        doNothing().when(sellerRepository)
                .delete(any(SellerEntity.class));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNoContent())
                .andDo(print());
    }

    @Test
    @DisplayName("Deve obter error ao nao encontrar filial na API")
    void shouldGetAnErrorWhenTheAPICantFindBranch() throws Exception {

        final String url = "/branch/1/seller/1-PJ";

        when(branchClientRequest.getBranch(anyLong()))
                .thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().isNotFound())
                .andDo(print());
    }

    private static ResponseEntity<BranchResponseDto> getBranchResponseDtoResponseEntity() {
        BranchResponseDto responseDto =
                new BranchResponseDto(1L, "Casas Bahia", "98.284.403/0001-04",
                        "Coronel Fabriciano", "MG", "PESSOA JURIDICA", true,
                        LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01"));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
