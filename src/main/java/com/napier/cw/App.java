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
        String region="Central Africa";
        String country="United States";
        String district="New York";

        //Get data populated cities in the world, continent, country, region and district
        ArrayList<City>  pcw    = a.getPCitiesWorld(num);
        ArrayList<CandC> pcc    = a.getPCitiesConti(continent,num);
 

        //Call all display functions
        a.displayPCitiesWorld(pcw);
        a.displayPCitiesConti(pcc,continent);


        // Disconnect from database
        a.disconnect();
    }

    /* =============================== =======================*/
    /* Functions of Extract Information from Database Section */

    //Extract populated cities of the world from the database
    public ArrayList<City> getPCitiesWorld(int num)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect = "SELECT Name, CountryCode, District, Population "
                                +"FROM city ORDER BY Population DESC LIMIT "+num;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //ArrayList Creation
            ArrayList<City> apcw = new ArrayList<City>();

            //Extract the info from the current record in the ResultSet
            while (rset.next())
            {
                City city = new City();

                //Using getInt for integer data, getString for string data
                city.setCname(rset.getString(1));
                city.setCccode(rset.getString(2));
                city.setCd(rset.getString(3));
                city.setCpop(rset.getInt(4 ));

                //Add the data to the apcw array
                apcw.add(city);
            }
            return apcw;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");

        }
        return null;
    }

    //Extract populated cities of a continent from the database
    public ArrayList<CandC> getPCitiesConti(String continent, int num)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect ="SELECT city.Name, country.Name, country.Continent, city.District, city.Population " +
                    "FROM city,country WHERE city.CountryCode = country.Code " +
                    "AND country.Continent='"+continent+"' ORDER BY city.Population DESC LIMIT "+num;

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            // Extract city information
            ArrayList<CandC> apcc = new ArrayList<CandC>();

            // Return city if valid.
            // Check one is returned

            //Extract the info from the current record in the ResultSet
            while (rset.next())
            {

                //Create an City and Country Object
                CandC cc = new CandC ();

                //Using getInt for integer data, getString for string data
                cc.setCityname(rset.getString(1));
                cc.setCountryname(rset.getString(2));
                cc.setDistrict(rset.getString(3));
                cc.setContinent(rset.getString(4));
                cc.setPopulation(rset.getInt(5));

                //Add the data to the apcw array
                apcc.add(cc);

            }
            return apcc;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");

        }
        return null;
    }



    /* =======================================  ===========  =========== */
    /* Functions of Generate Report Section */

    //Display the number of populated cities in the world by *N* given by user
    public void displayPCitiesWorld(ArrayList<City> pcw)
    {
        int count=0;
        System.out.println("Cities in the World:");
        // Print header
        System.out.println(String.format("%-10s %-30s %-20s %-20s %-20s", "Number","Cities", "Countries", "District", "Population"));
        // Loop over all city information in the list
        for (City PCW : pcw)
        {
            count++;
            String pcw_string =
                    String.format("%-10s %-20s %-20s %-20s %-20s",
                            count,PCW.getCname(), PCW.getCccode(), PCW.getCd(), PCW.getCpop());
            System.out.println(pcw_string);
        }
        System.out.println("\n");
    }

    //Display the populated Cities in a continent by *N* given by user
    public void displayPCitiesConti(ArrayList<CandC> pcc, String continent)
    {
        int count=0;
        // Print header
        System.out.println("Cities in "+continent+" Continent:");
        System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s %-20s", "Number","Cities","Countries", "Continent", "District","Population"));
        // Loop over all city information in the list
        for (CandC PCC : pcc)
        {
            count++;
            String pcc_string =
                    String.format("%-20s %-20s %-20s %-20s %-20s %-20s",
                            count,PCC.getCityname(),PCC.getCountryname(),PCC.getContinent(),
                            PCC.getDistrict(),PCC.getPopulation());
            System.out.println(pcc_string);
        }
        System.out.println("\n");
    }


    //Create object for MySQL database
    private Connection con = null;

    /* Database Connection and Disconnection Section */

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