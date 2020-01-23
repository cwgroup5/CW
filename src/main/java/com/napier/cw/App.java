package com.napier.cw;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class App
{
    int num=0;
    String area=null;

    /* =============================== =======================*/
    /* Functions of Extract Information from Database Section */

    //Extract populated cities of a continent from the database
    public ArrayList<City> getPCitiesConti(int selection)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter Number: ");
            num = Integer.parseInt(br.readLine());
            System.out.print("\n");

            //ArrayList Creation
            ArrayList<City> apcu = new ArrayList<City>();

            String strSelect=null;
            // Create an SQL statement
            Statement stmt = con.createStatement();
            if(selection==1)
            {
                System.out.println("Populated Cities in the world...");
                // Create string for SQL statement
                strSelect = "SELECT Name, CountryCode, District, Population "
                        +"FROM city ORDER BY Population DESC LIMIT "+num;
            }
            else if(selection==2)
            {
                System.out.print("Enter a continent: ");
                area =br.readLine();
                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT city.Name, city.CountryCode, "
                        +"city.District, city.Population FROM city,country "
                        +"WHERE city.CountryCode = country.Code "
                        +"AND country.Continent='"+area+"' ORDER BY city.Population DESC LIMIT "+num;
            }
            else if(selection==3)
            {
                System.out.print("Enter a region: ");
                area =br.readLine();
                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT city.Name, city.CountryCode, "
                        +"city.District, city.Population FROM city,country "
                        +"WHERE city.CountryCode = country.Code "
                        +"AND country.Region='"+area+"' ORDER BY city.Population DESC LIMIT "+num;
            }
            else if(selection==4)
            {
                System.out.print("Enter a country: ");
                area =br.readLine();
                // Create string for SQL statement for *N* populated cities in a country
                strSelect = "SELECT city.Name, city.CountryCode, "
                        +"city.District,city.Population FROM city,country "
                        +"WHERE city.CountryCode = country.Code "
                        +"AND country.Name='"+area+"' ORDER BY city.Population DESC LIMIT "+num;
            }
            else if(selection==5)
            {
                System.out.print("Enter a district: ");
                area =br.readLine();
                // Create string for SQL statement for *N* populated cities in a district
                strSelect ="SELECT Name, CountryCode, District, Population "
                        +"FROM city WHERE District='"+area+"'";
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
                apcu.add(city);
            }
            return apcu;
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

    //Display the populated Cities in a continent by *N* given by user
    public void displayPCitiesbyUser(ArrayList<City> pcu)
    {
        int count=0;
        // Print header
        System.out.println("Cities in "+area+" Continent:");
        System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", "Number","Cities","Countries", "District","Population"));
        // Loop over all city information in the list
        for (City PCU : pcu)
        {
            count++;
            String pcu_string =
                    String.format("%-20s %-20s %-20s %-20s %-20s",
                            count,PCU.getCname(),PCU.getCccode(),PCU.getCd(),PCU.getCpop());
            System.out.println(pcu_string);
        }
        System.out.println("Number of Populated Cities in "+area+" :"+" "+num+"\n");
    }

    /* =======================================  ===== */
    /* Database Connection and Disconnection Section */

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

    public static void main(String[] args) throws IOException {

        String yn;
        int selection;
        yn ="y";

        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();

        while (yn.equals("y"))
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Search Populated Cities by ....");
            System.out.println("1. The World \t\t 2. A Continent \t\t 3. A Region \t\t 4. A Country\n 5. A District");
            System.out.print("Choose a number:");
            selection = Integer.parseInt(br.readLine());

            ArrayList<City> pcu    = a.getPCitiesConti(selection);
            //Call display report functions
            a.displayPCitiesbyUser(pcu);

            System.out.print("Do you want to continue (y/n): ");
            yn =br.readLine();
            if (yn.equals("n")) {
                System.exit(0);
            }
        }
        // Disconnect from database
        a.disconnect();
    }
}