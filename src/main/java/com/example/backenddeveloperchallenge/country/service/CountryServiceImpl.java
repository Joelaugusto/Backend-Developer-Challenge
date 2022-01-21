package com.example.backenddeveloperchallenge.country.service;

import com.example.backenddeveloperchallenge.country.domain.Country;
import com.example.backenddeveloperchallenge.country.domain.CountryMapper;
import com.example.backenddeveloperchallenge.country.domain.CountryQuery;
import com.example.backenddeveloperchallenge.country.domain.CountryRequest;
import com.example.backenddeveloperchallenge.country.persistence.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;
    private final CountrySpecifiction specifiction;


    @Override
    public Page<Country> findAll(Pageable pageable, CountryQuery query) {
        return repository.findAll(specifiction.executeQuery(query), pageable);
    }

    @Override
    public Country findById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Country Not Found!"));
    }

    @Override
    public Country update(Long id, CountryRequest request) {
        Country country = findById(id);
        CountryMapper.INSTANCE.map(request, country);
        return repository.save(country);
    }

    @Override
    public void delete(Long id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Country Not Found!");
        }
    }

    @Override
    public Country create(CountryRequest request) {
        return repository.save(CountryMapper.INSTANCE.toEntity(request));
    }
}
