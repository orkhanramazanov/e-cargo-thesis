package com.orkhan.web.out.ecargo.controller;

import com.orkhan.web.out.ecargo.message.request.AddCountry;
import com.orkhan.web.out.ecargo.entity.Country;
import com.orkhan.web.out.ecargo.service.CountryService;
import com.orkhan.web.out.ecargo.util.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class CountryController {

    @Autowired
    CountryService countryService;

    final String clientUrl = Client.clientUrl;

    @GetMapping("/country/getAll")
    @CrossOrigin(origins = clientUrl)
    public ResponseEntity<List<Country>> getAllCountries() throws Exception {
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }

    @PostMapping("/country/addCountry")
    @CrossOrigin(origins = clientUrl)
    public ResponseEntity<Country> addCountry(@Valid @RequestBody AddCountry addCountry) throws Exception {
        return new ResponseEntity<>(countryService.addCountry(addCountry.getCountryName()), HttpStatus.OK);
    }

    @DeleteMapping("/country/deleteById/{uid}")
    @CrossOrigin(origins = clientUrl)
    public ResponseEntity<List<Country>> deleteCountryById(@PathVariable("uid") Long uid) throws Exception {
        return new ResponseEntity<>(countryService.deleteCountry(uid), HttpStatus.OK);
    }

}
