package org.example.common.utils;
import javax.servlet.http.HttpServletRequest;

public class URLUtils {
    public static String getCurrentURL(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String queryString = request.getQueryString();

        /*if (queryString != null) {
            url.append("?").append(queryString);
        }*/

        return url.toString();
    }
}
