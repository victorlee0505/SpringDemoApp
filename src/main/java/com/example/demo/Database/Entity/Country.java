package com.example.demo.Database.Entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Country {
    String name;
    String nativeName;
    List<String> topLevelDomain;
    String alpha2Code;
    List<String> currencies;
    List<String> callingCodes;
    String capital;
    List<String> altSpellings;
    String relevance;
    String region;
    String subregion;
    List<String> language;
    List<String> languages;
    int population;
    List<Integer> latlng;
    String demonym;
    List<String> borders;
    int area;
    double gini;
    List<String> timezones;
}
