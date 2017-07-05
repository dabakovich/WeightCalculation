package com.dabakovich;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by David on 01.07.2017.
 */
public class Starter {

    public static void main(String[] args) {
        String testEquation = "MgO + Ga2O3 = MgGa2O4(2g)";
        List<String> admixtures = new ArrayList<>(Arrays.asList("Mg:Zn(50%)", "Ga:Eu(4%)"));

        Equation equation = new Equation(testEquation, admixtures);
        System.out.println(equation);
    }
}
