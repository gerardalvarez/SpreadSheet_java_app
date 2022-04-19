package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;

public class CelaRefNum extends Numero{
    private BigDecimal resultat;
    private ArrayList<Numero> operadors;
    private String operacio;

    public CelaRefNum(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut, Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus, BigDecimal resultat) {
        super(id, contingut, arrodonit, num_Decimals, tipus);
        this.resultat = resultat;
    }
}
