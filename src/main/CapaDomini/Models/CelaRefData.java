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
    public CelaRefData(CelaRefData dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
        super(id,dd.getResultatFinal());
        this.type=dd.getType();
        this.colorFons=dd.getColorFons();
        this.colorLletra=dd.getColorLletra();
        this.contingut=dd.getContingut();
        Avaluar();
    }

    private String getContingut() {
        return this.contingut;
    }

    private void Avaluar(){
        if(this.contingut.substring(1).equals("#")){
            resultat_final=operadors.get(0).getResultatFinal();
        } else {

        }
    }
}
