package com.napier.cw;

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

    public void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
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
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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

//      Execute SQL statement
            ResultSet res = stmt.executeQuery(strSelect);
            // Return new employee if valid.
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
                //Add the data to the apcu array
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
    public  void getPopulation(String name)
    {
        try
        {
            long counpop=0;
            long ctypop=0;
            String area="";
            // Create an SQL statement
            Statement stmt = con.createStatement();


            String strSelect="SELECT "+name+",sum(DISTINCT country.Population), sum(city.Population) FROM city,country "
                    + "WHERE city.CountryCode=country.Code GROUP BY "+name;
            ResultSet rset = stmt.executeQuery(strSelect);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format("%1$-30s %2$-35s %3$-30s %4$-20s %5$-40s %6$-20s \n","Area","Total Population ","Living in Cities","Living in Cities in %","Not living in Cities","Not Living in Cities in %"));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(rset.next())
            {

                area = rset.getString(1);
                counpop = rset.getLong(2);
                ctypop = rset.getLong(3);
                long nlc = counpop - ctypop;
                System.out.format("%1$-30s %2$-35s %3$-40s %4$-20s %5$-40s %6$-20s \n", area,counpop,ctypop,ctypop*100 / counpop + " %",nlc,nlc*100 / counpop +" %");
            }

        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            System.out.println("Failed to get population of the people");

        }
    }
    public  void getPopulationInformation(String pinfo)
    {
        try
        {

            long countryPop=0;

            String area="";
            // Create an SQL statement
            Statement stmt = con.createStatement();

            if(pinfo.equals("World"))
            {
                strSelect="SELECT DISTINCT sum(country.Population) FROM country";
                ResultSet res = stmt.executeQuery(strSelect);
                while (res.next())
                {
                    countryPop=res.getLong(1);
                    System.out.format("%1$-20s %2$-25s \n","World Population",countryPop);
                }

            }
            else{
                String strSelect="SELECT "+pinfo+",sum(DISTINCT country.Population)FROM city,country "
                        + "WHERE city.CountryCode=country.Code GROUP BY "+pinfo;
                ResultSet rset = stmt.executeQuery(strSelect);
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                System.out.println(String.format("%1$-20s %2$-25s ","Area","Total Population"));
                System.out.println("------------------------------------------------------------------------------------------------------------------");
                while(rset.next())
                {
                    area = rset.getString(1);
                    countryPop = rset.getLong(2);
                    System.out.format("%1$-20s %2$-25s \n",area,countryPop);
                    System.out.println("--------------------------------------------------------------------------------------------------------------");
                }
            }
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            System.out.println("Failed to get population of the people");

        }
    }



    public void displayCountries(ArrayList<Country> countries) {
        // Check country is not null
        if (countries == null)
        {
            System.out.println("No country");
            return;
        }
        //display in table format

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        String format = "%1$-10s %2$-60s %3$-20s %4$-40s  %5$-15s  %6$-10s \n";
        System.out.format(format, "Code", "Country", "Continent", "Region", "Population", "Capital");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (Country country : countries) {
            if (country == null)
                continue;
            System.out.format(format, country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital());


        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }

    public void displayCities(ArrayList<City> cities) {
        if (cities == null)
        {
            System.out.println("No city");
            return;
        }
        //display in table format

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        String format = "%1$-10s %2$-25s %3$-25s %4$-20s  \n";
        System.out.format(format, "City", "Country", "District", "Population");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        for (City city : cities) {
            if (city == null)
                continue;
            System.out.format(format, city.getCname(), city.getCccode(), city.getCd(), city.getCpop());

        }
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayCapitalCity(ArrayList<City> capitals)
    {
        //display in table format
        if (capitals == null)
        {
            System.out.println("No capital city");
            return;
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        String format="%1$-25s %2$-60s %3$-20s \n ";
        System.out.format(format, "Name", "Country", "Population");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (City capital : capitals)
        {
            if (capital == null)
                continue;
            System.out.format(format,capital.getCname(),capital.getCccode(),capital.getCpop());
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

    public static void main(String[] args) throws IOException {

        String yn;
        int selection;
        yn = "y";

        // Create new Application

        App a = new App();
        // Connect to database
        if (args.length < 1)
        {
            a.connect("localhost:3306");
        }
        else
        {
            a.connect(args[0]);
        }
        BufferedReader m = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("Main Menu \n Enter Number \n 1. Country Report \n 2. City Report \n 3. Capital City Report \n");
        boolean mainLoop = false;
//        mm = Integer.parseInt(m.readLine());
        int choice;
        do {

            System.out.println("===============================================");
            System.out.println("                 Main Menu");
            System.out.println("===============================================");
            System.out.print("1.) Country Report \n");
            System.out.print("2.) City Report.\n");
            System.out.print("3.) Capital City Report.\n");
            System.out.print("4.) Population of the People.\n");
            System.out.print("5.) Population.\n");
            System.out.print("6.) Exit\n");
            System.out.println("===============================================");
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

                    ArrayList<City> capitals = a.getCapitalCity(selection);
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
                    System.out.print("\nEnter Your Sub-menu Choice: ");
                    //                        condition=br4.readLine();
                    int condition = Integer.parseInt(br4.readLine());

                    switch (condition) {
                        case 1:
                            a.getPopulation("Continent");
                            break;
                        case 2:
                            a.getPopulation("Region");
                            break;
                        case 3:
                            a.getPopulation("country.Name");
                            break;
                        default: //if user enter other number instead of 1,2,3,4,5
                            System.out.println("This is not a valid Menu Option! Please Select Another");
                            break;
                    }
                    break;
                case 5:
                    BufferedReader br5 = new BufferedReader(new InputStreamReader(System.in));
                    System.out.print("1.) The population of the world \n");
                    System.out.print("2.) The population of a continent\n");
                    System.out.print("3.) The population of a region\n");
                    System.out.print("4.) The population of a country\n");
                    System.out.print("5.) The population of a district\n");
                    System.out.print("6.) The population of a city\n");
                    System.out.print("\nEnter Your Sub-menu Choice: ");
                    //                        condition=br4.readLine();
                    int popInfo = Integer.parseInt(br5.readLine());

                    switch (popInfo) {
                        case 1:
                            a.getPopulationInformation("World");
                            break;
                        case 2:
                            a.getPopulationInformation("Continent");
                            break;
                        case 3:
                            a.getPopulationInformation("Region");
                            break;
                        case 4:
                            a.getPopulationInformation("country.Name");
                            break;
                        case 5:
                            a.getPopulationInformation("District");
                            break;
                        case 6:
                            a.getPopulationInformation("city.Name");
                            break;
                        default: //if user enter other number instead of 1,2,3,4,5
                            System.out.println("This is not a valid Menu Option! Please Select Another");
                            break;
                    }
                    break;
                case 6:
                    System.out.println("Exiting Program..."); //terminate program if user enter 4
                    break;
                default: //if user enter other number instead of 1,2,3,4,5
                    System.out.println("This is not a valid Menu Option! Please Select Another");
                    continue;
            }
        }while (choice!=6);

        // Disconnect from database
        a.disconnect();


    }
}