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
    }




    private void Avaluar() {

        /*switch (this.operacio){
            case "+":
                Suma();
                break;

        }*/

        if (this.operacio.equals("+")) Suma();
        else if (this.operacio.equals("-")) Resta();
        else if (this.operacio.equals("*")) Multiplica();
        else if (this.operacio.equals("/")) Divideix();
        else if (this.operacio.equals("AVG")) Average();
        else if (this.operacio.equals("=")) Copia();
        else if (this.operacio.equals("MED")) Mediana();
        else if (this.operacio.equals("VAR")) Variança();
        else Desviació();
        super.setResultat(this.resultat);
    }

    private void Suma(){
        int o = 0;
        int i= 2;
        BigDecimal res = new BigDecimal(0);
        while (i < contingut.length()){
            if (contingut.charAt(i) == '#') {
                res= res.add(this.operadors.get(o).getResultat());
                ++o;
                while (i < contingut.length() && (contingut.charAt(i) != ','))++i;
            }
            else if (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                String num= "0";
                while (i < contingut.length() && (contingut.charAt(i) != ',')) {
                    num = num + contingut.charAt(i);
                    ++i;
                }
                res= res.add(new BigDecimal(num));
            }
            ++i;
        }
        this.resultat= res;
    }

    private void Resta(){
        int o = 0;
        int i= 2;
        BigDecimal res = new BigDecimal(0);
        boolean t= false;
        while (i < contingut.length()){
            if (i==2) t= true;
            if (contingut.charAt(i) == '#') {
                if (t) {
                    res= this.operadors.get(o).getResultat();
                    t= false;
                }
                else res= res.subtract(this.operadors.get(o).getResultat());
                ++o;
                while (i < contingut.length() && (contingut.charAt(i) != ',')) ++i;
            }
            else if (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                String num= "0";
                while (i < contingut.length() && (contingut.charAt(i) != ',')) {
                    num = num + contingut.charAt(i);
                    ++i;
                }
                if (t) {
                    res = new BigDecimal(num);
                    t = false;
                }
                else res= res.subtract(new BigDecimal(num));
            }
            ++i;
        }
        this.resultat= res;
    }

    private void Multiplica(){
        int o = 0;
        int i= 2;
        BigDecimal res = new BigDecimal(1);
        while (i < contingut.length()){
            if (contingut.charAt(i) == '#') {
                res= res.multiply(this.operadors.get(o).getResultat());
                ++o;
                while (i < contingut.length() && (contingut.charAt(i) != ','))++i;
            }
            else if (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                String num= "0";
                while (i < contingut.length() && (contingut.charAt(i) != ',')) {
                    num = num + contingut.charAt(i);
                    ++i;
                }
                res= res.multiply(new BigDecimal(num));
            }
            ++i;
        }
        this.resultat= res;
    }

    private void Divideix(){
        int o = 0;
        int i= 2;
        BigDecimal res = new BigDecimal(0);
        boolean t= false;
        while (i < contingut.length()){
            if (i==2) t= true;
            if (contingut.charAt(i) == '#') {
                if (t) {
                    res= this.operadors.get(o).getResultat();
                    t= false;
                }
                else res= res.divide(this.operadors.get(o).getResultat());
                ++o;
                while (i < contingut.length() && (contingut.charAt(i) != ',')) ++i;
            }
            else if (contingut.charAt(i) >= '0' && contingut.charAt(i) <= '9') {
                String num= "0";
                while (i < contingut.length() && (contingut.charAt(i) != ',')) {
                    num = num + contingut.charAt(i);
                    ++i;
                }
                if (t) {
                    res = new BigDecimal(num);
                    t = false;
                }
                else res= res.divide(new BigDecimal(num));
            }
            ++i;
        }
        this.resultat= res;
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
