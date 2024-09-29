package com.challenge.cms.country.persistence;

import com.challenge.cms.country.domain.model.Country;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findAll(Sort sort);
    boolean existsByCapital(String capital);
    @Query(value = "SELECT * FROM countries WHERE is_deleted = true",
            nativeQuery = true)
    List<Country> findByIsDeleted();
    @Query(value = "SELECT * FROM countries WHERE is_deleted = true AND id = :deleted_id", nativeQuery = true)
    Country findDeletedById(@Param("deleted_id") Long id);
}
