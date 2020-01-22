package com.napier.cw;

import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class App
{
    int no=0;
    String area=null;

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



    public ArrayList<Country> getCountry(int selection)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String strSelect=null;
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            if(selection==1)
            {
                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name`" +
                        " FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` ORDER BY population DESC";
            }

            else if(selection==2)
            {

                System.out.print("Enter a Continent: ");
                area =br.readLine();
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name` " +
                        "FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` " +
                        "WHERE country.Continent='"+area+"' ORDER BY  `country`.`Population`  DESC";
            }
            else if(selection==3)
            {

                System.out.print("Enter a Region: ");
                area =br.readLine();
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect =  "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name`" +
                        " FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital`" +
                        " WHERE country.Region='"+area+"' ORDER BY population DESC";
            }

            else if(selection==4)
            {
                System.out.print("Enter Number of Countries: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name` " +
                        "FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` ORDER BY population DESC LIMIT " +no;
            }
            else if(selection==5)
            {
                System.out.print("Enter Number of Countries: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("Enter a Continent: ");
                area =br.readLine();
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name` " +
                        "FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` " +
                        "WHERE country.Continent='"+area+"' ORDER BY  `country`.`Population`  DESC LIMIT "+no;
            }
            else if(selection==6)
            {
                System.out.print("Enter Number of Countries: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("Enter a Region: ");
                area =br.readLine();
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name`" +
                        " FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` " +
                        "WHERE country.Region='"+area+"' ORDER BY  `country`.`Population`  DESC LIMIT "+no;
            }
            else if(selection==7)
            {
               System.exit(0);
            }

            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (res.next())
            {
                Country country = new Country();
                country.setCode(res.getString(1));
                country.setName(res.getString(2));
                country.setContinent(res.getString(3));
                country.setRegion(res.getString(4));
                country.setPopulation (res.getInt(5));
                country.setCapital(res.getString(6));

                countries.add(country);

            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");

        }
        return null;
    }
    public ArrayList<City> getCity(int selection2)
    {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String strSelect=null;
//            Create an SQL statement
            Statement stmt = con.createStatement();
//            Create string for SQL statement
           if(selection2==1)
            {
                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT `city`.`Name`, `country`.`Name`,`city`.`District`, `city`.`Population` " +
                        "FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` ORDER BY Population DESC ";
            }
           else if (selection2==2)
           {
               System.out.print("Enter a Continent: ");
               area =br.readLine();
               System.out.print("\n");

                strSelect=
                       "SELECT `city`.`Name`,`country`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Continent`='"+area+"' ORDER BY `Population` DESC ";

           }
           else if (selection2==3)
           {
               System.out.print("Enter a Region: ");
               area =br.readLine();
               System.out.print("\n");
               strSelect=
                       "SELECT `city`.`Name`,`country`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Region`='"+area+"' ORDER BY `Population` DESC ";
           }
           else if (selection2==4)
           {
               System.out.print("Enter a Country: ");
               area =br.readLine();
               System.out.print("\n");

               strSelect="SELECT `city`.`Name`,`country`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Name`='"+area+"' ORDER BY `Population` DESC ";

           }
           else if (selection2==5)
           {
               System.out.print("Enter a District: ");
               area =br.readLine();
               System.out.print("\n");

               strSelect="SELECT `city`.`Name`, `country`.`Name`,`city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `city`.`District`='"+area+"' ORDER BY `Population` DESC ";

           }

//      Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (res.next())
            {
                City city = new City();
                city.setCname(res.getString(1));
                city.setCccode(res.getString(2));
                city.setCd(res.getString(3));
                city.setCpop (res.getInt(4));
                cities.add(city);
            }
            return cities;

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City profile");

        }
        return null;
    }

    public void displayCountries(ArrayList<Country> countries)
    {

        //display in table format

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        String format="%1$-10s %2$-60s %3$-20s %4$-40s  %5$-15s  %6$-10s \n";
        System.out.format(format, "Code", "Country", "Continent", "Region", "Population", "Capital");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Country country : countries)
        {
            System.out.format(format,country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital());


        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
    public void displayCities(ArrayList<City> cities)
    {
        //display in table format

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        String format="%1$-10s %2$-25s %3$-25s %3$-20s  \n";
        System.out.format(format, "City","Country", "District", "Population");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        for (City city : cities)
        {
            System.out.format(format,city.getCname(),city.getCccode(), city.getCd(), city.getCpop());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
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

        String yn;
        int selection,selection2,mm;
        yn ="y";

        // Create new Application

        App a = new App();

        // Connect to database
        a.connect();

        BufferedReader m=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter 1 for Country Report: \n Enter 2 For City Report \n Enter 3 For Capital City Report \n");
        mm = Integer.parseInt(m.readLine());
        System.out.print("\n");
        if (mm==1){
            while (yn.equals("y")) {
                // Declare the object and initialize with
                // predefined standard input object


                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter 1 to organise all countries in the Word \n "
                        + "Enter 2 to organise all countries in a Continent \n "
                        + "Enter 3 to organise all countries in a Region \n "
                        + "Enter 4 to organise top populated Countries in the World \n "
                        + "Enter 5 to organise top populated Countries in a Continent  \n"
                        + "Enter 6 to organise top populated Countries in a Region \n"
                        + "Enter 7 to Exit \n  ");

                System.out.print("Choose a number:");
                selection = Integer.parseInt(br.readLine());

                ArrayList<Country> countries = a.getCountry(selection);
                a.displayCountries(countries);
                System.out.println("Number of Countries :"+ countries.size());

                System.out.print("Do you want to continue (y/n): ");
                yn =br.readLine();
                if (yn.equals("n")) {
                    System.exit(0);
                }
            }

        }
        else  if (mm==2){
            while (yn.equals("y")) {
                // Declare the object and initialize with
                // predefined standard input object


                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter 1 to organise all cities in the Word \n "
                        + "Enter 2 to organise all cities in a Continent \n "
                        + "Enter 3 to organise all cities in a Region \n "
                        + "Enter 4 to organise all cities in a Country \n "
                        + "Enter 5 to organise all cities in a District \n"
                        + "Enter 7 to Exit \n  ");

                System.out.print("Choose a number:");
                selection2 = Integer.parseInt(br.readLine());

                ArrayList<City> cities  = a.getCity(selection2);
                a.displayCities(cities);
                System.out.println("Number of Countries :"+ cities.size());

                System.out.print("Do you want to continue (y/n): ");
                yn =br.readLine();
                if (yn.equals("n")) {
                    System.exit(0);
                }
            }
        }


        // Disconnect from database
        a.disconnect();
    }

}