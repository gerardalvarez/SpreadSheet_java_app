package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;

public class CelaRefNum extends Numero{
    private BigDecimal resultat;

    public CelaRefNum(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut, Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus, BigDecimal resultat) {
        super(id, contingut, arrodonit, num_Decimals, tipus);
        this.resultat = resultat;
    }

    public BigDecimal getResultat() {
        return resultat;
    }

    public void setResultat(BigDecimal resultat) {
        this.resultat = resultat;
    }

    public void setTipus(Tipus_Numero tipus) {
        this.tipus = tipus;
    }


}
