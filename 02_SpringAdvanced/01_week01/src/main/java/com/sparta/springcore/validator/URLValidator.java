package com.sparta.springcore.validator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLValidator {
    public static boolean isValidUrl(String url)
    {
        try {
            new URL(url).toURI(); //url형태로 바꾸기. 만약 바귀지 않으면 익셉션 자동으로 뜸
            return true;
        }
        catch (URISyntaxException exception) {
            return false;
        }
        catch (MalformedURLException exception) {
            return false;
        }
    }
}
