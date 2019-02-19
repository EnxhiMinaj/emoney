package com.emoney.core.utils;

/**
 * Created by Anil Kumal on 02/02/2019.
 */
public class SecurityUtils {

    private static final String CHARACTER = "abcEefHghijklmABCyDFzGsUI";
    private static final String NUMBER = "0123456789";

    public static String generateRandomPassword(int characterLength, int numericLength) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < characterLength; i++) {
            int ndx = (int) (Math.random() * CHARACTER.length());
            sb.append(CHARACTER.charAt(ndx));
        }
        for (int i = 0; i < numericLength; i++) {
            int ndx = (int) (Math.random() * NUMBER.length());
            sb.append(NUMBER.charAt(ndx));
        }
        return sb.toString();
    }


}
