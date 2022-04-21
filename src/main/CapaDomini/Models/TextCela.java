package main.CapaDomini.Models;

import java.util.AbstractMap;
import java.util.Objects;

public class TextCela extends Cela{

    //CONSTRUCTORA
    public TextCela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat){
        super(id, resultat);
    }

    //FUNCIONS PUBLIQUES
    public Boolean buscarElement(String element){
        return resultat_final.contains(element);
    }

    public void remplacarElement(String element, String change){
        int index = resultat_final.indexOf(element);
        if(index != -1)
        {
            resultat_final = resultat_final.substring(0,index) + change + resultat_final.substring(index+element.length());
        }
    }

    public void AllMayus(){
        resultat_final =  resultat_final.toUpperCase();
    }

    public void AllMinus(){
        resultat_final =  resultat_final.toLowerCase();
    }
}
