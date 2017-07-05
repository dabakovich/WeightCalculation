package com.dabakovich;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by David on 01.07.2017.
 */
public class Molecule {
    private boolean leftSide;
    private int amount = 1;
    private List<Atom> atoms;
    private double percentage = 1;
    private double mass;
    private boolean host = true;
    private double originalMass;

    private Molecule() {
    }

    Molecule(String molecule) {
        if (molecule.contains("g)")) {
            mass = Double.valueOf(molecule.substring(molecule.indexOf("(") + 1, molecule.lastIndexOf("g")));
            originalMass = mass;
            molecule = molecule.substring(0, molecule.indexOf("("));
        }
        List<String> splitMolecule = new ArrayList<>(Arrays.asList(molecule.split("(?=[A-Z])")));
        if (!Character.isLetter(splitMolecule.get(0).charAt(0))) {
            amount = Integer.valueOf(splitMolecule.get(0));
            splitMolecule.remove(0);
        }
        atoms = splitMolecule.stream().map(Atom::new).collect(Collectors.toList());
    }

    Molecule setupPercentages(Atom host, Atom admixture, double percentage) {
        Molecule newMolecule = new Molecule();
        newMolecule.setLeftSide(this.leftSide);
        newMolecule.setAmount(this.amount);
        newMolecule.setPercentage(percentage);
        newMolecule.makeAdmixture();

        List<Atom> newAtoms = new ArrayList<>(this.atoms);
        admixture.setAmount(atoms.get(atoms.indexOf(host)).getAmount());
        newAtoms.set(newAtoms.indexOf(host), admixture);
        newMolecule.setAtoms(newAtoms);

//        System.out.println("Before:" + String.format("%.2f", this.percentage) + " for " + toString());
        this.percentage = this.percentage - percentage;
//        if (this.percentage < 0) this.percentage = 0;
//        System.out.println("After:" + String.format("%.2f", this.percentage));
        newMolecule.setMass(this.originalMass * newMolecule.getPercentage());
        this.mass = this.originalMass * this.percentage;
        return newMolecule;
    }

    double getSummaryMolecularMass() {
        double molecularMass = 0;
        for (Atom atom : atoms) {
            molecularMass += atom.getSummaryMass();
        }
        return amount * molecularMass * percentage;
    }

    boolean isLeftSide() {
        return leftSide;
    }

    void setLeftSide(boolean leftSide) {
        this.leftSide = leftSide;
    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    List<Atom> getAtoms() {
        return atoms;
    }

    private double getPercentage() {
        return percentage;
    }

    private void setAtoms(List<Atom> atoms) {
        this.atoms = atoms;
    }

    private void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    double getMass() {
        return mass;
    }

    void setMass(double mass) {
        this.mass = mass;
    }

    boolean isHost() {
        return host;
    }

    private void makeAdmixture() {
        this.host = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Molecule molecule = (Molecule) o;

        return amount == molecule.amount && (atoms != null ? atoms.containsAll(molecule.atoms) : molecule.atoms == null);
    }

    @Override
    public int hashCode() {
        int result = amount;
        result = 31 * result + (atoms != null ? atoms.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder molecule;
        double summaryAmount = amount * percentage;
        if (summaryAmount != 1) {
            if (summaryAmount % 1 == 0) molecule = new StringBuilder(String.valueOf(Math.round(summaryAmount)));
            else
                molecule = new StringBuilder(String.format("%.2f", summaryAmount));
        } else molecule = new StringBuilder();

        for (Atom atom : atoms) {
            molecule.append(atom.toString());
        }

        molecule.append("(m=").append(String.format("%.4f", mass)).append("g)");
        return molecule.toString();
    }
}
