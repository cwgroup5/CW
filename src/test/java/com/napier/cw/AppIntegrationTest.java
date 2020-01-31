package com.napier.cw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;




import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }
    @Test
    void testDisplayCities() {
//        ArrayList<City> cities = new ArrayList<>();
        City city = new City();

        city.setCname("Seoul");
        city.setCccode("KOR");
        city.setCd("Seoul");
        city.setCpop(3998899);

        assertEquals(city.getCccode(), "KOR");
        assertEquals(city.getCname(), "Seoul");
        assertEquals(city.getCd(), "Seoul");
        assertEquals(city.getCpop(), 3998899);

//        cities.add(city);

    }
    @Test
    void testDisplayCountries()
    {
//        ArrayList<City> cities = new ArrayList<>();
        Country country= new Country();

        country.setCode("KOR");
        country.setName("Korea");
        country.setContinent("Asia");
        country.setRegion("Eastern Asia");
        country.setPopulation(399988899);
        country.setCapital("Seoul");

        assertEquals(country.getCode(),"KOR");
        assertEquals(country.getName(),"Korea");
        assertEquals(country.getContinent(),"Asia");
        assertEquals(country.getRegion(),"Eastern Asia");
        assertEquals(country.getPopulation(),399988899);
        assertEquals(country.getCapital(),"Seoul");

    }
    @Test
    void testDisplayCapitalCities()
    {
//        ArrayList<City> cities = new ArrayList<>();
        City capital= new City();

        capital.setCname("Seoul");
        capital.setCccode("KOR");

        capital.setCpop(3998899);

        assertEquals(capital.getCccode(), "KOR");
        assertEquals(capital.getCname(), "Seoul");

        assertEquals(capital.getCpop(), 3998899);


    }
}
