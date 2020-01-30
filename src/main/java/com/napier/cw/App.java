package com.napier.cw;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;



public class App
{
    int num=0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /* =============================== =======================*/
    /* Functions of Extract Information from Database Section */

    //Extract populated cities of a continent from the database
    public  void getPCitiesConti(String name)
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
            System.out.println(String.format("%-30s %-30s %-40s %-40s",
                    "Area","Total Population of People ","Total Population Living in Cities","Not living"));
            while(rset.next())
            {
                area = rset.getString(1);
                counpop = rset.getLong(2);
                ctypop = rset.getLong(3);
                String pcu_string =
                        String.format("%-30s %-30s %-40s %-40s", area,counpop,ctypop,counpop-ctypop);
                System.out.println(pcu_string);
            }

        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");

        }
    }

    /* =======================================  ===== */
    /* Database Connection and Disconnectinon Section */

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

//        String yn;
        int selection;
//        yn ="y";
//
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String condi;
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();
        System.out.print("Enter Continent or Region or Country: ");
        condi=br.readLine();
        a.getPCitiesConti(condi);
        // Disconnect from database
        a.disconnect();
    }
}