package com.challenge.cms.country.domain.mapper;

import com.challenge.cms.country.domain.command.CountryCommand;
import com.challenge.cms.country.domain.model.Country;
import com.challenge.cms.country.presentation.Json.CountryJson;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryJson toJson(Country country);
    List<CountryJson> toJson(List<Country> countries);
    Country toCountry(CountryCommand countryCommand);
    void updateCountry(CountryCommand countryCommand, @MappingTarget Country country);
}
