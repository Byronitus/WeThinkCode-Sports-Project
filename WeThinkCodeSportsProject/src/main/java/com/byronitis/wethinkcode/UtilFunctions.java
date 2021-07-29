package com.byronitis.wethinkcode;

import org.apache.logging.log4j.util.Strings;
import org.ini4j.Wini;

import java.io.FileInputStream;
import java.io.InputStream;

public class UtilFunctions {

    /**
     * @param valueToChange The value to update
     * @param toMatch       partial string to match
     * @param replacement   what to replace it with
     * @return the changed string
     */
    public static String changeString(String valueToChange, String toMatch, String replacement) {

        if (Strings.isBlank(valueToChange) || Strings.isBlank(toMatch) || replacement == null) {
            return valueToChange;
        }

        return valueToChange.replaceAll(toMatch, replacement);
    }

    /**
     * Changes thesportsdb's URLs to an internal format
     *
     * @param urlToChange the URL to change
     * @return the changed URL
     */
    public static String changeURL(String urlToChange, String url) {
        String replacement = "small/";
        return url + changeString(urlToChange, url, replacement);
    }

    public static String readFromIni() {
        try {
            InputStream inputStream = new FileInputStream("APIKey.ini");
            Wini iniFile = new Wini(inputStream);
            return iniFile.get("APIKey", "key", String.class);
        } catch (Exception e) {
            return "1";
        }
    }
}
