package br.com.employeesmanagement.domain.usecase.seller;

import br.com.employeesmanagement.domain.gateway.SellerPersistence;
import br.com.employeesmanagement.domain.model.Seller;

public class RemoveSeller {
    private final GetSeller getSeller;
    private final SellerPersistence sellerPersistence;

    public RemoveSeller(GetSeller getSeller, SellerPersistence sellerPersistence) {
        this.getSeller = getSeller;
        this.sellerPersistence = sellerPersistence;
    }

    public void execute(Long branchId, String matricula) {
        Seller seller = getSeller.execute(branchId, matricula);
        sellerPersistence.remove(seller);
    }
}
