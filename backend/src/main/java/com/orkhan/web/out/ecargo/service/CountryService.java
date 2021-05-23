package com.orkhan.web.out.ecargo.service;

import com.orkhan.web.out.ecargo.entity.Country;
import com.orkhan.web.out.ecargo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;


    public Country addCountry(String name) throws Exception {
        try {
            Country countryExists= countryRepository.getCountryByName(name);
            if(countryExists == null)
            {
                Country country = new Country();
                country.setName(name);
                return countryRepository.save(country);
            }
            else
            {
                throw new Exception("Country Already Exists");
            }

        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public List<Country>  deleteCountry(Long id) throws Exception {
        try {
            Optional<Country> country = countryRepository.findById(id);
            if (country.isPresent()) {
                countryRepository.deleteById(id);
                return countryRepository.findAll();
            }
            throw new Exception("Invalid Id");
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public List<Country>  getAllCountries() throws Exception {
        try {
            return countryRepository.findAll();

        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }

    }
}
