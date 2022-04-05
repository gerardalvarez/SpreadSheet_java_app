package main.CapaDomini.Models;

import java.math.BigDecimal;

public class Numero extends Cela_Proba{
    private BigDecimal resultat;
    private Boolean es_Decimal;
    private Integer num_Decimals;
    private Tipus_Numero tipus;

    //Constructor
    public Numero(BigDecimal resultat, Boolean es_Decimal, Integer num_Decimals, Tipus_Numero tipus) {
        this.resultat = resultat;
        this.es_Decimal = es_Decimal;
        this.num_Decimals = num_Decimals;
        this.tipus = tipus;
    }

    //Mètodes

    public void incrementar () {
        this.resultat = this.resultat.add(new BigDecimal(1));
    }
    public void decrementar () {
        this.resultat = this.resultat.add(new BigDecimal(-1));
    }

    public void potencia(int exp) {
        this.resultat = this.resultat.pow(exp);
    }

    public void arrel(int exp) {
        this.resultat = this.resultat.pow(1/exp);
    }


    //Setters

    public void setResultat(BigDecimal resultat) {
        this.resultat = resultat;
    }

    public void setEs_Decimal(Boolean es_Decimal) {
        this.es_Decimal = es_Decimal;
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

    public Boolean getEs_Decimal() {
        return es_Decimal;
    }

    public Integer getNum_Decimals() {
        return num_Decimals;
    }

    public Tipus_Numero getTipus() {
        return tipus;
    }
}

