package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;

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

    public CelaRefNum(CelaRefNum dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
        super(id,dd.getResultatFinal());
        this.resultat = dd.getResultat();
        this.setArrodonit(dd.getArrodonit());
        this.setNum_Decimals(dd.getNum_Decimals());
        this.setTipus(dd.getTipus());
        this.colorFons=dd.getColorFons();
        this.colorLletra=dd.getColorLletra();
        this.type=dd.getType();
        this.contingut=dd.getContingut();
        Avaluar();
    }

    public String getContingut() {
        return this.contingut;
    }

    private void Avaluar() {

        /*switch (this.operacio){
            case "+":
                Suma();
                break;

        }*/

        if (this.operacio.equals("SUM")) Suma();
        else if (this.operacio.equals("RES")) Resta();
        else if (this.operacio.equals("PRO")) Multiplica();
        else if (this.operacio.equals("DIV")) Divideix();
        else if (this.operacio.equals("AVG")) Average();
        else if (this.operacio.equals("MED")) Mediana();
        else if (this.operacio.equals("VAR")) Variança();
        else if (this.operacio.equals("MOD")) Moda();
        else if (this.operacio.equals("MAX")) Max();
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
        Bloc_celes bc= new Bloc_celes();
        this.resultat= BigDecimal.valueOf(bc.calculaMitjana(this.operadors));
    }
    private void Desviació(){
        //Blocs
        Bloc_celes bc= new Bloc_celes();
        this.resultat=BigDecimal.valueOf(bc.calculaDesviació(this.operadors));
    }
    private void Variança() {
        //Blocs
        Bloc_celes bc= new Bloc_celes();
        this.resultat= BigDecimal.valueOf(bc.calculaVariança(this.operadors));
    }
    private void Copia(){
        //Blocs
    }
    private void Mediana(){
        //Blocs
        Bloc_celes bc= new Bloc_celes();
        this.resultat= BigDecimal.valueOf(bc.calculaMediana(this.operadors));
    }

    private void Moda(){
        //Blocs
        Bloc_celes bc= new Bloc_celes();
        this.resultat= BigDecimal.valueOf(bc.calculaModa(this.operadors));
    }

    private void Max(){
        //Blocs
        Bloc_celes bc= new Bloc_celes();
        this.resultat= BigDecimal.valueOf(bc.maxim(this.operadors));

    }

    public BigDecimal getResultat(){
        return this.resultat;
    }
}
