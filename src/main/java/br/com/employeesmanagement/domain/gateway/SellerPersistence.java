package br.com.employeesmanagement.domain.gateway;

import br.com.employeesmanagement.domain.model.Seller;

import java.util.Optional;

public interface SellerPersistence {
    Seller save(Seller seller);
    Optional<Seller> findByMatricula(String matricula);
    void remove(Seller seller);
}
