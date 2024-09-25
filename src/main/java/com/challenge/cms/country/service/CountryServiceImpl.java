package com.challenge.cms.country.service;

import com.challenge.cms.configuration.exception.ResponseException;
import com.challenge.cms.country.domain.command.CountryCommand;
import com.challenge.cms.country.domain.mapper.CountryMapper;
import com.challenge.cms.country.domain.model.Country;
import com.challenge.cms.country.persistence.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<Country> findAll(String orderBy, String direction) {
        Sort sort = sortParamValidate(orderBy, direction);
        return countryRepository.findAll(sort);
    }

    private Sort sortParamValidate(String orderBy, String direction) {
        if (orderBy == null || orderBy.isEmpty())
            return Sort.unsorted();

        Sort.Direction sortDirection;
        List<String> allowedSortBy = Arrays.asList("name", "capital", "region", "suRegion", "area");

        try {
            sortDirection = Sort.Direction.fromString(direction == null ? "ASC" : direction);
        } catch (IllegalArgumentException e) {
            throw new ResponseException("country.invalid-sort-param", HttpStatus.BAD_REQUEST);
        }

        Optional<String> orderByFiled = allowedSortBy.stream().filter(item -> item.equalsIgnoreCase(orderBy)).findFirst();
        if (orderByFiled.isPresent()){
            return Sort.by(sortDirection, orderByFiled.get());
        } else {
            throw new ResponseException("country.invalid-sort-param", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Country create (CountryCommand countryCommand){
        Country country = countryMapper.toCountry(countryCommand);
        try {
            return countryRepository.save(country);
        } catch (DataIntegrityViolationException exception){
            throw new ResponseException("country.already-exists", HttpStatus.CONFLICT);
        }
    }

    @Override
    public Country update(Long id, CountryCommand countryCommand) {
        Country country = findById(id);
        countryMapper.updateCountry(countryCommand, country);
        return countryRepository.save(country);
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new ResponseException("country.not-found", HttpStatus.NOT_FOUND));
    }

    @Override
    public void delete(Long id) {
        if(!countryRepository.existsById(id))
            throw new ResponseException("country.not-found", HttpStatus.NOT_FOUND);
        countryRepository.deleteById(id);
    }
}
