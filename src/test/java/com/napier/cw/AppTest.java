package com.napier.cw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;
    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void displayCountriesTestNull()
    {
        app.displayCountries(null);
    }
    @Test
    void displayCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.displayCountries(countries);
    }
    @Test
    void displayCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        app.displayCountries(countries);
    }
    @Test
    void displayCountries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.setCode("CHN");
        country.setName("China");
        country.setContinent("Asia");
        country.setRegion("Eastern Asia");
        country.setPopulation(1277558000);
        country.setCapital("Peking");
        countries.add(country);
        app.displayCountries(countries);
    }
    @Test
    void displayCitiesTestNull()
    {
        app.displayCities(null);
    }
    @Test
    void displayCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<>();
        app.displayCities(cities);
    }
    @Test
    void displayCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(null);
        app.displayCities(cities);
    }
    @Test
    void displayCities()
    {
        ArrayList<City> cities = new ArrayList<>();
        City city = new City();
        city.setCname("Mogok");
        city.setCccode("MYA");
        city.setCd("Pyin Oo Lwin");
        city.setCpop(100000);
        cities.add(city);
        app.displayCities(cities);
    }
    @Test
    void displayCapitalCityTestNull()
    {
        app.displayCapitalCity(null);
    }
    @Test
    void displayCapitalCityTestEmpty()
    {
        ArrayList<City> capitals = new ArrayList<>();
        app.displayCapitalCity(capitals);
    }
    @Test
    void displayCapitalCityTestContainsNull()
    {
        ArrayList<City> capitals = new ArrayList<City>();
        capitals.add(null);
        app.displayCapitalCity(capitals);
    }
    @Test
    void displayCapitalCity()
    {
        ArrayList<City> capitals = new ArrayList<>();
        City capital = new City();
        //Using getInt for integer data, getString for string data
        capital.setCname("Nay Pyi Taw");
        capital.setCccode("MYA");
        capital.setCpop(100000);
        //Add the data to the apcu array
        capitals.add(capital);
        app.displayCapitalCity(capitals);


    }
}
