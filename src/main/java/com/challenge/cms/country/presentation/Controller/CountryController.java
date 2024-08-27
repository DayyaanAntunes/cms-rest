package com.challenge.cms.country.presentation.Controller;

import com.challenge.cms.country.domain.command.CountryCommand;
import com.challenge.cms.country.domain.mapper.CountryMapper;
import com.challenge.cms.country.presentation.Json.CountryJson;
import com.challenge.cms.country.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryMapper countryMapper;
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryJson>> getAllCountries(@RequestParam(required = false) String sortBy,
                                                             @RequestParam(defaultValue = "ASC") String direction) {
        return ResponseEntity.ok(countryMapper.toJson(countryService.findAll(sortBy, direction)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryJson> getCountry(@PathVariable Long id) {
        return ResponseEntity.ok(countryMapper.toJson(countryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CountryJson> createCountry(@Valid @RequestBody CountryCommand countryCommand) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryMapper.toJson(countryService.create(countryCommand)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryJson> updateCountry(@PathVariable Long id, @Valid @RequestBody CountryCommand countryCommand) {
        return ResponseEntity.ok(countryMapper.toJson(countryService.update(id, countryCommand)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id){
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
