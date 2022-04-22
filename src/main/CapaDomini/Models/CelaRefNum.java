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

    public CelaRefNum(AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus, String contingut, ArrayList<Numero> operadors, String operacio) {
        super(id, new BigDecimal(0), arrodonit, num_Decimals, tipus);
        this.contingut= contingut;
        this.operadors= operadors;
        this.operacio= operacio;
        Avaluar();
        super.setResultat(this.resultat);
    }

    private void Avaluar() {
        if (this.operacio.equals("+")) Suma();
        else if (this.operacio.equals("-")) Resta();
        else if (this.operacio.equals("*")) Multiplica();
        else if (this.operacio.equals("/")) Divideix();
        else if (this.operacio.equals("AVG")) Average();
        else if (this.operacio.equals("=")) Copia();
        else if (this.operacio.equals("MED")) Mediana();
        else if (this.operacio.equals("VAR")) Variança();
        else Desviació();
    }

    private void Suma(){
        int o = 0;
        int i= 2;
        BigDecimal res = new BigDecimal(0);
        while (i < contingut.length()){
            if (contingut.substring(i).equals("#")) {
                res.add(this.operadors.get(o).getResultat());
                ++o;
                while ((!contingut.substring(i).equals(",")) && i < contingut.length()) ++i;
            } else if (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                String num= "0";
                while ((!contingut.substring(i).equals(",")) && i < contingut.length()) {
                    num = num + contingut.charAt(i);
                    ++i;
                }
                System.out.println(num);
                System.out.println(res);
                res.add(new BigDecimal("num"));
            }
            ++i;
        }
    }

    private void Resta(){
        int o = 0;
        BigDecimal res = new BigDecimal(0);
        for (int i = 2; i < contingut.length(); ++i) {
            if (contingut.substring(i).equals("#")) {
                res.subtract(this.operadors.get(o).getResultat());
                ++o;
            } else if (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                String num = null;
                while (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                    num = num + contingut.charAt(i);
                    ++i;
                }
                res.subtract(new BigDecimal(num));
            }
        }
    }

    private void Multiplica(){
        int o = 0;
        BigDecimal res = new BigDecimal(1);
        for (int i = 2; i < contingut.length(); ++i) {
            if (contingut.substring(i).equals("#")) {
                res.multiply(this.operadors.get(o).getResultat());
                ++o;
            } else if (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                String num = null;
                while (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                    num = num + contingut.charAt(i);
                    ++i;
                }
                res.multiply(new BigDecimal(num));
            }
        }
    }

    private void Divideix(){
        int o = 0;
        BigDecimal res = new BigDecimal(0);
        boolean t= false;
        for (int i = 2; i < contingut.length(); ++i) {
            if (i==2) t= true;
            if (contingut.substring(i).equals("#")) {
                if (t) res= this.operadors.get(o).getResultat();
                else res.divide(this.operadors.get(o).getResultat());
                ++o;
                t= false;
            } else if (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                String num = null;
                while (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                    num = num + contingut.charAt(i);
                    ++i;
                }
                if (t) res= new BigDecimal(num);
                else res.divide(new BigDecimal(num));
                t= false;
            }
        }
    }

    private void Average(){
        //Blocs
    }
    private void Desviació(){
        //Blocs
    }
    private void Variança() {
        //Blocs
    }
    private void Copia(){
        //Blocs
    }
    private void Mediana(){
        //Blocs
    }

    public BigDecimal getResultatt(){
        return this.resultat;
    }
}
