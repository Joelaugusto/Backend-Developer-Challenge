package com.example.backenddeveloperchallenge.country.service;

import com.example.backenddeveloperchallenge.country.domain.*;
import com.github.javafaker.Faker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCountryTest {

    private final Faker faker = new Faker();

    protected Faker faker(){
        return this.faker;
    }

    public Country anyCountry(){
        Country country = new Country();
        country.setArea(faker.number().numberBetween(1, 100000));
        country.setCapital(faker.country().capital());
        country.setRegion(faker.options().option(Region.AFRICA, Region.AMERICA, Region.ASIA, Region.EUROPE, Region.OCEANIA));
        country.setName(faker.country().name());
        country.setSubRegion(faker.lorem().word());
        country.setId(faker.number().randomNumber());
        return country;
    }

    public CountryRequest anyCountryRequest(){
        CountryRequest countryRequest = new CountryRequest();
        countryRequest.setArea(faker.number().numberBetween(1, 100000));
        countryRequest.setCapital(faker.country().capital());
        countryRequest.setRegion(faker.options().option(Region.AFRICA, Region.AMERICA, Region.ASIA, Region.EUROPE, Region.OCEANIA));
        countryRequest.setName(faker.country().name());
        countryRequest.setSubRegion(faker.lorem().word());
        return countryRequest;
    }

    public CountryResponse anyCountryResponse(){
        CountryResponse countryResponse = new CountryResponse();
        countryResponse.setArea(faker.number().numberBetween(1, 100000));
        countryResponse.setCapital(faker.country().capital());
        countryResponse.setRegion(faker.options().option(Region.AFRICA, Region.AMERICA, Region.ASIA, Region.EUROPE, Region.OCEANIA));
        countryResponse.setName(faker.country().name());
        countryResponse.setSubRegion(faker.lorem().word());
        countryResponse.setId(faker.number().randomNumber());
        return countryResponse;
    }

    public CountryQuery anyCountryQuery(){
        CountryQuery query = new CountryQuery();
        query.setArea(faker.number().numberBetween(1, 100000));
        query.setCapital(faker.country().capital());
        query.setRegion(faker.options().option(Region.AFRICA, Region.AMERICA, Region.ASIA, Region.EUROPE, Region.OCEANIA));
        query.setName(faker.country().name());
        query.setSubRegion(faker.lorem().word());
        return query;
    }

    public List<Country> anyListOfCountries(){
        List<Country> countries = new ArrayList<>();
        countries.add(anyCountry());
        countries.add(anyCountry());
        countries.add(anyCountry());
        return countries;
    }

    public Page<Country> anyPageOfCountries(){
        return new PageImpl<>(anyListOfCountries());
    }

    public List<CountryResponse> anyListOfCountriesResponse(){
        List<CountryResponse> countries = new ArrayList<>();
        countries.add(anyCountryResponse());
        countries.add(anyCountryResponse());
        countries.add(anyCountryResponse());
        return countries;
    }

    public Page<CountryResponse> anyPageOfCountriesResponse(){
        return new PageImpl<>(anyListOfCountriesResponse());
    }

    public Specification<Country> anyCountrySpecification(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.and();
    }

}
