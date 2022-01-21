package com.example.backenddeveloperchallenge.country.service;

import com.example.backenddeveloperchallenge.country.domain.Country;
import com.example.backenddeveloperchallenge.country.domain.CountryQuery;
import com.example.backenddeveloperchallenge.country.domain.Region;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CountrySpecifiction {

    public Specification<Country> executeQuery(CountryQuery query){
        return findByName(query.getName())
                .and(findByArea(query.getArea()))
                .and(findByRegion(query.getRegion()))
                .and(findBySubRegion(query.getSubRegion()))
                .and(findByCapital(query.getCapital()));
    }

    public Specification<Country> findByName(String name){
        return (root, query, cb) -> name != null ? cb.like(cb.upper(root.get("name")),
                "%"+name.toUpperCase()+"%") : cb.and();
    }

    public Specification<Country> findByArea(Integer area){
        return (root, query, cb) -> area != null ? cb.equal(root.get("area"),area):cb.and();
    }

    public Specification<Country> findByRegion(Region region){
        return (root, query, cb) -> region != null ?
                cb.equal(root.get("region"),region):cb.and();
    }
    public Specification<Country> findBySubRegion(String subRegion){
        return (root, query, cb) -> subRegion != null ? cb.like(cb.upper(root.get("subRegion")),
                "%"+subRegion.toUpperCase()+"%") : cb.and();
    }
    public Specification<Country> findByCapital(String capital){
        return (root, query, cb) -> capital != null ? cb.like(cb.upper(root.get("capital")),
                "%"+capital.toUpperCase()+"%") : cb.and();
    }
}
