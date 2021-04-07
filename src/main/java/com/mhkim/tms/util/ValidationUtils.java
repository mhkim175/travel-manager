package com.mhkim.tms.util;

import static java.util.regex.Pattern.matches;

public class ValidationUtils {

    public static boolean checkEmail(String email) {
        return matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", email);
    }
    
}
