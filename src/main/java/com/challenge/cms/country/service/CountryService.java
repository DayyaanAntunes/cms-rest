package com.challenge.cms.country.service;

import com.challenge.cms.country.domain.command.CountryCommand;
import com.challenge.cms.country.domain.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> findAll(String sortBy, String direction);
    Country create(CountryCommand countryCommand);
    Country update(Long id, CountryCommand countryCommand);
    Country findById(Long id);
    void delete(Long id);
    List<Country> findAllDeletedCountries();
    Country restoreCountry(Long id);
}
