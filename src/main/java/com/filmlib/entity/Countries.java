package com.filmlib.entity;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Countries {

    public static List<String> allCountries() {
        return Stream.of(Locale.getISOCountries())
                .map(countryCode -> new Locale("", countryCode))
                .map(Locale::getDisplayCountry)
                .collect(Collectors.toList());
    }
}
