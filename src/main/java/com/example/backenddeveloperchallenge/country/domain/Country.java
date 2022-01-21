package com.example.backenddeveloperchallenge.country.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "countries")
@Getter
@Setter
@ToString
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, length = 20)
    private String name;

    @Column(name = "capital", length = 50)
    private String capital;

    @Enumerated(EnumType.STRING)
    @Column(name = "region")
    private Region region;

    @Column(name = "sub_region", length = 50)
    private String subRegion;

    @Column(name = "area")
    private Integer area;

}
