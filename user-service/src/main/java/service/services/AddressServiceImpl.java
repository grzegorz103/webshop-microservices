package service.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import service.dao.AddressRepository;
import service.models.Address;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address getByUser() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return addressRepository.findByUserId(userId)
                .orElseGet(() -> addressRepository.save(Address.builder().userId(userId).build()));
    }

    @Override
    public Address update(Address address) {
        address.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        return addressRepository.save(address);
    }
}
