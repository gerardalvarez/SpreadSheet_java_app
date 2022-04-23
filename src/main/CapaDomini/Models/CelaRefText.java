package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;

public class CelaRefText extends TextCela{
    private String resultat;
    private ArrayList<TextCela> operadors;
    private String operacio;


    public CelaRefText(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) {
        super(id, resultat);
    }
}
