package com.example.backenddeveloperchallenge.country.service;

import com.example.backenddeveloperchallenge.country.domain.Country;
import com.example.backenddeveloperchallenge.country.domain.CountryQuery;
import com.example.backenddeveloperchallenge.country.domain.CountryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

    Page<Country> findAll(Pageable pageable, CountryQuery query);
    Country findById(Long id);
    Country update(Long id, CountryRequest request);
    void delete(Long id);
    Country create(CountryRequest request);

}
