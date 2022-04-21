package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;

public class CelaRefNum extends Numero{
    private BigDecimal resultat;
    private ArrayList<Numero> operadors;
    private String operacio;

    public CelaRefNum(AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus, BigDecimal resultat) {
        super(id, null, arrodonit, num_Decimals, tipus);
        super.setResultat(CalculaResult(this.operadors, this.operacio));
    }

    private BigDecimal CalculaResult(ArrayList<Numero> operadors,String operacio){
        if (operacio.equals("+")) return Sumar(operadors);
        else if (operacio.equals("-")) return Restar(operadors);
        else if (operacio.equals("*")) return Multiplicar(operadors);
        else return Dividir(operadors);
    }

    private BigDecimal Sumar(ArrayList<Numero> operadors) {
        BigDecimal res= BigDecimal.valueOf(0);
        for (int i= 0; i < operadors.size(); ++i){
            res.add(operadors.get(i).getResultat());
        }
        return res;
    }

    private BigDecimal Restar(ArrayList<Numero> operadors) {
        BigDecimal res= operadors.get(0).getResultat();
        for (int i= 1; i < operadors.size(); ++i){
            res.subtract(operadors.get(i).getResultat());
        }
        return res;
    }

    private BigDecimal Multiplicar(ArrayList<Numero> operadors) {
        BigDecimal res= operadors.get(0).getResultat();
        for (int i= 1; i < operadors.size(); ++i){
            res.multiply(operadors.get(i).getResultat());
        }
        return res;
    }

    private BigDecimal Dividir(ArrayList<Numero> operadors) {
        BigDecimal res= operadors.get(0).getResultat();
        for (int i= 1; i < operadors.size(); ++i){
            res.divide(operadors.get(i).getResultat());
        }
        return res;
    }
}
