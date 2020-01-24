package com.napier.cw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void testDisplayCities()
    {
//        ArrayList<City> cities = new ArrayList<>();
       City city= new City();

        city.setCname("Seoul");
        city.setCccode("KOR");
        city.setCd("Seoul");
        city.setCpop(3998899);
        app.getCity(2);
        
        assertEquals(city.getCccode(),"KOR");
        assertEquals(city.getCname(),"Seoul");
        assertEquals(city.getCd(),"Seoul");
        assertEquals(city.getCpop(),3998899);

//        cities.add(city);



    }
}