package com.napier.cw;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

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
//    public void input()
//    {
//        Scanner input = new Scanner(System.in);
//
//        System.out.println("Enter Number of Countries:");
//        int noOfCountries = input.nextInt();
//        System.out.println("NoOfCountries =" +noOfCountries);
//    }


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

    public ArrayList<Country> getCountryByUser(int no)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "ORDER BY Population DESC LIMIT " +no;


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
//    public void sampleMethod(Scanner sc){
//        StringBuffer sb = new StringBuffer();
//        System.out.println("Enter your name: ");
//        String name = sc.next();
//        System.out.println("Hello "+name);
//    }

//
    public ArrayList<Country> getCountryInContinent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE country.Continent = 'Asia' "
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

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        int no = 10;


        // Display Country Profile

        // Extract country population information
//        ArrayList<Country> countries = a.getCountry();
//        ArrayList<Country> countries = a.getCountryInContinent();
//        ArrayList<Country> countries = a.getCountryInRegion();

        ArrayList<Country> countries = a.getCountryByUser(no);

        a.displayCountries(countries);

        // display number of  countries
        System.out.println("Number of Countries :"+ countries.size());
        // Disconnect from database
        a.disconnect();
    }

}