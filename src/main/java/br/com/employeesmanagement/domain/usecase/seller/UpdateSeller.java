package br.com.employeesmanagement.domain.usecase.seller;

import br.com.employeesmanagement.domain.model.Seller;
import br.com.employeesmanagement.domain.usecase.branch.GetBranch;

public class UpdateSeller {
    private final GetSeller getSeller;
    private final SaveSeller saveSeller;

    public UpdateSeller(GetSeller getSeller, SaveSeller saveSeller) {
        this.getSeller = getSeller;
        this.saveSeller = saveSeller;
    }

    public Seller execute(Long branchId, String matricula, Seller seller) {
        Seller sellerForUpdate = getSeller.execute(branchId, matricula);
        updateSeller(sellerForUpdate, seller);
        return saveSeller.execute(sellerForUpdate);
    }

    private void updateSeller(Seller sellerForUpdate, Seller seller) {
        sellerForUpdate.setNome(seller.getNome());
        sellerForUpdate.setDataNascimento(seller.getDataNascimento());
        sellerForUpdate.setCpfOuCnpj(seller.getCpfOuCnpj());
        sellerForUpdate.setEmail(seller.getEmail());
        sellerForUpdate.setTipoContrato(seller.getTipoContrato());
    }
}
