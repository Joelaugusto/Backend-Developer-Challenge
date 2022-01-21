package com.example.backenddeveloperchallenge.country.service;

import com.example.backenddeveloperchallenge.country.domain.Country;
import com.example.backenddeveloperchallenge.country.domain.CountryQuery;
import com.example.backenddeveloperchallenge.country.domain.CountryRequest;
import com.example.backenddeveloperchallenge.country.persistence.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest extends BaseCountryTest {


    CountryServiceImpl countryService;

    @Mock
    private CountryRepository repository;

    @Mock
    private CountrySpecifiction specifiction;

    @BeforeEach
    void setUp() {
        countryService = new CountryServiceImpl(repository, specifiction);
    }

    @Test
    void findAll() {
        Specification<Country> countrySpecification = anyCountrySpecification();
        Page<Country> countryPage = anyPageOfCountries();
        CountryQuery query = anyCountryQuery();
        Mockito.when(specifiction.executeQuery(query)).thenReturn(countrySpecification);
        Mockito.when(repository.findAll(countrySpecification, Pageable.unpaged())).thenReturn(countryPage);
        assertEquals(countryPage, countryService.findAll(Pageable.unpaged(), query));
        Mockito.verify(specifiction).executeQuery(query);
        Mockito.verify(repository).findAll(countrySpecification, Pageable.unpaged());
    }

    @Test
    void findById() {
        Country country = anyCountry();
        Mockito.when(repository.findById(country.getId())).thenReturn(Optional.of(country));
        Country saved = countryService.findById(country.getId());
        Mockito.verify(repository).findById(country.getId());
        Mockito.verifyNoMoreInteractions(repository);
        assertEquals(country,saved);
    }

    @Test
    void update() {

        Country country = anyCountry();
        CountryRequest request = anyCountryRequest();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(country));
        Mockito.when(repository.save(country)).thenReturn(country);
        Country saved = countryService.update(1L, request);
        Mockito.verify(repository).findById(1L);
        Mockito.verify(repository).save(country);
        assertNotNull(saved);
    }

    @Test
    void delete() {
        Mockito.when(repository.existsById(1L)).thenReturn(true);
        countryService.delete(1L);
        Mockito.verify(repository).existsById(1L);
        Mockito.verify(repository).deleteById(1L);
    }

    @Test
    void create() {
        Country country = anyCountry();
        Mockito.when(repository.save(Mockito.any())).thenReturn(country);
        Country save = countryService.create(Mockito.any());
        Mockito.verify(repository).save(Mockito.any());
        assertNotNull(save);
    }
}