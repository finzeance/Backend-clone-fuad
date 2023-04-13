package id.co.mii.serverapp.controllers;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.services.CountryService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('READ_ADMIN', 'READ_USER')")
    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id) {
        return countryService.getById(id);
    }

    // without dto
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Country create(@RequestBody Country country) {
        return countryService.create(country);
    }

    // with dto
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping("/dto")
    public Country createWithDTO(@RequestBody CountryRequest countryRequest) {
        return countryService.createWithDTO(countryRequest);
    }

    // with modelmapper
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping("/dto-mm")
    public Country createWithModelMapper(
            @RequestBody CountryRequest countryRequest
    ) {
        return countryService.createWithModelMapper(countryRequest);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public Country update(
            @PathVariable Integer id,
            @RequestBody Country country
    ) {
        return countryService.update(id, country);
    }

    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id) {
        return countryService.delete(id);
    }
}
