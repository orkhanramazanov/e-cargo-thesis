package com.orkhan.web.out.ecargo.controller;



import com.orkhan.web.out.ecargo.entity.Country;
import com.orkhan.web.out.ecargo.message.request.AddCountry;
import com.orkhan.web.out.ecargo.service.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class CountryControllerTests {

    @InjectMocks
    CountryController countryController;

    @Mock
    CountryService countryService;

    @Before
    public void init() {
        countryService = mock(CountryService.class);
    }

    @Test
    public void getAllCountries() throws Exception {
        when(countryService.getAllCountries()).thenReturn(new ArrayList<Country>());
        ResponseEntity<?> responseMessage =  countryController.getAllCountries();
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void addCountry() throws Exception {
        AddCountry addCountry = new AddCountry();
        addCountry.setCountryName("test");
        when(countryService.addCountry("test")).thenReturn(new Country());
        ResponseEntity<?> responseMessage = countryController.addCountry(addCountry);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void deleteCountryById() throws Exception {
        Country country = new Country();
        country.setName("test");
        when(countryService.deleteCountry(1L)).thenReturn(new ArrayList<Country>());
        ResponseEntity<?> responseMessage =  countryController.deleteCountryById(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }


}
