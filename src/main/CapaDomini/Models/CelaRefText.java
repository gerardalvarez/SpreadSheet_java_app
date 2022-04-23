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

    private void Avaluar(){
        if (this.contingut.substring(1,3).equals("min")){
            Bloc_celes bc= new Bloc_celes();
            bc.remplaçar_minuscules(operadors);
        }
        else {
            Bloc_celes bc= new Bloc_celes();
            bc.remplaçar_majuscules(operadors);
        }
    }
}
