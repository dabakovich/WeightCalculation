package com.dabakovich;

/**
 * Created by David on 01.07.2017, 2:12.
 */
public class Atom {
    private static final Elements E = new Elements();
    private String name;
    private int amount = 1;
    private double percentage = 1;
    private double atomicMass;

    Atom(String atom) {
        String[] splitAtom = atom.split("(?=[0-9])", 2);
        this.name = splitAtom[0];
        if (splitAtom.length > 1) this.amount = Integer.valueOf(splitAtom[1]);
        this.atomicMass = E.getAtomMass(name);
    }

    double getSummaryMass() {
        return amount * atomicMass * percentage;
    }

    int getAmount() {
        return amount;
    }

    void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atom atom = (Atom) o;

        return name != null ? name.equals(atom.name) : atom.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        String stringAmount = "";
        double summaryAmount = amount * percentage;
        if (summaryAmount != 1) {
            if (summaryAmount % 1 == 0) stringAmount += Math.round(summaryAmount);
            else stringAmount += String.format("%.2f", summaryAmount);
        }
        return name + stringAmount;
    }
}
