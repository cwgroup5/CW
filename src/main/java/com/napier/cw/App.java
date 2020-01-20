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



       // Display Country Profile
        System.out.println("---------" +
                "-------");
        Country c=a.getCountry();
        System.out.println(c);
        // Disconnect from database
        a.disconnect();
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
                            + "ORDER BY Population DESC";


            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country country = new Country();
                country.setCode(rset.getString("Code"));
                country.setName(rset.getString("Name"));
                country.setContinent(rset.getString("Continent"));
                country.setRegion(rset.getString("Region"));
                country.setPopulation (rset.getInt("Population"));
                country.setCapital(rset.getString("Capital"));

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