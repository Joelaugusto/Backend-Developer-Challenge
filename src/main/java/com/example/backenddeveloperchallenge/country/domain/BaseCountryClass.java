package com.example.backenddeveloperchallenge.country.domain;

import lombok.Data;

@Data
public abstract class BaseCountryClass {

    protected String name;
    protected String capital;
    protected Region region;
    protected String subRegion;
    protected Integer area;
}
