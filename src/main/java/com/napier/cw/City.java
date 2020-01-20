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
    private int cityid;
    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }
    /**
     * City's Name
     */
    private String cname;
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    /**
     * City's Country Code
     */
    private String cccode;
    public String getCccode() {
        return cccode;
    }

    public void setCccode(String cccode) {
        this.cccode = cccode;
    }
    /**
     * City's District
     */
    private String cd;
    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public int getCpop() {
        return cpop;
    }

    public void setCpop(int cpop) {
        this.cpop = cpop;
    }

    /**
     * City's Population
     */
    private int cpop;
}