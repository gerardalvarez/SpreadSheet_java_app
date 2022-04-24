package main.CapaDomini.Models;

import java.util.AbstractMap;
import java.util.ArrayList;

public class CelaRefData extends DataCela{
    private String contingut;
    private ArrayList<TextCela> operadors;

    public CelaRefData(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) {
        super(id, contingut);
        this.contingut=contingut;
        Avaluar();
    }

    private void Avaluar(){
        if(this.contingut.charAt(1) == '#'){
            resultat_final=operadors.get(0).getResultatFinal();
        } else {

        }
    }
}
