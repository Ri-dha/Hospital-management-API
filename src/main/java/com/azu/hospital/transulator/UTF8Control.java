package com.azu.hospital.transulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UTF8Control extends ResourceBundle.Control {
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
            throws IllegalAccessException, InstantiationException, IOException {


        String resourceName = toResourceName(toBundleName(baseName, locale), "properties");

        try (InputStream stream = loader.getResourceAsStream(resourceName)) {
            assert stream != null;
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                return new PropertyResourceBundle(bufferedReader);
            }
        }
    }
}

