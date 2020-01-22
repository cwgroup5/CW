package com.napier.cw;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

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
            // Message if SQL driver cannot be loaded
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

    public ArrayList<City> getCCWbyUser(String cont)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT `city`.`Name`, `country`.`Name`, `country`.`Population` FROM `city` "
                            + "LEFT JOIN `country` ON `city`.`ID` = `country`.`Capital` "
                            + "ORDER BY `country`.`Population` DESC";

            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            ArrayList<City> accw = new ArrayList<City>();
            while (res.next())
            {
                City cty =new City();
                cty.setCname(res.getString(1));
                cty.setCccode(res.getString(2));
                cty.setCpop(res.getInt(3));
                accw.add(cty);


            }
            return accw;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
    }

    public ArrayList<City> getCitiesInContinent(String cont)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT `city`.`Name`, `country`.`Name`, `country`.`Population` FROM `city` "
                            + "LEFT JOIN `country` ON `city`.`ID` = `country`.`Capital` WHERE country.Continent='"+cont+"' ORDER BY `country`.Population DESC ";

            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            ArrayList<City> accw = new ArrayList<City>();
            while (res.next())
            {
                City cty =new City();
                cty.setCname(res.getString(1));
                cty.setCccode(res.getString(2));
                cty.setCpop(res.getInt(3));
                accw.add(cty);
            }
            return accw;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
    }

    public ArrayList<City> getCitiesInRegionByUser(String re)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT `city`.`Name`, `country`.`Name`, `country`.`Population` FROM `city` "
                            + "LEFT JOIN `country` ON `city`.`ID` = `country`.`Capital` WHERE country.Region='"+re+"' ORDER BY `country`.Population DESC ";

            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            ArrayList<City> accw = new ArrayList<City>();
            while (res.next())
            {
                City cty =new City();
                cty.setCname(res.getString(1));
                cty.setCccode(res.getString(2));
                cty.setCpop(res.getInt(3));
                accw.add(cty);
            }
            return accw;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
    }

    public ArrayList<City> getCityByUser(int no)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT `city`.`Name`, `country`.`Name`, `country`.`Population` FROM `city` "
                            + "LEFT JOIN `country` ON `city`.`ID` = `country`.`Capital` "
                            + "ORDER BY `country`.`Population` DESC LIMIT " +no;


            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            ArrayList<City> accw = new ArrayList<City>();
            while (res.next())
            {
                City cty =new City();
                cty.setCname(res.getString(1));
                cty.setCccode(res.getString(2));
                cty.setCpop(res.getInt(3));
                accw.add(cty);
            }
            return accw;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
    }

    public ArrayList<City> getCitiesInContinentByUser(String cont, int no)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT `city`.`Name`, `country`.`Name`, `country`.`Population` FROM `city` "
                            + "LEFT JOIN `country` ON `city`.`ID` = `country`.`Capital` "
                            + "WHERE country.Continent='"+cont+"' ORDER BY `country`.Population DESC LIMIT " +no;


            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            ArrayList<City> accw = new ArrayList<City>();
            while (res.next())
            {
                City cty =new City();
                cty.setCname(res.getString(1));
                cty.setCccode(res.getString(2));
                cty.setCpop(res.getInt(3));
                accw.add(cty);
            }
            return accw;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
    }

    public ArrayList<City> getCitiesInRegionByUser(String re, int no)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT `city`.`Name`, `country`.`Name`, `country`.`Population` FROM `city` "
                            + "LEFT JOIN `country` ON `city`.`ID` = `country`.`Capital` "
                            + "WHERE country.Region='"+re+"' ORDER BY `country`.Population DESC LIMIT " +no;


            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            ArrayList<City> accw = new ArrayList<City>();
            while (res.next())
            {
                City cty =new City();
                cty.setCname(res.getString(1));
                cty.setCccode(res.getString(2));
                cty.setCpop(res.getInt(3));
                accw.add(cty);
            }
            return accw;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");
            return null;
        }
    }

    public void displayCCbyUser(ArrayList<City> cty)
    {
        //display in table format

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        String format="%1$-25s %2$-60s %3$-20s \n ";
        System.out.format(format, "Name", "Country", "Population");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (City CTY : cty)
        {
            System.out.format(format,CTY.getCname(),CTY.getCccode(),CTY.getCpop());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * Connection to MySQL database.
  */
    private Connection con = null;
    /**
     * Connect to the MySQL database.
     */

    public static void main(String[] args) throws IOException
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        // Declare the object and initialize with
        // predefined standard input object

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Enter No of Capital City:");
//        int no=Integer.parseInt(br.readLine());
        System.out.println("Enter Name of Continent:");
        String cont = br.readLine();

//        System.out.println("Enter Name of Region:");
//        String re = br.readLine();

//        ArrayList<City> capcty = a.getCCWbyUser(cont);
        ArrayList<City> capcty = a.getCitiesInContinent(cont);
//        ArrayList<City> capcty = a.getCitiesInRegionByUser(cont);
//        ArrayList<City> capcty = a.getCityByUser(no);
//        ArrayList<City> capcty = a.getCitiesInContinentByUser(cont, no);
//        ArrayList<City> capcty = a.getCitiesInRegionByUser(re, no);
        a.displayCCbyUser(capcty);

        // display number of  countries
        System.out.println("Number of Capital Cities :"+ capcty.size());
        // Disconnect from database
        a.disconnect();
    }

}