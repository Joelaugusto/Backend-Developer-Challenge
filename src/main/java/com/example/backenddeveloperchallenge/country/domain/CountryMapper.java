package com.example.backenddeveloperchallenge.country.domain;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public abstract class CountryMapper {

    public static final CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    public abstract CountryResponse toResponse(Country country);
    public abstract Country toEntity(CountryRequest countryRequest);
    public abstract void map(CountryRequest countryRequest,@MappingTarget Country country);

    public Page<CountryResponse> toResponse(Page<Country> countryPage) {
        return countryPage.map(this::toResponse);
    }
}
