package br.com.employeesmanagement.infraestructure.client;

import br.com.employeesmanagement.domain.gateway.BranchClient;
import br.com.employeesmanagement.domain.model.Branch;
import br.com.employeesmanagement.infraestructure.persistence.wraper.BranchWrapper;
import br.com.employeesmanagement.infraestructure.dto.BranchResponseDto;
import br.com.employeesmanagement.infraestructure.exception.BranchNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchClientImpl implements BranchClient {
    private final BranchClientRequest branchClientRequest;

    public BranchClientImpl(BranchClientRequest branchClientRequest) {
        this.branchClientRequest = branchClientRequest;
    }

    @Override
    public Branch getBranch(Long branchId) {
        BranchResponseDto responseEntity =
                Optional.ofNullable(branchClientRequest.getBranch(branchId).getBody())
                        .orElseThrow(() -> new BranchNotFoundException("Filial não encontrada"));
        return BranchWrapper.fromResponseDto(responseEntity);
    }
}
