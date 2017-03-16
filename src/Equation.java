import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by David on 28.10.2015.
 * CLASS ABOUT:
 */
public class Equation {
    static Scanner scan = new Scanner(System.in);
    float tempMoleculeMass = 1;
    int admixtureCount = 0;
    public ArrayList<Molecule> molecules = new ArrayList<Molecule>();
    public int k;
    public String equation;
    public boolean end = false;
    public int iEqual, cMolecule = 0;

    // SAMPLES
    static String testEquation1 = "MgO+Ga2O3+Eu2O3=MgGa2Eu2O4"; //"MgO+0.96Ga2O3+0.04Eu2O3=MgGa1.92Eu0.08O4";
    static String testEquation2 = "ZnO+Ga2O3+Eu2O3=ZnGa2Eu2O4";
    static String testEquation3 = "0.96MgO+0.04ZnO+Ga2O3=Mg0.96Zn0.04Ga2O4";
    static String testEquation4 = "0.96MgO+0.04ZnO+0.98Ga2O3+0.02Eu2O3=Mg0.96Zn0.04Ga1.96Eu0.04O4";
    static String testEquation5 = "2.85Gd2O3+5Ga2O3+0.05Eu2O3=2Gd2.85Ga5Eu0.05O12";

    public Equation(){
        System.out.print("Enter an equation, please (or enter demoX for demo): ");
        String s = scan.nextLine();
        if (s.equals("demo1")) equation = testEquation1;
        else if (s.equals("demo2")) equation = testEquation2;
        else if (s.equals("demo3")) equation = testEquation3;
        else if (s.equals("demo4")) equation = testEquation4;
        else if (s.equals("demo5")) equation = testEquation5;
        else equation = s;
        // IANA E?IOAAI? ?A?IAEIE

        readEquation();
    }

    public String toString() {
        String newEquation = "";
        int i = 1;
        for (Molecule m : molecules) {
            newEquation += m.toString(i);
            if (((i - 1) < iEqual) || ((i - 1) > iEqual && (i) < cMolecule)) newEquation += " + ";
            else if ((i - 1) == iEqual) newEquation += " = ";
            i++;
        }
        return newEquation;
    }

    void readEquation() {
        do {
            molecules.add(new Molecule(readMolecule()));
            k++;
            cMolecule++;
        } while (!end);
        //  AII?OEE
        System.out.println("How much admixtures is in equation?");
        if (scan.hasNextInt()){
            admixtureCount = scan.nextInt();
        } else System.out.println("You entered not number. Count will equal 0.");
        if (admixtureCount > 0) fillAdmixture(admixtureCount);
        calculateMasses();
    }

    String readMolecule() {
        int temp = k;

        if (equation.indexOf("+", k) != -1 || equation.indexOf("=", k) != -1) {
            do {
                k++;
            } while (!(equation.charAt(k) == '+') && !(equation.charAt(k) == '='));
            if (equation.charAt(k) == '=') iEqual = cMolecule;
        } else {
            end = true;
            k = equation.length();
        }

        return equation.substring(temp, k);
    }
    void fillAdmixture(int count){
        float admixturePercent = 100;
        int temp1 = 0, temp2 = 0, temp3 = 0;
        System.out.println(this.toString());
        for (int i = 1; i < count + 1; i++) {
            System.out.println("Percent of " + i + " admixture:");
            if (scan.hasNextFloat()) admixturePercent = scan.nextFloat();
            else System.out.println("You entered not number. Weight will equal 100.");
            admixturePercent /= 100;

            System.out.println("Number of " + i + " admixture:");
            if (scan.hasNextInt()) temp1 = scan.nextInt();
            else System.out.println("You entered not number. Count will equal 0.");

            System.out.println("What substance belong " + i + " admixture?");
            if (scan.hasNextInt()) temp2 = scan.nextInt();
            else System.out.println("You entered not number. Count will equal 0.");
            molecules.get(temp1 - 1).moleculeAmount *= admixturePercent;
            molecules.get(temp2 - 1).moleculeAmount *= (1 - admixturePercent);

            System.out.println("Number of ultimate substance: ");
            if (scan.hasNextInt()) temp3 = scan.nextInt();
            else System.out.println("You entered not number. Count will equal 0.");
            System.out.println(molecules.get(temp3 - 1).toString());

            System.out.println("Number of " + i + " admixture in molecule:");
            if (scan.hasNextInt()) temp1 = scan.nextInt();
            else System.out.println("You entered not number. Count will equal 0.");

            System.out.println("What atom belong " + i + " admixture in molecule?");
            if (scan.hasNextInt()) temp2 = scan.nextInt();
            else System.out.println("You entered not number. Count will equal 0.");

            molecules.get(temp3 - 1).moleculeAtoms.get(temp1 - 1).atomAmount *= admixturePercent;
            molecules.get(temp3 - 1).moleculeAtoms.get(temp2 - 1).atomAmount *= (1 - admixturePercent);
        }
    }
    void calculateMasses(){
        int temp1 = 0;
        System.out.print("Enter weight, please: ");
        if (scan.hasNextFloat()){
            tempMoleculeMass = scan.nextFloat();
        } else System.out.println("You entered not number. Weight will equal 1.");
        System.out.println(toString());
        System.out.print("Enter number of molecule with known weight: ");
        if (scan.hasNextInt()){
            temp1 = scan.nextInt();
        } else System.out.println("You entered not number. Number will equal 0.");
        molecules.get(temp1 - 1).calculateMolMasses();
        molecules.get(temp1 - 1).moleculeMass = tempMoleculeMass;
        float k = tempMoleculeMass / molecules.get(temp1 - 1).moleculeMolMass;
        for (int i = 0; i < molecules.size(); i++) {
            if (i == (temp1 - 1)) continue;
            molecules.get(i).calculateMolMasses();
            molecules.get(i).calculateMasses(k);
            System.out.println(molecules.get(i).toString(i + 1) + " : m = " + molecules.get(i).moleculeMass);
        }
        /*molecules.get(molecules.size() - 1).moleculeMolMass *= molecules.get(molecules.size() - 1).moleculeAmount;
        float molOutMass = molecules.get(molecules.size() - 1).moleculeMass = tempMoleculeMass;
        for (int i = 0; i < molecules.size() - 1; i++) {
            molecules.get(i).moleculeMolMass *= molecules.get(i).moleculeAmount;
            molecules.get(i).moleculeMass = (molecules.get(i).moleculeMolMass * molOutMass) / molecules.get(molecules.size() - 1).moleculeMolMass;
            System.out.println(molecules.get(i).toString(i + 1) + " : m = " + molecules.get(i).moleculeMass);
        }*/
    }
}