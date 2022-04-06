package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Numero extends Cela_Proba{
    private BigDecimal resultat;
    private Boolean arrodonit;
    private Integer num_Decimals;
    private Tipus_Numero tipus;

    //Constructor
    public Numero(String contingut, Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus) {
        super(contingut);
        this.resultat = new BigDecimal(contingut);
        this.arrodonit = arrodonit;
        this.num_Decimals = num_Decimals;
        this.tipus = tipus;
    }

    //Mètodes

    public void incrementar () {
        this.resultat = this.resultat.add(new BigDecimal(1));
    }
    public void reduir () {
        this.resultat = this.resultat.add(new BigDecimal(-1));
    }

    public void potencia(Integer exp) {
        this.resultat = this.resultat.pow(exp);
    }

    public void setDecimals() {
        if(this.arrodonit) {
            this.resultat = this.resultat.setScale(this.num_Decimals, RoundingMode.HALF_UP);
        }
        else {
            this.resultat = this.resultat.setScale(this.num_Decimals, RoundingMode.DOWN);
        }
    }


    //Setters

    public void setResultat(BigDecimal resultat) {
        this.resultat = resultat;
    }

    public void setarrodonit(Boolean arrodonit) {
        this.arrodonit = arrodonit;
    }

    public void setNum_Decimals(Integer num_Decimals) {
        this.num_Decimals = num_Decimals;
    }

    public void setTipus(Tipus_Numero tipus) {
        this.tipus = tipus;
    }

    //Getters

    public BigDecimal getResultat() {
        return resultat;
    }

    public Boolean getarrodonit() {
        return arrodonit;
    }

    public Integer getNum_Decimals() {
        return num_Decimals;
    }

    public Tipus_Numero getTipus() {
        return tipus;
    }
}

