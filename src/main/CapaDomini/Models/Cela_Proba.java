package main.CapaDomini.Models;

import java.awt.*;
import java.util.AbstractMap;

class Cela_Proba {
    protected static AbstractMap.SimpleEntry<Integer, Integer> id;
    protected static String contingut;
    protected static Color color_Fons;
    protected static Color color_Lletra;

    public Cela_Proba(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut){
        this.id= id;
        this.contingut= contingut;
    }

    public AbstractMap.SimpleEntry<Integer, Integer> getId(){
        return this.id;
    }

    public String getContingut(){
        return this.contingut;
    }
    public void setId(AbstractMap.SimpleEntry<Integer, Integer> idc){
        this.id= idc;
    }

}


