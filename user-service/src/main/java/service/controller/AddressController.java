package service.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.models.Address;
import service.services.AddressService;

import java.security.Principal;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Address getUserAddress(@AuthenticationPrincipal Principal principal) {
        System.out.println("Scopy:");
        for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            System.out.println(authority.getAuthority());
        }
        return addressService.getByUser();
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public Address update(@PathVariable("id") String id,
                          @RequestBody Address address) {

        return addressService.update(address);
    }

}
