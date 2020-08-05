package service.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import service.models.Address;
import service.services.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Address getUserAddress() {
        return addressService.getByUser();
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Address update(@PathVariable("id") Long id,
                          @RequestBody Address address) {
        return addressService.update(address);
    }

}
