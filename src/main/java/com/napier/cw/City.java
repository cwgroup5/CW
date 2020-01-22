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

    /**
     * City's Name
     */
    private String cname;

    /**
     * City's Country Code
     */
    private String cccode;
    /**
     * City's District
     */
    private String cd;

    /**
     * City's Population
     */
    private int cpop;

    /* Getter cityid */
    public int getCityid() {
        return cityid;
    }

    /* Setter cityid */
    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    /* Getter cname */
    public String getCname() {
        return cname;
    }

    /* Setter cname */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /* Getter ccode */
    public String getCccode() {
        return cccode;
    }

    /* Setter ccode */
    public void setCccode(String cccode) {
        this.cccode = cccode;
    }

    /* Getter cd */
    public String getCd() {
        return cd;
    }

    /* Setter cd */
    public void setCd(String cd) {
        this.cd = cd;
    }

    /* Getter cpop */
    public int getCpop() {
        return cpop;
    }

    /* Setter cpop */
    public void setCpop(int cpop) {
        this.cpop = cpop;
    }
}