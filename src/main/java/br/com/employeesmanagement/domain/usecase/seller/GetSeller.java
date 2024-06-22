package br.com.employeesmanagement.domain.usecase.seller;

import br.com.employeesmanagement.domain.exception.SellerNotFoundException;
import br.com.employeesmanagement.domain.gateway.SellerPersistence;
import br.com.employeesmanagement.domain.model.Branch;
import br.com.employeesmanagement.domain.model.Seller;
import br.com.employeesmanagement.domain.usecase.branch.GetBranch;

import java.util.Optional;
public class GetSeller {

    private final GetBranch getBranch;
    private final SellerPersistence sellerPersistence;

    public GetSeller(GetBranch getBranch, SellerPersistence sellerPersistence) {
        this.getBranch = getBranch;
        this.sellerPersistence = sellerPersistence;
    }

    public Seller execute(Long branchId, String matricula) {
        Branch branch = getBranch.execute(branchId);
        return sellerPersistence.findByMatricula(matricula)
                .flatMap(sellerFound -> validBranch(branch, sellerFound))
                .orElseThrow(() -> new SellerNotFoundException("Vendedor não encontrado"));
    }

    private Optional<Seller> validBranch(Branch branch, Seller sellerFound) {
        if (sellerFound.getFilal() != null && sellerFound.getFilal().getId().equals(branch.getId())) {
            sellerFound.setFilal(branch);
            return Optional.of(sellerFound);
        } else {
            return Optional.empty();
        }
    }


}
