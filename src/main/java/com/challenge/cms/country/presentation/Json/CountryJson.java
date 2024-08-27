package com.challenge.cms.country.presentation.Json;

public record CountryJson(
    Long id,
    String name,
    String capital,
    String region,
    String subRegion,
    double area
){}
