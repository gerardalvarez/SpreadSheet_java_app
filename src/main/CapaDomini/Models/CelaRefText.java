package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;

public class CelaRefText extends TextCela{
    private String contingut;
    private ArrayList<TextCela> operadors;


    public CelaRefText(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut, ArrayList<TextCela> operadors) {
        super(id, contingut);
        this.contingut=contingut;
        Avaluar();
    }
    public CelaRefText(CelaRefText dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
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
        if (this.contingut.substring(1,3).equals("min")){
            Bloc_celes bc= new Bloc_celes();
            bc.remplaçar_minuscules(operadors);
        }
        if(this.contingut.substring(1).equals("#")){
            resultat_final=operadors.get(0).getResultatFinal();
        } else {
            Bloc_celes bc= new Bloc_celes();
            bc.remplaçar_majuscules(operadors);
        }
    }
}
