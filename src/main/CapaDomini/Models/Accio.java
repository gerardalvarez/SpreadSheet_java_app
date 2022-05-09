package main.CapaDomini.Models;

import java.util.ArrayList;

public class Accio {
    String accio;
    ArrayList<Cela> celes;

    public Accio (String ac, ArrayList<Cela> ce){
        accio= ac;
        celes= ce;
    }

    public String get_Accio(){
        return accio;
    }

    public ArrayList<Cela> get_CelesAc(){
        return celes;
    }
}

