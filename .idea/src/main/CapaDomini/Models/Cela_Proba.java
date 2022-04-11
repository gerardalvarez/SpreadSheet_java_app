package main.CapaDomini.Models;

import java.awt.*;
import java.util.AbstractMap;

class Cela_Proba {
    protected static AbstractMap<Integer, Integer> id;
    protected static String contingut;
    protected static Color color_Fons;
    protected static Color color_Lletra;

    public Cela_Proba(String contingut){
        this.contingut= contingut;
    }

    public AbstractMap<Integer, Integer> getId(){
        return this.id;
    }
    public String getContingut(){
        return this.contingut;
    }
}


