package com.dmdev.http.locale;

import java.util.ResourceBundle;

public class LocaleRunner {
    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("translations");
        System.out.println(resourceBundle.getString("page.logon.password"));
    }
}
