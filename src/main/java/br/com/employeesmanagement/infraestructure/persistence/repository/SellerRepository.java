package br.com.employeesmanagement.infraestructure.persistence.repository;

import br.com.employeesmanagement.domain.model.Seller;
import br.com.employeesmanagement.infraestructure.persistence.entity.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
    Optional<SellerEntity> findByMatricula(String matricula);
}
