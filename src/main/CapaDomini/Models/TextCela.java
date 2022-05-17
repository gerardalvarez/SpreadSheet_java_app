package main.CapaDomini.Models;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;

public class TextCela extends Cela implements Cloneable{

    //CONSTRUCTORA
    public TextCela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat){
        super(id, resultat);
    }
    public TextCela(TextCela dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
        super(id,dd.getResultatFinal());
        this.type=dd.getType();
        this.colorFons=dd.getColorFons();
        this.colorLletra=dd.getColorLletra();
    }

    public TextCela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat, Color cf, Color cl, CelaEnum dt, String t, ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> obs){
        super(id, resultat, cf, cl ,dt, t, obs);
        this.type= t;
        this.colorFons= cf;
        this.colorLletra= cl;
    }

    //FUNCIONS PUBLIQUES
    public Boolean buscarElement(String element){
        return resultat_final.contains(element);
    }

    public void remplacarElement(String element, String change){
       resultat_final= resultat_final.replaceAll(element,change);
    }

    public void AllMayus(){
        resultat_final =  resultat_final.toUpperCase();
    }

    public void AllMinus(){
        resultat_final =  resultat_final.toLowerCase();
    }

    public Object clone() {
        return new TextCela(this.id, this.resultat_final, this.colorFons, this.colorFons, this.designedType, this.type, this.observadors);
    }
}
