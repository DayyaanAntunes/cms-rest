package com.challenge.cms.country.domain.command;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CountryCommand(
        @NotBlank(message = "{country.name-missing}")
        String name,
        @NotBlank(message = "{country.capital-missing}")
        String capital,
        @NotBlank(message = "{country.region-missing}")
        String region,
        @NotBlank(message = "{country.sub-region-missing}")
        String subRegion,
        @NotNull(message = "{country.area-missing}")
        @Min(value = 1, message = "{country.invalid-area}")
        double area
){}
