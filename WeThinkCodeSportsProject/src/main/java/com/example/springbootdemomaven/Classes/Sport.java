package com.example.springbootdemomaven.Classes;


public class Sport {
    public String idSport;
    public String strSport;
    public String strFormat;
    public String strSportThumb;
    public String strSportThumbGreen;
    public String strSportDescription;

    public void setStrSportThumb(String url){this.strSportThumb = url;}

    public String getStrSportDescription() {
        return strSportDescription;
    }

    public String getStrSportThumbGreen() {
        return strSportThumbGreen;
    }

    public String getStrSportThumb() {
        return strSportThumb;
    }

    public String getStrFormat() {
        return strFormat;
    }

    public String getStrSport() {
        return strSport;
    }

    public String getIdSport() {
        return idSport;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "idSport='" + idSport + '\'' +
                ", strSport='" + strSport + '\'' +
                ", strFormat='" + strFormat + '\'' +
                ", strSportThumb='" + strSportThumb + '\'' +
                ", strSportThumbGreen='" + strSportThumbGreen + '\'' +
                ", strSportDescription='" + strSportDescription + '\'' +
                '}';
    }
}
