package com.example.springbootdemomaven.Classes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sport {
    public @Id Long idSport;
    public String strSport;
    public String strFormat;
    public String strSportThumb;
    public String strSportThumbGreen;

    @Column(length=10485760)
    public String strSportDescription;

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

    public Long getIdSport() {
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
