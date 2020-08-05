package service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import service.models.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByUserId(String userId);

}
