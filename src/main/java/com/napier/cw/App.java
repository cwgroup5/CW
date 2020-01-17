package com.napier.cw;

import java.sql.*;

public class App
{

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get Employee
//        City cy= a.getCity(69);
        City ct=a.getCity();
        System.out.println(ct);
//        // Display
        System.out.println("----------------");
        Country c=a.getCountry();
        System.out.println(c);
        // Disconnect from database
        a.disconnect();
    }
    public City getCity()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city "
                            + "Order By Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City city = new City();
                city.cityid = rset.getInt("ID");
                city.cname = rset.getString("Name");
                city.cccode = rset.getString("District");
                city.cpop = rset.getInt("Population");
                System.out.println(city.cname);
                while(rset.next()) {
                    System.out.println(
                            "Name -" + rset.getString("Name")
                                    + ", District -" + rset.getString("District")
                                    + ", Population -" + rset.getInt("Population")
                    );

                }
                System.out.println("Finished");
                return city;

            }
            else return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public Country getCountry()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "Order By Population DESC";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country country = new Country();
                country.Code = rset.getString("Code");
                country.Name = rset.getString("Name");
                country.Continent = rset.getString("Continent");
                country.Region = rset.getString("Region");
                country.Population = rset.getInt("Population");
                country.Captial = rset.getString("Capital");

                while (rset.next()) {
                    System.out.println(
                            "Code -" + rset.getString("Code")
                                    + ", Name -" + rset.getString("Name")
                                    + ", Continent -" + rset.getString("Continent")
                                    + ", Region -" + rset.getString("Region")
                                    + ", Population -" + rset.getInt("Population")
                                    + ", Capital -" + rset.getString("Capital")
                    );

                }
                System.out.println("Finished");
                return country;

            }
            else return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
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

}