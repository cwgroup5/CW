package com.napier.cw;

// represents on Country

public class Country {
    /** Code Number for Country **/
    private String Code; // private = restricted access
    /** Name for Country **/
    private String Name; // private = restricted access
    /** Continent for Country **/
    private String Continent; // private = restricted access
    /**  Region of Country **/
    private String Region;// private = restricted access
    /** Population of the Country **/
    private int Population;// private = restricted access
    /** Capital City Country **/

    private String Capital;// private = restricted access
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



    //Getter
    public String getName() {
        return Name;
    }
    //Setter

    public void setName(String name) {
        Name = name;
    }


    //Getter
    public String getContinent() {
        return Continent;
    }
    //Setter

    public void setContinent(String continent) {
        Continent = continent;
    }



    //Getter
    public String getRegion() {
        return Region;
    }
    //Setter
    public void setRegion(String region) {
        Region = region;
    }




    //Getter
    public int getPopulation() {
        return Population;
    }
    //Setter
    public void setPopulation(int population) {
        Population = population;
    }


    //Getter
    public String getCapital() {
        return Capital;
    }
    //Setter
    public void setCapital(String capital) {
        Capital = capital;
    }
}








