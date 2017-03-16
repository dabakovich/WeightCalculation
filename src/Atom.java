/**
 * Created by David on 28.10.2015.
 * CLASS ABOUT:
 */
public class Atom {
    public String atomName;
    public float atomMass;
    public float atomAmount;

    public Atom(String name, float amount){
        atomName = name;
        atomAmount = amount;
        for (Elements e : Elements.atoms) {
            if (name.equals(e.getName())){
                atomMass = e.getMass();
                break;
            }
        }
    }
}
