import java.util.HashMap;
enum Elements {
    H, He,
    Li, Be, B, C, N, O, F, Ne,
    Na, Mg, Al, Si, P, S, Cl, Ar,
    K, Ca, Sc, Ti, V, Cr,  Mn, Fe, Co, Ni, Cu, Zn, Ga, Ge, As, Se, Br, Kr,
    Rb, Sr, Y, Zr, Nb, Mo, Tc, Ru, Rh, Pd, Ag, Cd, In, Sn, Sb, Te, I, Xe,
    Cs, Ba, La, Hf, Ta, W, Re, Os, Ir, Pt, Au, Hg, Tl, Pb, Bi, Po, At, Rn,
    Fr, Ra, Ac, Rf, Db, Sg, Bh, Hs, Mt, Ds, Rg, Cn, Nh, Fl, Mc, Lv, Ts, Og,
    Ce, Pr, Nd, Pm, Sm, Eu, Gd, Tb, Dy, Ho, Er, Tm, Yb, Lu,
    Th, Pa, U, Np, Pu, Am, Cm, Bk, Cf, Es, Fm, Md, No, Lr;
}
class atomic_mass{
    static HashMap<Elements, String> molarMass = new HashMap<Elements, String>();

    public static float getAtomicMass(String element){
        molarMass.clear();
        molarMass.put(Elements.H,"1.00797");
        molarMass.put(Elements.He,"4.00260");
        molarMass.put(Elements.Li,"6.941");
        molarMass.put(Elements.Be,"9.01218");
        molarMass.put(Elements.B,"10.81");
        molarMass.put(Elements.C,"12.011");
        molarMass.put(Elements.N,"14.0067");
        molarMass.put(Elements.O, "15.9994");
        molarMass.put(Elements.F, "18.998403");
        molarMass.put(Elements.Ne, "20.179");
        molarMass.put(Elements.Na, "22.98977");
        molarMass.put(Elements.Mg, "24.305");
        molarMass.put(Elements.Al, "26.98154");
        molarMass.put(Elements.Si, "28.0855");
        molarMass.put(Elements.P, "30.97376");
        molarMass.put(Elements.S, "32.06");
        molarMass.put(Elements.Cl, "35.453");
        molarMass.put(Elements.K, "39.0983");
        molarMass.put(Elements.Ar, "39.948");
        molarMass.put(Elements.Ca, "40.08");
        molarMass.put(Elements.Sc, "44.9559");
        molarMass.put(Elements.Ti, "47.90");
        molarMass.put(Elements.V, "50.9415");
        molarMass.put(Elements.Cr, "51.996");
        molarMass.put(Elements.Si, "28.0855");
        molarMass.put(Elements.Mn, "54.9380");
        molarMass.put(Elements.Fe, "55.847");
        molarMass.put(Elements.Ni, "58.70");
        molarMass.put(Elements.Co, "58.9332");
        molarMass.put(Elements.Cu, "63.546");
        molarMass.put(Elements.Zn, "65.38");
        molarMass.put(Elements.Ga, "69.72");
        molarMass.put(Elements.Ge, "72.59");
        molarMass.put(Elements.As, "74.9216");
        molarMass.put(Elements.Se, "78.96");
        molarMass.put(Elements.Br, "79.904");
        molarMass.put(Elements.Kr, "83.80");
        molarMass.put(Elements.Rb, "85.4678");
        molarMass.put(Elements.Sr, "87.62");
        molarMass.put(Elements.Y, "88.9059");
        molarMass.put(Elements.Zr, "91.22");
        molarMass.put(Elements.Nb, "92.9064");
        molarMass.put(Elements.Mo, "95.94");
        molarMass.put(Elements.Tc, "98");
        molarMass.put(Elements.Ru, "101.07");
        molarMass.put(Elements.Rh, "102.9055");
        molarMass.put(Elements.Pd, "106.4");
        molarMass.put(Elements.Ag, "107.868");
        molarMass.put(Elements.Cd, "112.41");
        molarMass.put(Elements.In, "114.82");
        molarMass.put(Elements.Sn, "118.69");
        molarMass.put(Elements.Sb, "121.75");
        molarMass.put(Elements.I, "126.9045");
        molarMass.put(Elements.Te, "127.60");
        molarMass.put(Elements.Xe, "131.30");
        molarMass.put(Elements.Cs, "132.9054");
        molarMass.put(Elements.Ba, "137.33");
        molarMass.put(Elements.La, "138.9055");
        molarMass.put(Elements.Ce, "140.12");
        molarMass.put(Elements.Pr, "140.9077");
        molarMass.put(Elements.Nd, "144.24");
        molarMass.put(Elements.Pm, "145");
        molarMass.put(Elements.Sm, "150.4");
        molarMass.put(Elements.Eu, "151.96");
        molarMass.put(Elements.Gd, "157.25");
        molarMass.put(Elements.Tb, "158.9254");
        molarMass.put(Elements.Dy, "162.50");
        molarMass.put(Elements.Ho, "164.9304");
        molarMass.put(Elements.Er, "167.26");
        molarMass.put(Elements.Tm, "168.9342");
        molarMass.put(Elements.Yb, "173.04");
        molarMass.put(Elements.Lu, "174.967");
        molarMass.put(Elements.Hf, "178.49");
        molarMass.put(Elements.Ta, "180.9479");
        molarMass.put(Elements.W, "183.85");
        molarMass.put(Elements.Re, "186.207");
        molarMass.put(Elements.Os, "190.2");
        molarMass.put(Elements.Ir, "192.22");
        molarMass.put(Elements.Pt, "195.09");
        molarMass.put(Elements.Au, "196.9665");
        molarMass.put(Elements.Hg, "200.59");
        molarMass.put(Elements.Tl, "204.37");
        molarMass.put(Elements.Pb, "207.2");
        molarMass.put(Elements.Bi,"208.9804");
        molarMass.put(Elements.Po,"209");
        molarMass.put(Elements.At,"210");
        molarMass.put(Elements.Rn,"222");
        molarMass.put(Elements.Fr,"223");
        molarMass.put(Elements.Ac,"227.0278");
        molarMass.put(Elements.Pa,"231.0359");
        molarMass.put(Elements.Th,"232.0381");
        molarMass.put(Elements.Np,"237.0482");
        molarMass.put(Elements.U,"238.029");
        molarMass.put(Elements.Pu,"242");
        molarMass.put(Elements.Am,"243");
        molarMass.put(Elements.Bk,"247");
        molarMass.put(Elements.Cm,"247");
        molarMass.put(Elements.No,"250");
        molarMass.put(Elements.Cf,"251");
        molarMass.put(Elements.Es,"252");
        molarMass.put(Elements.Hs,"255");
        molarMass.put(Elements.Mt,"256");
        molarMass.put(Elements.Fm,"257");
        molarMass.put(Elements.Md,"258");
        molarMass.put(Elements.Lr,"260");
        molarMass.put(Elements.Rf,"261");
        molarMass.put(Elements.Bh,"262");
        molarMass.put(Elements.Db,"262");
        molarMass.put(Elements.Sg,"263");
        Elements f = Enum.valueOf(Elements.class,element);
        return Float.valueOf(molarMass.get(f));

    }
    public static float getAtomicMassMole(String molecule){
        float total = 0;
        String current = "";
        for(int i = 0; i < molecule.length();i++){

            if(i == 0){
                current += molecule.charAt(i);
                continue;
            }
            if(Character.isUpperCase(molecule.charAt(i))){
                float molar = getAtomicMass(current);
                total += molar;
                current = String.valueOf(molecule.charAt(i));
                continue;
            }
            if(Character.isDigit(molecule.charAt(i))){
                float molar = getAtomicMass(current);
                float vafdas = Float.valueOf(String.valueOf(molecule.charAt(i)));
                total += molar * vafdas;
                break;
            }

            current += molecule.charAt(i);

        }
        return total;
    }
}

