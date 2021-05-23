package com.orkhan.web.out.ecargo.service;

import com.orkhan.web.out.ecargo.entity.Country;
import com.orkhan.web.out.ecargo.repository.CountryRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class CountryServiceTests {

    @InjectMocks
    CountryService countryService;

    @Mock
    CountryRepository countryRepository;

    @Test
    public void addCountry() throws Exception {
        Country country = new Country();
        country.setName("test");

        when(countryRepository.getCountryByName("test")).thenReturn(null);
        when(countryRepository.save(country)).thenReturn(country);

        Country country1 = countryService.addCountry("test");
        if(country1 !=null)
        assertEquals(country.getName(),country1.getName());
    }

    @Test
    public void addCountryReturnsError() throws Exception {
        Country country = new Country();
        country.setName("test");

        when(countryRepository.getCountryByName("test")).thenReturn(country);
        when(countryRepository.save(country)).thenReturn(country);

        try {
            Country country1 = countryService.addCountry("test");
        }
        catch (Exception e) {
            assertEquals(e.getMessage(),"Country Already Exists");
        }


    }

    @Test
    public void deleteCountry() throws Exception {
        Country country = new Country();
        country.setName("test");

        when(countryRepository.findById(1L)).thenReturn(java.util.Optional.of(country));
        when(countryRepository.findAll()).thenReturn(new ArrayList<Country>(Arrays.asList(country)));

        try {
            List<Country> country1 = countryService.deleteCountry(1L);
            assertEquals(country1.get(0).getName(),country.getName());
        }
        catch (Exception e) {
        }


    }

    @Test
    public void deleteCountryReturnsError() throws Exception {
        Country country = new Country();
        country.setName("test");

        Country country2 =null;

        when(countryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(country2));
        when(countryRepository.findAll()).thenReturn(new ArrayList<Country>(Arrays.asList(country)));

        try {
            List<Country> country1 = countryService.deleteCountry(1L);
        }
        catch (Exception e) {
            assertEquals(e.getMessage(),"Invalid Id");
        }


    }

    @Test
    public void getAllCountries() throws Exception {
        Country country = new Country();
        country.setName("test");

        when(countryRepository.findAll()).thenReturn(new ArrayList<Country>(Arrays.asList(country)));

        try {
            List<Country> country1 = countryService.getAllCountries();
            assertEquals(country1.get(0).getName(),country.getName());
        }
        catch (Exception e) {
        }


    }

}
