package com.napier.cw;

/**
 * Represents an City
 */
public class City
{
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

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCccode() {
        return cccode;
    }

    public void setCccode(String cccode) {
        this.cccode = cccode;
    }

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


}
