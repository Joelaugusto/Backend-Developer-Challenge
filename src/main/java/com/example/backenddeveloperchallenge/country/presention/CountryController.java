package com.example.backenddeveloperchallenge.country.presention;

import com.example.backenddeveloperchallenge.country.domain.CountryMapper;
import com.example.backenddeveloperchallenge.country.domain.CountryQuery;
import com.example.backenddeveloperchallenge.country.domain.CountryRequest;
import com.example.backenddeveloperchallenge.country.domain.CountryResponse;
import com.example.backenddeveloperchallenge.country.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<Page<CountryResponse>> fetchAll(Pageable pageable, CountryQuery query){
        return ResponseEntity.ok(CountryMapper.INSTANCE
                .toResponse(countryService.findAll(pageable,query)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> getCountry(@PathVariable Long id) {
        return ResponseEntity.ok(CountryMapper.INSTANCE.toResponse(countryService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CountryResponse> createCountry(@RequestBody CountryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CountryMapper.INSTANCE.toResponse(countryService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> updateCountry(@PathVariable Long id, @RequestBody CountryRequest request){
        return ResponseEntity.ok(CountryMapper.INSTANCE
                .toResponse(countryService.update(id,request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id){
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
