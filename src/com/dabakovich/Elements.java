package com.dabakovich;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by David on 28.10.2015.
 * CLASS ABOUT:
 */
class Elements {
    private static final Properties ELEMENTS = new Properties();

    Elements() {
        try {
            FileInputStream input = new FileInputStream("elements.dat");
            ELEMENTS.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    float getAtomMass(String name) {
        return Float.valueOf((String) ELEMENTS.get(name));
    }
}
