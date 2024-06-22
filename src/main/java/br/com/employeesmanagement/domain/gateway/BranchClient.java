package br.com.employeesmanagement.domain.gateway;

import br.com.employeesmanagement.domain.model.Branch;

public interface BranchClient {
    Branch getBranch(Long branchId);
}
