package br.com.employeesmanagement.infraestructure.controller;

import br.com.employeesmanagement.domain.model.Seller;
import br.com.employeesmanagement.domain.model.validators.DocumentValidatorFactory;
import br.com.employeesmanagement.domain.usecase.seller.GetSeller;
import br.com.employeesmanagement.domain.usecase.seller.RemoveSeller;
import br.com.employeesmanagement.domain.usecase.seller.SaveSeller;
import br.com.employeesmanagement.domain.usecase.seller.UpdateSeller;
import br.com.employeesmanagement.infraestructure.dto.SellerResponseDto;
import br.com.employeesmanagement.infraestructure.dto.SellerRequestDto;
import br.com.employeesmanagement.infraestructure.mapper.SellerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/branch/{branchId}/seller")
public class SellerController {

    private final SaveSeller saveSeller;
    private final GetSeller getSeller;
    private final UpdateSeller updateSeller;
    private final RemoveSeller removeSeller;

    public SellerController(SaveSeller saveSeller, GetSeller getSeller, UpdateSeller updateSeller, RemoveSeller removeSeller) {
        this.saveSeller = saveSeller;
        this.getSeller = getSeller;
        this.updateSeller = updateSeller;
        this.removeSeller = removeSeller;
    }

    @PostMapping()
    public ResponseEntity<Void> saveSellerRequest(@PathVariable("branchId") Long branchId,
                                                  @RequestBody SellerRequestDto requestDto) {

        Seller seller = SellerMapper
                .fromDto(DocumentValidatorFactory.createValidator(requestDto.cpfOuCnpj()), requestDto);

        seller = saveSeller.execute(branchId, seller);

        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest().path("/".concat(String.valueOf(seller.getMatricula())))
                        .buildAndExpand(seller.getMatricula())
                        .toUri())
                .build();
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<SellerResponseDto> getSellerRequest(@PathVariable("branchId") Long branchId,
                                                              @PathVariable("matricula") String matricula) {

        Seller seller = getSeller.execute(branchId, matricula);

        return ResponseEntity.ok().body(SellerMapper.toResponseDto(seller));
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Void> updateSellerRequest(@PathVariable("branchId") Long branchId,
                                                    @PathVariable("matricula") String matricula,
                                                    @RequestBody SellerRequestDto requestDto) {

        Seller seller = SellerMapper
                .fromDto(DocumentValidatorFactory.createValidator(requestDto.cpfOuCnpj()), requestDto);

        updateSeller.execute(branchId, matricula, seller);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> removeSellerRequest(@PathVariable("branchId") Long branchId,
                                                    @PathVariable("matricula") String matricula) {

        removeSeller.execute(branchId, matricula);
        return ResponseEntity.noContent().build();
    }

}
