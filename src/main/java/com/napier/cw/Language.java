package com.napier.cw;

public class Language {
    private String CountyCode;

    public String getCountyCode() {
        return CountyCode;
    }

    public void setCountyCode(String countyCode) {
        CountyCode = countyCode;
    }

    private String Language;

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    private Boolean IsOfficial;

    @Override
    public String toString() {
        return "Language{" +
                "CountyCode='" + CountyCode + '\'' +
                ", Language='" + Language + '\'' +
                ", IsOfficial=" + IsOfficial +
                ", Percentage=" + Percentage +
                '}';
    }

    public Boolean getOfficial() {
        return IsOfficial;
    }

    public void setOfficial(Boolean official) {
        IsOfficial = official;
    }

    private float Percentage;

    public float getPercentage() {
        return Percentage;
    }

    public void setPercentage(float percentage) {
        Percentage = percentage;
    }



}
