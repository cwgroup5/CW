package com.napier.cw;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class App {
    int no = 0;
    String area = null;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String strSelect = null;

    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // Message if SQL driver cannot be loaded
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }


    public ArrayList<Country> getCountry(int selection) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            if (selection == 1) {
                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, country.`Name`, Continent, Region, `country`.`Population`, `city`.`Name`" +
                        " FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` ORDER BY population DESC";
                



            } else if (selection == 2) {

                System.out.print("Enter a Continent: ");
                area = br.readLine();
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name` " +
                        "FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` " +
                        "WHERE country.Continent='" + area + "' ORDER BY  `country`.`Population`  DESC";
            } else if (selection == 3) {

                System.out.print("Enter a Region: ");
                area = br.readLine();
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name`" +
                        " FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital`" +
                        " WHERE country.Region='" + area + "' ORDER BY population DESC";
            } else if (selection == 4) {
                System.out.print("Enter Number of Countries: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name` " +
                        "FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` ORDER BY population DESC LIMIT " + no;
            } else if (selection == 5) {
                System.out.print("Enter Number of Countries: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("Enter a Continent: ");
                area = br.readLine();
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name` " +
                        "FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` " +
                        "WHERE country.Continent='" + area + "' ORDER BY  `country`.`Population`  DESC LIMIT " + no;
            } else if (selection == 6) {
                System.out.print("Enter Number of Countries: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("Enter a Region: ");
                area = br.readLine();
                System.out.print("\n");

                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT Code, `country`.`Name`, Continent, Region, `country`.`Population`, `city`.`Name`" +
                        " FROM `country` LEFT JOIN `city` ON `city`.`ID` = `country`.`Capital` " +
                        "WHERE country.Region='" + area + "' ORDER BY  `country`.`Population`  DESC LIMIT " + no;
            } else if (selection == 7) {
                System.exit(0);
            }
            else
            {
                System.out.println("This is not a valid Option!");
                System.exit(0);
            }

            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new country if valid.
            // Check one is returned
            ArrayList<Country> countries = new ArrayList<Country>();
            while (res.next()) {
                Country country = new Country();
                country.setCode(res.getString(1));
                country.setName(res.getString(2));
                country.setContinent(res.getString(3));
                country.setRegion(res.getString(4));
                country.setPopulation(res.getInt(5));
                country.setCapital(res.getString(6));
                country.setTotalPopulation(res.getInt(7));
                countries.add(country);


            }
            return countries;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country profile");

        }
        return null;
    }

    public ArrayList<City> getCity(int selection) {
        try {

//            Create an SQL statement
            Statement stmt = con.createStatement();
//            Create string for SQL statement
            if (selection == 1) {
                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT `city`.`Name`, `country`.`Name`,`city`.`District`, `city`.`Population` " +
                        "FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` ORDER BY Population DESC ";
            } else if (selection == 2) {
                System.out.print("Enter a Continent: ");
                area = br.readLine();
                System.out.print("\n");

                strSelect =
                        "SELECT `city`.`Name`,`country`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Continent`='" + area + "' ORDER BY `Population` DESC ";

            } else if (selection == 3) {
                System.out.print("Enter a Region: ");
                area = br.readLine();


                System.out.print("\n");
                strSelect =
                        "SELECT `city`.`Name`,`country`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Region`='" + area + "' ORDER BY `Population` DESC ";
            } else if (selection == 4) {
                System.out.print("Enter a Country: ");
                area = br.readLine();
                System.out.print("\n");

                strSelect = "SELECT `city`.`Name`,`country`.`Name`, `city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `country`.`Name`='" + area + "' ORDER BY `Population` DESC ";

            } else if (selection == 5) {
                System.out.print("Enter a District: ");
                area = br.readLine();
                System.out.print("\n");

                strSelect = "SELECT `city`.`Name`, `country`.`Name`,`city`.`District`, `city`.`Population` FROM city LEFT JOIN country ON `city`.`CountryCode` = `country`.`Code` WHERE `city`.`District`='" + area + "' ORDER BY `Population` DESC ";

            }
            else if(selection==6)
            {
                System.out.print("Enter Number of Cities: ");
                no = Integer.parseInt(br.readLine());
                // Create string for SQL statement
                strSelect = "SELECT city.Name, country.Name, city.District, city.Population FROM city, country WHERE city.CountryCode = country.Code ORDER BY Population DESC LIMIT "+no;
            }
            else if(selection==7)
            {
                System.out.print("Enter Number of Cities: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("Enter a continent: ");
                area =br.readLine();
                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT city.Name, country.Name, "
                        +"city.District, city.Population FROM city,country "
                        +"WHERE city.CountryCode = country.Code "
                        +"AND country.Continent='"+area+"' ORDER BY city.Population DESC LIMIT "+no;
            }
            else if(selection==8)
            {
                System.out.print("Enter Number of Cities: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("Enter a region: ");
                area =br.readLine();
                // Create string for SQL statement for populated cities in a region
                strSelect = "SELECT city.Name, country.Name, "
                        +"city.District, city.Population FROM city,country "
                        +"WHERE city.CountryCode = country.Code "
                        +"AND country.Region='"+area+"' ORDER BY city.Population DESC LIMIT "+no;
            }
            else if(selection==9)
            {
                System.out.print("Enter Number of Cities: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("Enter a country: ");
                area =br.readLine();
                // Create string for SQL statement for *N* populated cities in a country
                strSelect = "SELECT city.Name, country.Name, "
                        +"city.District,city.Population FROM city,country "
                        +"WHERE city.CountryCode = country.Code "
                        +"AND country.Name='"+area+"' ORDER BY city.Population DESC LIMIT "+no;
            }
            else if(selection==10)
            {
                System.out.print("Enter Number of Cities: ");
                no = Integer.parseInt(br.readLine());
                System.out.print("Enter a district: ");
                area =br.readLine();
                // Create string for SQL statement for *N* populated cities in a district
                strSelect ="SELECT city.Name, country.Name, city.District, city.Population "
                        +"FROM city,country WHERE city.CountryCode = country.Code AND District='"+area+"' ORDER BY city.Population DESC LIMIT "+no;
            }

            else if (selection == 11) {
                System.exit(0);
            }
            else
            {
                System.out.println("This is not a valid Option!");
                System.exit(0);
            }

//      Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new city if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (res.next()) {
                City city = new City();
                city.setCname(res.getString(1));
                city.setCccode(res.getString(2));
                city.setCd(res.getString(3));
                city.setCpop(res.getInt(4));
                cities.add(city);
            }
            return cities;

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City profile");

        }
        return null;
    }
    public ArrayList<City> getCapitalCity(int selection)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            if(selection == 1)
            {
                System.out.println("All Capital Cities in the World");
                // Create string for SQL statement
                strSelect = " SELECT city.Name, country.Name, country.Population \n" +
                        "FROM `city` \n" +
                        "LEFT JOIN `country` ON `city`.`ID` = `country`.`Capital` ORDER BY Population DESC";

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
                System.out.print("Enter a Number of Capital Cities: ");
                no =Integer.parseInt(br.readLine());
                // Create string for SQL statement
                strSelect = " SELECT city.Name, country.Name, country.Population FROM city "
                        + "LEFT JOIN country ON city.ID = country.Capital "
                        + "ORDER BY country.Population DESC LIMIT "+no;
            }
            else if(selection==5)
            {
                System.out.print("Enter a Number of Capital Cities: ");
                no =Integer.parseInt(br.readLine());
                System.out.print("Enter a continent: ");
                area =br.readLine();

                // Create string for SQL statement for populated capital cities in a region
                strSelect = " SELECT city.Name, country.Name, country.Population FROM city "
                        + "LEFT JOIN country ON city.ID = country.Capital "
                        +"WHERE country.Continent='"+area+"' ORDER BY country.Population DESC LIMIT "+no;
            }
            else if(selection==6)
            {
                System.out.print("Enter Number of Capital Cities:");
                no = Integer.parseInt(br.readLine());
                System.out.print("Enter a region: ");
                area =br.readLine();
                // Create string for SQL statement for populated capital cities in a region
                strSelect =  " SELECT city.Name, country.Name, country.Population FROM city "
                        + "LEFT JOIN country ON city.ID = country.Capital "
                        + "WHERE country.Region='"+area+"' ORDER BY country.Population DESC LIMIT "+no;

            }
            else if (selection == 7)
            {
                System.exit(0);
            }
            else
            {
                System.out.println("This is not a valid Option!");
                System.exit(0);
            }

            // Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);

            //Extract the info from the current record in the ResultSet
            ArrayList<City> capitals = new ArrayList<City>();
            while (res.next())
            {
                City capital = new City();
                //Using getInt for integer data, getString for string data
                capital.setCname(res.getString(1));
                capital.setCccode(res.getString(2));
                capital.setCpop(res.getInt(3 ));
                //Add the data to the capital array
                capitals.add(capital);
            }

            return capitals;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities details");

        }
        return null;
    }
    public  void getPopulationofthePeople(String name)
    {
        try
        {
            ArrayList<String> conrecoun = new ArrayList<String>();

            String strSelect=null;
            String strSelect2=null;
            String area="";
            // Create an SQL statement
            Statement stmt = con.createStatement();
            if(name.equals("Country"))
            {
                name= "Name";
            }
            System.out.println(name);
            strSelect="SELECT DISTINCT "+name+" FROM `country` ORDER BY '"+name+"' ASC";
            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println(String.format("%-30s %-40s %-40s %-40s",
                    "Area","Total Population of People ","Total Population Living in Cities","Not living"));
            while(rset.next())
            {
                String  a =rset.getString(1);
                conrecoun.add(a);
            }
            for(String sss : conrecoun)
            {
                Statement stmt2 = con.createStatement();
                strSelect2 = "SELECT sum(country.Population),sum(city.Population) " +
                        "FROM city LEFT JOIN country ON city.CountryCode = country.Code WHERE country."+name+"='"+sss+"'";
                ResultSet rset2 = stmt2.executeQuery(strSelect2);

                //Extract the info from the current record in the ResultSet
                while (rset2.next())
                {
                    long p1=rset2.getLong(1);
                    long p2=rset2.getLong(2);
                    long p3=p1-p2;
                    String pcu_string =
                            String.format("%-30s %-40s %-40s %-40s", sss,p1,p2,p3);
                    System.out.println(pcu_string);
                }
            }

        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            System.out.println("Failed to get population of the people");

        }
    }
//    public long[] getPopulation(int selection)
//    {
//        try
//        {
//            // Create an SQL statement
//            Statement stmt = con.createStatement();
//            if(selection == 1)
//            {
//                // Create string for SQL statement
//                strSelect = " SELECT SUM(Population) FROM country";
//
//            }
//            else if(selection==2)
//            {
//                System.out.print("Enter a Continent: ");
//                area =br.readLine();
//                System.out.print("\n");
//
//                // Create string for SQL statement
//                strSelect =" SELECT SUM(Population) FROM country WHERE Continent='"+area;
//            }
//            else if(selection==3)
//            {
//                System.out.print("Enter a Region: ");
//                area =br.readLine();
//                System.out.print("\n");
//
//                // Create string for SQL statement
//                strSelect =" SELECT SUM(Population) FROM country WHERE Region='"+area;
//            }
//            else if(selection==4)
//            {
//                System.out.print("Enter a Country: ");
//                area =br.readLine();
//                // Create string for SQL statement
//                strSelect =" SELECT SUM(Population) FROM country WHERE Name='"+area;
//            }
//            else if (selection == 6)
//            {
//                System.exit(0);
//            }
//            else
//            {
//                System.out.println("This is not a valid Option!");
//                System.exit(0);
//            }
//
//            // Execute SQL statement
//            ResultSet res = stmt.executeQuery(strSelect);
//
//            //Extract the info from the current record in the ResultSet
//            long population;
//            if (!res.next())
//                return null;
//            else {
//                population = res.getLong("SUM(Population)");
//            }
//            long[] result = new long[3];
//            result[0] = population;
//            return result;
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            System.out.println("Failed to get capital cities details");
//
//        }
//        return null;
//
//    }




    public void displayCountries(ArrayList<Country> countries) {

        //display in table format

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        String format = "%1$-10s %2$-60s %3$-20s %4$-40s  %5$-15s  %6$-10s \n";
        System.out.format(format, "Code", "Country", "Continent", "Region", "Population", "Capital");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Country country : countries) {
            //display country report
            System.out.format(format, country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital());
            System.out.format("The population of the world: ", country.getTotalPopulation());

        }


        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayCities(ArrayList<City> cities) {
        //display in table format

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        String format = "%1$-20s %2$-25s %3$-25s %4$-20s  \n";
        System.out.format(format, "City", "Country", "District", "Population");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        for (City city : cities) {
            //display  city report
            System.out.format(format, city.getCname(), city.getCccode(), city.getCd(), city.getCpop());

        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayCapitalCity(ArrayList<City> capitals)
    {
        //display in table format

        System.out.println("--------------------------------------------------------------------------------------------------------------");
        String format="%1$-25s %2$-60s %3$-20s \n ";
        System.out.format(format, "Name", "Country", "Population");
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        for (City capital : capitals)
        {
            //display capital city report
            System.out.format(format,capital.getCname(),capital.getCccode(),capital.getCpop());
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------");
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */

    public static void main(String[] args) throws IOException {

        String yn,condition;
        int selection;
        yn = "y";


        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();

        //ask input from user
        BufferedReader m = new BufferedReader(new InputStreamReader(System.in));
        boolean mainLoop = false;
        int choice;
        do {

            System.out.println(" Main Menu\n");
            System.out.print("1.) Country Report \n");
            System.out.print("2.) City Report.\n");
            System.out.print("3.) Capital City Report.\n");
            System.out.print("4.) Population of the People.\n");
            System.out.print("5.) Exit\n");
            System.out.print("\nEnter Your Menu Choice: ");

            choice = Integer.parseInt(m.readLine());
            switch (choice) {

                case 1:
                    // display country report's menu
                    //ask input from user
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        System.out.print("1.) All countries in the Word \n");
                        System.out.print("2.) All countries in a Continent\n");
                        System.out.print("3.) All countries in a Region\n");
                        System.out.print("4.) Top populated Countries in the World \n");
                        System.out.print("5.) Top populated Countries in a Continent\n");
                        System.out.print("6.) Top populated Countries in a Region\n");
                        System.out.print("7.) Exit\n");
                        System.out.print("\nEnter Your Sub-menu Choice: ");

                        selection = Integer.parseInt(br.readLine());

                        ArrayList<Country> countries = a.getCountry(selection);
                        a.displayCountries(countries);
                        System.out.println("Number of Countries :" + countries.size());

                        System.out.print("Do you want to continue (y/n): ");
                        yn = br.readLine();
                        if (yn.equals("n")) {
                            System.exit(0); //terminate program if user enter "n"
                        }
                        break;
                case 2:
//                    // display city report's menu
//                    //ask input from user
                        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                        System.out.print("1.)  All cities in the Word \n");
                        System.out.print("2.)  All cities in a Continent\n");
                        System.out.print("3.)  All cities in a Region\n");
                        System.out.print("4.)  All cities in a Country \n");
                        System.out.print("5.)  All cities in a District\n");
                        System.out.print("6.)  Top populated cities in the world \n");
                        System.out.print("7.)  Top populated cities in a Continent\n");
                        System.out.print("8.)  Top populated cities in a Region\n");
                        System.out.print("9.)  Top populated cities in a Country \n");
                        System.out.print("10.) Top populated cities in a District\n");
                        System.out.print("11.) Exit\n");
                        System.out.print("\nEnter Your Sub-menu Choice: ");

                        selection = Integer.parseInt(br2.readLine());

                        ArrayList<City> cities = a.getCity(selection);
                        a.displayCities(cities);
                        System.out.println("Number of Cities :" + cities.size());

                        System.out.print("Do you want to continue (y/n): ");
                        yn = br2.readLine();
                        if (yn.equals("n")) {
                            System.exit(0); //terminate program if user enter "n"
                        }

                        break;
                case 3:
//                    // display capital city report's menu
////                    //ask input from user
                        BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
                        System.out.print("1.) All capital cities in the Word \n");
                        System.out.print("2.) All capital cities in a Continent\n");
                        System.out.print("3.) All capital cities in a Region\n");
                        System.out.print("4.) Top populated capital cities in the World \n");
                        System.out.print("5.) Top populated capital cities in a Continent\n");
                        System.out.print("6.) Top populated capital cities in a Region\n");
                        System.out.print("7.) Exit\n");
                        System.out.print("\nEnter Your Sub-menu Choice: ");

                        selection = Integer.parseInt(br3.readLine());

                        ArrayList<City> capitals    = a.getCapitalCity(selection);
                        a.displayCapitalCity(capitals);
                        System.out.println("Number of capital cities :" + capitals.size());

                        System.out.print("Do you want to continue (y/n): ");
                        yn = br3.readLine();
                        if (yn.equals("n")) {
                            System.exit(0);//terminate program if user enter "n"
                        }
                        break;

                case 4:

                            BufferedReader br4 = new BufferedReader(new InputStreamReader(System.in));
                            System.out.print("1.) The population of people in each Continent \n");
                            System.out.print("2.) The population of people in each region\n");
                            System.out.print("3.) The population of people in each country\n");
    //                        condition=br4.readLine();
                            int cond= Integer.parseInt(br4.readLine());
                            switch (cond){
                                case 1:
                                    a.getPopulationofthePeople("Continent");
                                    break;
                                case 2:
                                    a.getPopulationofthePeople("Region");
                                    break;
                                case 3:
                                    a.getPopulationofthePeople("Country");
                                    break;
                                default: //if user enter other number instead of 1,2,3,4,5
                                    System.out.println("This is not a valid Menu Option! Please Select Another");
                                    break;
                            }

                        break;

                case 5:
                        System.out.println("Exiting Program..."); //terminate program if user enter 4
                        break;
                default: //if user enter other number instead of 1,2,3,4,5
                        System.out.println("This is not a valid Menu Option! Please Select Another");
                        break;
            }
        }while (choice!=5);

            // Disconnect from database
            a.disconnect();
    }
}