package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Scanner;

public class CelaRefNum extends Numero{
    private BigDecimal resultat;
    private ArrayList<Numero> operadors;
    private String operacio;
    private String contingut;

    public CelaRefNum(AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus, String contingut, ArrayList<Numero> operadors) {
        super(id, null, arrodonit, num_Decimals, tipus);
        this.contingut= contingut;
        this.operadors= operadors;
        Avaluar(contingut);
        super.setResultat(this.resultat);
    }
/*
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

    private BigDecimal Dividir(ArrayList<Numero> operadors) {
        BigDecimal res= operadors.get(0).getResultat();
        for (int i= 1; i < operadors.size(); ++i){
            res.divide(operadors.get(i).getResultat());
        }
        return res;
    }
 */
    private void Avaluar(String contingut){
        //SCANNER
        /*
        BigDecimal res= new BigDecimal(0);
        Scanner sc= new Scanner(contingut);
        sc.next();
        while(sc.hasNext()){
            if (sc.next().equals("#")){
                int idfila, idcolum;
                idfila= sc.nextInt();
                sc.next();
                idcolum= sc.nextInt();
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(idfila,idcolum);
                for ()
            }
        }
         */
        int o= 0;
        BigDecimal res= new BigDecimal(0);
        for (int i= 1; i < contingut.length(); ++i){
            if (contingut.substring(i).equals("#")){
                res= this.operadors.get(o).getResultat();
                ++o;
            }
            else if (isNumerical(contingut.substring(i)))
        }
}
