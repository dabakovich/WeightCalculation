import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by David on 27.10.2015.
 * CLASS ABOUT:
 */
public class Molecule {
    final public String moleculeName;
    public String moleculeNewName;
    public float moleculeAmount;
    public float moleculeMolMass;
    public float moleculeMass;
    ArrayList<Atom> moleculeAtoms = new ArrayList<Atom>();
    int k = 0;
    boolean end = false;

    // CONSTRUCTORS
    public Molecule(String molecule){
        moleculeName = molecule;
        readMolecule();
    }

    public String toString(){
        if (moleculeAmount != 1) {
            if (moleculeAmount % 1 == 0) moleculeNewName = String.valueOf(Math.round(moleculeAmount)) + "(";
            else
                moleculeNewName = String.format("%.2f", moleculeAmount) + "(";
        } else moleculeNewName = "(";

        for (int i = 0; i < moleculeAtoms.size(); i++) {
            moleculeNewName += moleculeAtoms.get(i).atomName;
            if (moleculeAtoms.get(i).atomAmount != 1) {
                if (moleculeAtoms.get(i).atomAmount % 1 == 0) moleculeNewName = moleculeNewName + Math.round(moleculeAtoms.get(i).atomAmount);
                else moleculeNewName = moleculeNewName + String.format("%.2f", moleculeAtoms.get(i).atomAmount);
            }
            moleculeNewName += "(" + (i + 1) + ")";
            if (i != moleculeAtoms.size() - 1)moleculeNewName += " ";
        }
        return moleculeNewName + ")";
    }
    public String toString(int p){
        if (moleculeAmount != 1) {
            if (moleculeAmount % 1 == 0) moleculeNewName = String.valueOf(Math.round(moleculeAmount)) + "(";
            else
                moleculeNewName = String.format("%.2f", moleculeAmount) + "(";
        } else moleculeNewName = "(";

        for (int i = 0; i < moleculeAtoms.size(); i++) {
            moleculeNewName += moleculeAtoms.get(i).atomName;
            if (moleculeAtoms.get(i).atomAmount != 1) {
                if (moleculeAtoms.get(i).atomAmount % 1 == 0) moleculeNewName = moleculeNewName + Math.round(moleculeAtoms.get(i).atomAmount);
                else moleculeNewName = moleculeNewName + String.format("%.2f", moleculeAtoms.get(i).atomAmount);
            }
            if (i != moleculeAtoms.size() - 1)moleculeNewName += " ";
        }
        if (p == 0) return moleculeNewName + ")";
        else return moleculeNewName + ") (" + p + ")";
    }

    // FUNCTIONS
    public void readMolecule(){
        moleculeAmount = readIndex();
        while (!end){
            moleculeAtoms.add(new Atom(readName(), readIndex()));
        }
    }

    public void calculateMolMasses(){
        for (Atom a : moleculeAtoms){
            moleculeMolMass += a.atomAmount * a.atomMass;
        }
        moleculeMolMass *= moleculeAmount;
    }

    public void calculateMasses(float k){
        moleculeMass = moleculeMolMass * k;
    }

    public String readName(){
        if (((moleculeName.length() - k) > 1) && !(Character.isDigit(moleculeName.charAt(k + 1)))) {
            k += 2;
            if ((moleculeName.length() - k) == 0) end = true;
            return (moleculeName.substring(k - 2, k));
        } else {
            k++;
            if ((moleculeName.length() - k) == 0) end = true;
            return moleculeName.substring(k - 1, k);
        }
    }
    public float readIndex(){
        int temp;
        if (!end) {
            if (Character.isLetter(moleculeName.charAt(k))) return 1;

            if ((moleculeName.length() - k > 1) && !Character.isLetter(moleculeName.charAt(k + 1))) {
                temp = k;
                do {
                    k++;
                } while ((moleculeName.length() - k > 0) && !Character.isLetter(moleculeName.charAt(k)));
                if ((moleculeName.length() - k) == 0) end = true;
                return Float.parseFloat(moleculeName.substring(temp, k));
            } else {
                k++;
                if ((moleculeName.length() - k) == 0) end = true;
                return Float.parseFloat(moleculeName.substring(k - 1, k));
            }
        } else{
            return 1;
        }
    }
}
