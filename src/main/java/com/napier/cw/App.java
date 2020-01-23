package com.napier.cw;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class App
{
    int num;
    String area;

    public ArrayList<City> getCCWbyUser(int selection)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Number: ");
            num = Integer.parseInt(br.readLine());
            System.out.print("\n");

            //ArrayList Creation
            ArrayList<City> apcc = new ArrayList<City>();

            String strSelect=null;
            // Create an SQL statement
            Statement stmt = con.createStatement();
            if(selection==1)
            {
                System.out.println("All Capital Cities in the World");
                // Create string for SQL statement
                strSelect = " SELECT city.Name, country.Name, country.Population FROM city "
                        + "LEFT JOIN country ON city.ID = country.Capital "
                        + "ORDER BY country.Population DESC";

            }
            else if(selection==2)
            {
                System.out.print("Enter a Continent: ");
                area =br.readLine();
                System.out.print("\n");

                // Create string for SQL statement
                strSelect =" SELECT city.Name, country.Name, country.Population FROM city "
                        + "LEFT JOIN country ON city.ID = country.Capital "
                        + "WHERE country.Continent='"+area+"' ORDER BY country.Population DESC ";
            }
            else if(selection==3)
            {
                System.out.print("Enter a Region: ");
                area =br.readLine();
                System.out.print("\n");

                // Create string for SQL statement
                strSelect =" SELECT city.Name, country.Name, country.Population FROM city "
                        + "LEFT JOIN country ON city.ID = country.Capital "
                        + "WHERE country.Region='"+area+"' ORDER BY country.Population DESC ";
            }
            else if(selection==4)
            {
                // Create string for SQL statement
                strSelect = " SELECT city.Name, country.Name, country.Population FROM city "
                        + "LEFT JOIN country ON city.ID = country.Capital "
                        + "ORDER BY country.Population DESC";
            }
            else if(selection==5)
            {
                System.out.print("Enter a Number: ");
                num =Integer.parseInt(br.readLine());
                System.out.print("Enter a continent: ");
                area =br.readLine();

                // Create string for SQL statement for populated capital cities in a region
                strSelect = " SELECT city.Name, country.Name, country.Population FROM city "
                        + "LEFT JOIN country ON city.ID = country.Capital "
                        +"WHERE country.Continent='"+area+"' ORDER BY country.Population DESC LIMIT"+num;
            }
            else if(selection==6)
            {
                System.out.print("Enter Number: ");
                num = Integer.parseInt(br.readLine());
                System.out.print("Enter a region: ");
                area =br.readLine();
                // Create string for SQL statement for populated capital cities in a region
                strSelect =  " SELECT city.Name, country.Name, country.Population FROM city "
                        + "LEFT JOIN country ON city.ID = country.Capital "
                        + "WHERE country.Region='"+area+"' ORDER BY country.Population DESC LIMIT "+num;

            }
            else
            {
                System.out.println("Out of Index!");
            }

            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract the info from the current record in the ResultSet
            while (rset.next())
            {
                City city = new City();
                //Using getInt for integer data, getString for string data
                city.setCname(rset.getString(1));
                city.setCccode(rset.getString(2));
                city.setCd(rset.getString(3));
                city.setCpop(rset.getInt(4 ));
                //Add the data to the apcu array
                apcc.add(city);
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
    public void displayPCCbyUser(ArrayList<City> cty)
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

        ArrayList<City> pcc    = a.getCCWbyUser(selection);
        //Call display report functions
        a.displayPCCbyUser(pcc);

        // Disconnect from database
        a.disconnect();
    }

}
