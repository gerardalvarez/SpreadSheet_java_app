package main.CapaDomini.Models;

import java.util.AbstractMap;
import java.util.Objects;

public class TextCela extends Cela{

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
}
