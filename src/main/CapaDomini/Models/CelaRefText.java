package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;

public class CelaRefText extends TextCela{
    private String contingut;
    private ArrayList<TextCela> operadors;


    public CelaRefText(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut, ArrayList<TextCela> operadors) {
        super();
        this.contingut=contingut;
        Avaluar();
    }
}
