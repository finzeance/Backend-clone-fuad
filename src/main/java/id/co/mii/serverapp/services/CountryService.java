package id.co.mii.serverapp.services;

import id.co.mii.serverapp.models.Country;
import id.co.mii.serverapp.models.Region;
import id.co.mii.serverapp.models.dto.requests.CountryRequest;
import id.co.mii.serverapp.repositories.CountryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CountryService {
    
    @Autowired
    private CountryRepository countryRepository;
    private RegionService regionService;
    private ModelMapper modelMapper;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getById(Integer id) {
        return countryRepository
                .findById(id)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found!!")
                );
    }

    // without dto
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    // with dto
    public Country createWithDTO(CountryRequest countryRequest) {
        Country country = new Country();
        country.setCode(countryRequest.getCode());
        country.setName(countryRequest.getName());

        Region region = regionService.getById(countryRequest.getRegionId());
        country.setRegion(region);
        return countryRepository.save(country);
    }

    // with dto by modelmapper
    public Country createWithModelMapper(CountryRequest countryRequest) {
        Country country = modelMapper.map(countryRequest, Country.class);
        country.setRegion(regionService.getById(countryRequest.getRegionId()));
        return countryRepository.save(country);
    }

    public Country update(Integer id, Country country) {
        getById(id); // method getById
        country.setId(id);
        return countryRepository.save(country);
    }

    public Country delete(Integer id) {
        Country country = getById(id);
        countryRepository.delete(country);
        return country;
    }
}
