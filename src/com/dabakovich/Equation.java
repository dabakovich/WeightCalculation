package com.dabakovich;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dabak on 05.07.2017.
 */
public class Equation {

    private List<Molecule> molecules;

    Equation(String molecules, List<String> admixtures) {
        this.molecules = readMolecule(molecules);
        addAdmixtures(admixtures);
        calculateMasses();
    }

    private List<Molecule> readMolecule(String equation) {
        equation = equation.replaceAll(" ", "");
        List<String> leftSide = Arrays.asList(equation.split("=")[0].split("\\+"));
        List<String> rightSide = Arrays.asList(equation.split("=")[1].split("\\+"));
        List<Molecule> molecules = new ArrayList<>();

        molecules.addAll(leftSide.stream().map(Molecule::new).peek(m -> m.setLeftSide(true)).collect(Collectors.toList()));
        molecules.addAll(rightSide.stream().map(Molecule::new).peek(m -> m.setLeftSide(false)).collect(Collectors.toList()));

        return molecules;
    }


    private void addAdmixtures(List<String> admixtures) {
        Map<Molecule, Set<Molecule>> hostsAndAdmixtures = new HashMap<>();
        List<Molecule> newMolecules = new ArrayList<>();

        for (String ad : admixtures) {
            double prc = 0.01 * Float.valueOf(ad.substring(ad.indexOf("(") + 1, ad.indexOf("%")));
            Atom host = new Atom(ad.substring(0, ad.indexOf(":")));
            Atom admixture = new Atom(ad.substring(ad.indexOf(":") + 1, ad.indexOf("(")));

            molecules.stream()
                    .filter(m -> m.isHost() && m.getAtoms().contains(host))
                    .forEach(m -> {
                        Set<Molecule> adms = hostsAndAdmixtures.get(m);
                        if (adms == null) adms = new HashSet<>();
                        Molecule newAdm = m.setupPercentages(host, admixture, prc);
                        adms.add(newAdm);
                        newMolecules.add(newAdm);
                        hostsAndAdmixtures.put(m, adms);
                    });
            molecules.addAll(newMolecules);
            newMolecules.clear();
        }
    }

    private void calculateMasses() {
        double coefficient;
        double summaryMass = 0;
        List<Molecule> knownMolecules = molecules
                .stream()
                .filter(m -> m.getMass() > 0)
                .collect(Collectors.toList());
        double summaryKnownMass = knownMolecules.stream().mapToDouble(Molecule::getMass).sum();
        double summaryMolecularMass = knownMolecules.stream().mapToDouble(Molecule::getSummaryMolecularMass).sum();
        coefficient = summaryKnownMass / summaryMolecularMass;

        for (Molecule molecule : molecules) {
            molecule.setMass(molecule.getSummaryMolecularMass() * coefficient);
            summaryMass += molecule.getMass();
        }

        System.out.println("Summary mass: " + String.format("%.4fg", summaryMass));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Molecule> left = molecules.stream().filter(Molecule::isLeftSide).collect(Collectors.toList());
        List<Molecule> right = molecules.stream().filter(m -> !m.isLeftSide()).collect(Collectors.toList());
        int size = 1;
        for (Molecule m : left) {
            sb.append(m);
            if (size < left.size()) sb.append(" + ");
            else sb.append(" = ");
            size++;
        }
        size = 1;
        for (Molecule m : right) {
            sb.append(m);
            if (size < right.size()) sb.append(" + ");
            size++;
        }

        return sb.toString();
    }
}
