package service.controller;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import service.config.auth0.Auth0ManagementEndpoint;
import service.models.Address;
import service.services.AddressService;

import java.security.Principal;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    private Auth0ManagementEndpoint managementEndpoint;

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

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('read:users')")
    public Object getUsers() {
        Object users = managementEndpoint.getUsers();
        System.out.println(users);
        return users;
    }

}
