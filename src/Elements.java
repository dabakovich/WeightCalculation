/**
 * Created by David on 28.10.2015.
 * CLASS ABOUT:
 */
public class Elements {
    private String name;
    private float mass;

    public Elements(String atomName, float atomMass){
        name = atomName;
        mass = atomMass;
    }
    public String getName(){
        return name;
    }
    public float getMass(){
        return mass;
    }

    static Elements atoms[] = new Elements[]{
            new Elements("Zero", 0),
            new Elements("H", 2f),
            new Elements("He", 4f),
            new Elements("Li", 6.941f),
            new Elements("Be", 9.012f),
            new Elements("B", 10.811f),
            new Elements("C", 12.011f),
            new Elements("N", 14.0067f),
            new Elements("O", 15.999f),
            new Elements("F", 18.998f),
            new Elements("Ne", 20.1797f),
            new Elements("Na", 22.99f),
            new Elements("Mg", 24.305f),
            new Elements("Al", 26.981f),
            new Elements("Si", 28.086f),
            new Elements("P", 30.973f),
            new Elements("S", 32.06f),
            new Elements("Cl", 35.453f),
            new Elements("Ar", 39.948f),
            new Elements("K", 39.098f),
            new Elements("Ca", 40.08f),
            new Elements("Sc", 44.956f),
            new Elements("Ti", 47.90f),
            new Elements("V", 50.941f),
            new Elements("Cr", 51.996f),
            new Elements("Mn", 54.938f),
            new Elements("Fe", 55.845f),
            new Elements("Co", 58.933f),
            new Elements("Ni", 58.6934f),
            new Elements("Cu", 63.546f),
            new Elements("Zn", 65.38f),
            new Elements("Ga", 69.72f),
            new Elements("Ge", 72.59f),
            new Elements("As", 74.922f),
            new Elements("Se", 78.96f),
            new Elements("Br", 79.904f),
            new Elements("Kr", 83.798f),
            new Elements("Rb", 85.468f),
            new Elements("Sr", 87.62f),
            new Elements("Y", 88.906f),
            new Elements("Zr", 91.22f),
            new Elements("Nb", 92.906f),
            new Elements("Mo", 95.94f),
            new Elements("Tc", 98.9063f),
            new Elements("Ru", 101.07f),
            new Elements("Rh", 102.9055f),
            new Elements("Pd", 106.42f),
            new Elements("Ag", 107.868f),
            new Elements("Cd", 112.40f),
            new Elements("In", 114.82f),
            new Elements("Sn", 118.69f),
            new Elements("Sb", 121.75f),
            new Elements("Te", 127.60f),
            new Elements("I", 126.9045f),
            new Elements("Xe", 131.293f),
            new Elements("Cs", 132.905f),
            new Elements("Ba", 137.34f),
            new Elements("La", 138.905f),
            new Elements("Ce", 140.12f),
            new Elements("Pr", 140.908f),
            new Elements("Nd", 144.24f),
            new Elements("Pm", 145f),
            new Elements("Sm", 150.4f),
            new Elements("Eu", 151.96f),
            new Elements("Gd", 157.25f),
            new Elements("Tb", 158.925f),
            new Elements("Dy", 162.5f),
            new Elements("Ho", 164.93f),
            new Elements("Er", 167.26f),
            new Elements("Tm", 168.93f),
            new Elements("Yb", 173.04f),
            new Elements("Lu", 174.97f),
            new Elements("Hf", 178.49f),
            new Elements("Ta", 180.9479f),
            new Elements("W", 183.85f),
            new Elements("Re", 186.207f),
            new Elements("Os", 190.23f),
            new Elements("Ir", 192.217f),
            new Elements("Pt", 195.09f),
            new Elements("Au", 196.967f),
            new Elements("Hg", 200.59f),
            new Elements("Tl", 204.37f),
            new Elements("Pb", 207.2f),
            new Elements("Bi", 208.98f),
    };
}
