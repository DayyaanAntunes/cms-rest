package com.challenge.cms.country.persistence;

import com.challenge.cms.country.domain.model.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findAll(Sort sort);
    boolean existsByCapital(String capital);
}
