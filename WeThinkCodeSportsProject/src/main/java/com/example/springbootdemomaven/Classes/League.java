package com.example.springbootdemomaven.Classes;

public class League {
    public String idLeague;
    public String idSoccerXML;
    public String idAPIfootball;
    public String strSport;
    public String strLeague;
    public String strLeagueAlternate;
    public String strDivision;
    public String idCup;
    public String strCurrentSeason;
    public String intFormedYear;
    public String dateFirstEvent;
    public String strGender;
    public String strCountry;
    public String strWebsite;
    public String strFacebook;
    public String strTwitter;
    public String strYoutube;
    public String strRSS;
    public String strDescriptionEN;
    public String strDescriptionDE;
    public String strDescriptionFR;
    public String strDescriptionIT;
    public String strDescriptionCN;
    public String strDescriptionJP;
    public String strDescriptionRU;
    public String strDescriptionES;
    public String strDescriptionPT;
    public String strDescriptionSE;
    public String strDescriptionNL;
    public String strDescriptionHU;
    public String strDescriptionNO;
    public String strDescriptionPL;
    public String strDescriptionIL;
    public String strFanart1;
    public String strFanart2;
    public String strFanart3;
    public String strFanart4;
    public String strBanner;
    public String strBadge;
    public String strLogo;
    public String strPoster;
    public String strTrophy;
    public String strNaming;
    public String strComplete;
    public String strLocked;
    public int Count;

    public int getCount() {
        return Count;
    }

    public void IncreaseCount() {
        try {
            this.Count = this.Count + 1;
        } catch (Exception e) {
            this.Count = 0;
        }
    }

    public void setStrLogo(String url) {
        this.strLogo = url;
    }

    public String getStrLocked() {
        return strLocked;
    }

    public String getStrComplete() {
        return strComplete;
    }

    public String getStrNaming() {
        return strNaming;
    }

    public String getStrTrophy() {
        return strTrophy;
    }

    public String getStrPoster() {
        return strPoster;
    }

    public String getStrLogo() {
        return strLogo;
    }

    public String getStrBadge() {
        return strBadge;
    }

    public String getStrBanner() {
        return strBanner;
    }

    public String getStrFanart4() {
        return strFanart4;
    }

    public String getStrFanart3() {
        return strFanart3;
    }

    public String getStrFanart2() {
        return strFanart2;
    }

    public String getStrFanart1() {
        return strFanart1;
    }

    public String getStrDescriptionIL() {
        return strDescriptionIL;
    }

    public String getStrDescriptionPL() {
        return strDescriptionPL;
    }

    public String getStrDescriptionNO() {
        return strDescriptionNO;
    }

    public String getStrDescriptionHU() {
        return strDescriptionHU;
    }

    public String getStrDescriptionNL() {
        return strDescriptionNL;
    }

    public String getStrDescriptionSE() {
        return strDescriptionSE;
    }

    public String getStrDescriptionPT() {
        return strDescriptionPT;
    }

    public String getStrDescriptionES() {
        return strDescriptionES;
    }

    public String getStrDescriptionRU() {
        return strDescriptionRU;
    }

    public String getStrDescriptionJP() {
        return strDescriptionJP;
    }

    public String getStrDescriptionCN() {
        return strDescriptionCN;
    }

    public String getStrDescriptionIT() {
        return strDescriptionIT;
    }

    public String getStrDescriptionFR() {
        return strDescriptionFR;
    }

