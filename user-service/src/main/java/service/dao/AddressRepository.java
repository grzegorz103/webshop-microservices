package service.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import service.models.Address;

import java.util.Optional;

public interface AddressRepository extends ElasticsearchRepository<Address, Long> {

    Optional<Address> findByUserId(String userId);

}
