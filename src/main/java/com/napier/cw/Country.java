package com.napier.cw;

// represents on Country

public class Country {



    /** Code Number for Country **/
    private String Code; // private = restricted access

    @Override
    public String toString() {
        return "Country{" +
                "Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", Continent='" + Continent + '\'' +
                ", Region='" + Region + '\'' +
                ", Population=" + Population +
                ", Capital='" + Capital + '\'' +
                '}';
    }

    // Getter
    public String getCode() {
        return Code;
    }
    // Setter
    public void setCode(String code) {
        Code = code;
    }

    /** Name for Country **/
    private String Name; // private = restricted access

    //Getter
    public String getName() {
        return Name;
    }
    //Setter

    public void setName(String name) {
        Name = name;
    }

    /** Continent for Country **/

    private String Continent; // private = restricted access
    //Getter
    public String getContinent() {
        return Continent;
    }
    //Setter

    public void setContinent(String continent) {
        Continent = continent;
    }

    /**  Region of Country **/
    private String Region;// private = restricted access

    //Getter
    public String getRegion() {
        return Region;
    }
    //Setter
    public void setRegion(String region) {
        Region = region;
    }

    /** Population of the Country **/
    private int Population;// private = restricted access


    //Getter
    public int getPopulation() {
        return Population;
    }
    //Setter
    public void setPopulation(int population) {
        Population = population;
    }

    private  int TotalPopulation;

    public int getTotalPopulation() {
        return TotalPopulation;
    }

    public void setTotalPopulation(int totalPopulation) {
        TotalPopulation = totalPopulation;
    }




    /** Capital City Country **/

    private String Capital;// private = restricted access
    //Getter
    public String getCapital() {
        return Capital;
    }
    //Setter
    public void setCapital(String capital) {
        Capital = capital;
    }
}