    public String getStrDescriptionDE() {
        return strDescriptionDE;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public String getStrRSS() {
        return strRSS;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public String getStrTwitter() {
        return strTwitter;
    }

    public String getStrFacebook() {
        return strFacebook;
    }

    public String getStrWebsite() {
        return strWebsite;
    }

    public String getStrCountry() {
        return strCountry;
    }

    public String getStrGender() {
        return strGender;
    }

    public String getDateFirstEvent() {
        return dateFirstEvent;
    }

    public String getIntFormedYear() {
        return intFormedYear;
    }

    public String getStrCurrentSeason() {
        return strCurrentSeason;
    }

    public String getIdCup() {
        return idCup;
    }

    public String getStrDivision() {
        return strDivision;
    }

    public String getStrLeagueAlternate() {
        return strLeagueAlternate;
    }

    public String getStrLeague() {
        return strLeague;
    }

    public String getStrSport() {
        return strSport;
    }

    public String getIdAPIfootball() {
        return idAPIfootball;
    }

    public String getIdSoccerXML() {
        return idSoccerXML;
    }

    public String getIdLeague() {
        return idLeague;
    }

    public String toJsonString() {
        return "{" +
                "idLeague:\"" + this.idLeague + "\"" +
                ", idSoccerXML:\"" + this.idSoccerXML + "\"" +
                ", idAPIfootball:\"" + this.idAPIfootball + "\"" +
                ", strSport:\"" + this.strSport + "\"" +
                ", strLeague:\"" + this.strLeague + "\"" +
                ", strLeagueAlternate:\"" + this.strLeagueAlternate + "\"" +
                ", strDivision:\"" + this.strDivision + "\"" +
                ", idCup:\"" + this.idCup + "\"" +
                ", strCurrentSeason:\"" + this.strCurrentSeason + "\"" +
                ", intFormedYear:\"" + this.intFormedYear + "\"" +
                ", dateFirstEvent:\"" + this.dateFirstEvent + "\"" +
                ", strGender:\"" + this.strGender + "\"" +
                ", strCountry:\"" + this.strCountry + "\"" +
                ", strWebsite:\"" + this.strWebsite + "\"" +
                ", strFacebook:\"" + this.strFacebook + "\"" +
                ", strTwitter:\"" + this.strTwitter + "\"" +
                ", strYoutube:\"" + this.strYoutube + "\"" +
                ", strRSS:\"" + this.strRSS + "\"" +
                ", strDescriptionEN:\"" + this.strDescriptionEN + "\"" +
                ", strDescriptionDE:\"" + this.strDescriptionDE + "\"" +
                ", strDescriptionFR:\"" + this.strDescriptionFR + "\"" +
                ", strDescriptionIT:\"" + this.strDescriptionIT + "\"" +
                ", strDescriptionCN:\"" + this.strDescriptionCN + "\"" +
                ", strDescriptionJP:\"" + this.strDescriptionJP + "\"" +
                ", strDescriptionRU:\"" + this.strDescriptionRU + "\"" +
                ", strDescriptionES:\"" + this.strDescriptionES + "\"" +
                ", strDescriptionPT:\"" + this.strDescriptionPT + "\"" +
                ", strDescriptionSE:\"" + this.strDescriptionSE + "\"" +
                ", strDescriptionNL:\"" + this.strDescriptionNL + "\"" +
                ", strDescriptionHU:\"" + this.strDescriptionHU + "\"" +
                ", strDescriptionNO:\"" + this.strDescriptionNO + "\"" +
                ", strDescriptionPL:\"" + this.strDescriptionPL + "\"" +
                ", strDescriptionIL:\"" + this.strDescriptionIL + "\"" +
                ", strFanart1:\"" + this.strFanart1 + "\"" +
                ", strFanart2:\"" + this.strFanart2 + "\"" +
                ", strFanart3:\"" + this.strFanart3 + "\"" +
                ", strFanart4:\"" + this.strFanart4 + "\"" +
                ", strBanner:\"" + this.strBanner + "\"" +
                ", strBadge:\"" + this.strBadge + "\"" +
                ", strLogo:\"" + this.strLogo + "\"" +
                ", strPoster:\"" + this.strPoster + "\"" +
                ", strTrophy:\"" + this.strTrophy + "\"" +
                ", strNaming:\"" + this.strNaming + "\"" +
                ", strComplete:\"" + this.strComplete + "\"" +
                ", strLocked:\"" + this.strLocked + "\"" +
                ", Count:" + this.Count +
                "}";
    }

}
