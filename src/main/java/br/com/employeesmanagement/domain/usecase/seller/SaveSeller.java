package br.com.employeesmanagement.domain.usecase.seller;

import br.com.employeesmanagement.domain.gateway.SellerPersistence;
import br.com.employeesmanagement.domain.model.Branch;
import br.com.employeesmanagement.domain.model.Seller;
import br.com.employeesmanagement.domain.usecase.branch.GetBranch;

public class SaveSeller {
    private final SellerPersistence sellerPersistence;
    private final GetBranch getBranch;
    public SaveSeller(SellerPersistence sellerPersistence, GetBranch getBranch) {
        this.sellerPersistence = sellerPersistence;
        this.getBranch = getBranch;
    }
    public Seller execute(Long branchId, Seller seller) {
        Branch branch = getBranch.execute(branchId);
        seller.setFilal(branch);
        seller = sellerPersistence.save(seller);
        seller.createMatricula();
        return sellerPersistence.save(seller);
    }

    public Seller execute(Seller seller) {
        seller.createMatricula();
        return sellerPersistence.save(seller);
    }
}
