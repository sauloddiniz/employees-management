package br.com.employeesmanagement.domain.usecase.branch;

import br.com.employeesmanagement.domain.gateway.BranchClient;
import br.com.employeesmanagement.domain.model.Branch;

public class GetBranch {
    private final BranchClient branchClient;
    public GetBranch(BranchClient branchClient) {
        this.branchClient = branchClient;
    }
    public Branch execute(Long id) {
        return branchClient.getBranch(id);
    }
}
