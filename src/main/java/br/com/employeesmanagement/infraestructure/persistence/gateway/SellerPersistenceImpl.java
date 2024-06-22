package br.com.employeesmanagement.infraestructure.persistence.gateway;

import br.com.employeesmanagement.domain.gateway.SellerPersistence;
import br.com.employeesmanagement.domain.model.Seller;
import br.com.employeesmanagement.infraestructure.persistence.entity.SellerEntity;
import br.com.employeesmanagement.infraestructure.persistence.repository.SellerRepository;
import br.com.employeesmanagement.infraestructure.persistence.wraper.SellerWrapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SellerPersistenceImpl implements SellerPersistence {
    private final SellerRepository sellerRepository;

    public SellerPersistenceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller save(Seller seller) {
        SellerEntity sellerEntity =
                sellerRepository.save(SellerWrapper.toEntity(seller));
        return SellerWrapper.fromEntity(sellerEntity);
    }

    @Override
    public Optional<Seller> findByMatricula(String matricula) {
        return sellerRepository.findByMatricula(matricula)
                .map(SellerWrapper::fromEntity);
    }

    @Override
    public void remove(Seller seller) {
        sellerRepository.delete(SellerWrapper.toEntity(seller));
    }
}
