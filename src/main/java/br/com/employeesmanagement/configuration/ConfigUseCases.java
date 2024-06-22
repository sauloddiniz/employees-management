package br.com.employeesmanagement.configuration;

import br.com.employeesmanagement.domain.gateway.BranchClient;
import br.com.employeesmanagement.domain.gateway.SellerPersistence;
import br.com.employeesmanagement.domain.usecase.branch.GetBranch;
import br.com.employeesmanagement.domain.usecase.seller.GetSeller;
import br.com.employeesmanagement.domain.usecase.seller.RemoveSeller;
import br.com.employeesmanagement.domain.usecase.seller.SaveSeller;
import br.com.employeesmanagement.domain.usecase.seller.UpdateSeller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUseCases {

    @Bean
    public SaveSeller saveSeller(SellerPersistence sellerPersistence, GetBranch getBranch) {
        return new SaveSeller(sellerPersistence, getBranch);
    }

    @Bean
    public GetBranch getBranch(BranchClient branchClient) {
        return new GetBranch(branchClient);
    }

    @Bean
    public GetSeller getSeller(GetBranch getBranch, SellerPersistence sellerPersistence) {
        return new GetSeller(getBranch, sellerPersistence);
    }

    @Bean
    public UpdateSeller updateSeller(GetSeller getSeller, SaveSeller saveSeller) {
        return new UpdateSeller(getSeller, saveSeller);
    }

    @Bean
    public RemoveSeller removeSeller(GetSeller getSeller, SellerPersistence sellerPersistence) {
        return new RemoveSeller(getSeller, sellerPersistence);
    }

}
