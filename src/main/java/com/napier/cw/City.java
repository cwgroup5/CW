package com.napier.cw;


/**
 * Represents an City
 */
public class City
{
    @Override
    public String toString() {
        return "City{" +
                "cityid=" + cityid +
                ", cname='" + cname + '\'' +
                ", cccode='" + cccode + '\'' +
                ", cd='" + cd + '\'' +
                ", cpop=" + cpop +
                '}';
    }
    /**
     * City int;
     */
    public int cityid;

    /**
     * City's Name
     */
    public String cname;

    /**
     * City's Country Code
     */
    public String cccode;

    /**
     * City's District
     */
    public String cd;

    /**
     * City's Population
     */
    public int cpop;


}