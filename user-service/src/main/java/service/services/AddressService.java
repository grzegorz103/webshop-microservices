package service.services;

import service.models.Address;

public interface AddressService {

    Address getByUser();

    Address update(Address address);

}
