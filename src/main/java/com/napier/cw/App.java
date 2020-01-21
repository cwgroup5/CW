package com.napier.cw;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class App
{

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();
        int num = 10;
        String continent ="Asia";
        String region ="Central Africa";
        String country="United States";

        // Disconnect from database
        a.disconnect();
    }

    public ArrayList<City> getCity(int num)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Name, CountryCode, District, Population FROM city ORDER BY Population DESC LIMIT "+num;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cty = new ArrayList<City>();

            // Return city if valid.
            // Check one is returned
            while (rset.next())
            {
                City city = new City();
                city.setCname(rset.getString(1));
                city.setCccode(rset.getString(2));
                city.setCd(rset.getString(3));
                city.setCpop(rset.getInt(4 ));
                cty.add(city);


            }
            return cty;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");

        }
        return null;
    }

    public void printCity(ArrayList<City> cty)
    {
        int count=0;
        // Print header
        System.out.println(String.format("%-10s %-30s %-20s %-20s %-20s", "Number","Cities", "Countries", "District", "Population"));
        // Loop over all employees in the list
        for (City city : cty)
        {
            count++;
            String emp_string =
                    String.format("%-10s %-20s %-20s %-20s %-20s",
                            count,city.getCname(), city.getCccode(), city.getCd(), city.getCpop());
            System.out.println(emp_string);
        }
    }

    //Create object for MySQL database
    private Connection con = null;

    //Connect to MySQL database
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

    //Disconnect from MySQL database
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