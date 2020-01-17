package com.napier.cw;

/**
 * Represents an City
 */
public class Country
{
    @Override
    public String toString() {
        return "Country{" +
                "Code='" + Code + '\'' +
                ", Name='" + Name + '\'' +
                ", Continent='" + Continent + '\'' +
                '}';
    }

    public String Code;

    /**
    /**
     * Name
     */
    public String Name;

    /**
     *  Continent
     */
    public String Continent;

    /**
     *  Region
     */
    public String Region;

    /**
     *  Region
     */
    public int Population;

    /**
     *  Captial
     */
    public String Captial;




}