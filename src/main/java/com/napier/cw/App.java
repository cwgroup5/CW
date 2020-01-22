package com.napier.cw;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

//import java.util.Scanner;

public class App
{
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
    public ArrayList<Country> getCountry()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "ORDER BY Population DESC";


            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (res.next())
            {
                Country country = new Country();
                country.setCode(res.getString("Code"));
                country.setName(res.getString("Name"));
                country.setContinent(res.getString("Continent"));
                country.setRegion(res.getString("Region"));
                country.setPopulation (res.getInt("Population"));
                country.setCapital(res.getString("Capital"));

                countries.add(country);

            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
    }
    public ArrayList<Country> getCountryInContinent(String cont)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE country.Continent='"+cont+"' ORDER BY Population DESC";


            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<Country>();
            // Return new employee if valid.
            // Check one is returned
            while (res.next())
            {
                Country country = new Country();
                country.setCode(res.getString("Code"));
                country.setName(res.getString("Name"));
                country.setContinent(res.getString("Continent"));
                country.setRegion(res.getString("Region"));
                country.setPopulation (res.getInt("Population"));
                country.setCapital(res.getString("Capital"));

                countries.add(country);

            }
            return  countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
    }
    public ArrayList<Country> getCountryInRegion()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE country.Region = 'Middle East' "
                            + "ORDER BY Population DESC";


            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            ArrayList<Country> countries = new ArrayList<Country>();
            // Return new employee if valid.
            // Check one is returned
            while (res.next())
            {
                Country country = new Country();
                country.setCode(res.getString("Code"));
                country.setName(res.getString("Name"));
                country.setContinent(res.getString("Continent"));
                country.setRegion(res.getString("Region"));
                country.setPopulation (res.getInt("Population"));
                country.setCapital(res.getString("Capital"));
                countries.add(country);

            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
    }
    public void displayCountries(ArrayList<Country> countries)
    {
        //display in table format

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        String format="%1$-10s %2$-60s %3$-20s %4$-40s  %5$-15s  %6$-10s \n";
        System.out.format(format, "Code", "Country", "Continent", "Region", "Population", "Capitals");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Country country : countries)
        {
            System.out.format(format,country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    //
//    /**
//     * Connection to MySQL database.
//     */
    private Connection con = null;
    //
//    /**
//     * Connect to the MySQL database.
//     */


    public ArrayList<City> getCity()
    {
        try {
//            Create an SQL statement
            Statement stmt = con.createStatement();
//            Create string for SQL statement
            String strSelect=
                    "SELECT `city`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` ORDER BY Population DESC ";
//      Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (res.next())
            {
                City city = new City();
                city.setCname(res.getString("Name"));
                city.setCd(res.getString("District"));
                city.setCpop (res.getInt("Population"));


                cities.add(city);


            }
            return cities;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City profile");
            return null;

        }
    }
    public ArrayList<City> getCityInContinent(String cont)
    {
        try {
//            Create an SQL statement
            Statement stmt = con.createStatement();
//            Create string for SQL statement
            String strSelect=
                    "SELECT `city`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Continent`='"+cont+"' ORDER BY `Population` DESC ";

//      Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (res.next())
            {
                City city = new City();
                city.setCname(res.getString("Name"));
                city.setCd(res.getString("District"));
                city.setCpop (res.getInt("Population"));


                cities.add(city);


            }
            return cities;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City profile");
            return null;

        }
    }
    public ArrayList<City> getCityInRegion(String reg)
    {
        try {
//            Create an SQL statement
            Statement stmt = con.createStatement();
//            Create string for SQL statement
            String strSelect=
                    "SELECT `city`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Region`='"+reg+"' ORDER BY `Population` DESC ";
//      Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (res.next())
            {
                City city = new City();
                city.setCname(res.getString("Name"));
                city.setCd(res.getString("District"));
                city.setCpop (res.getInt("Population"));


                cities.add(city);


            }
            return cities;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City profile");
            return null;

        }
    }
    public ArrayList<City> getCityInCountry()
    {
        try {
//            Create an SQL statement
            Statement stmt = con.createStatement();
//            Create string for SQL statement
            String strSelect=
                    "SELECT `city`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.Name ='Malaysia' ORDER BY Population DESC ";
//      Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (res.next())
            {
                City city = new City();
                city.setCname(res.getString("Name"));
                city.setCd(res.getString("District"));
                city.setCpop (res.getInt("Population"));


                cities.add(city);


            }
            return cities;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City profile");
            return null;

        }
    }
    public ArrayList<City> getCityInDistrict()
    {
        try {
//            Create an SQL statement
            Statement stmt = con.createStatement();
//            Create string for SQL statement
            String strSelect=
                    "SELECT `city`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE district ='Mandalay' ORDER BY Population DESC ";
//      Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (res.next())
            {
                City city = new City();
                city.setCname(res.getString("Name"));
                city.setCd(res.getString("District"));
                city.setCpop (res.getInt("Population"));


                cities.add(city);


            }
            return cities;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City profile");
            return null;

        }
    }
    public void displayCities(ArrayList<City> cities)
    {
        //display in table format

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        String format="%1$-10s %2$-25s %3$-20s  \n";
        System.out.format(format, "City-Name", "District", "Population");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        for (City city : cities)
        {
            System.out.format(format,city.getCname(), city.getCd(), city.getCpop());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }
    public static void main(String[] args) throws IOException
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
// Declare the object and initialize with
        // predefined standard input object

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter Name of Continent:");
//        String cont = br.readLine();
        System.out.println("Enter Name of Region:");
        String reg = br.readLine();
        // Display Country Profile

        // Extract country population information
//        ArrayList<Country> countries = a.getCountry();
//        ArrayList<Country> countries = a.getCountryInContinent();
//        ArrayList<Country> countries = a.getCountryInRegion();
//        a.displayCountries(countries);
        /** Produce City Report **/
//        ArrayList<City> cities = a.getCityInContinent(cont);
        //ArrayList<City> cities = a.getCity();
        ArrayList<City> cities = a.getCityInRegion(reg);
//        ArrayList<City> cities = a.getCityInCountry();
//        ArrayList<City> cities = a.getCityInDistrict();
        a.displayCities(cities);

        // Test the size of the returned data - should be
//        System.out.println("Number of Countries :"+ countries.size());
        // Disconnect from database
        a.disconnect();
    }
}