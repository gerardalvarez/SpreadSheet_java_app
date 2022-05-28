/**
 * @file CelaRefNum.java
 * @author Miguel Frias
 * @date 28/05/2022
 * @brief Implementació de la classe CelaRefNum
 */

package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;

public class CelaRefNum extends Numero{
    private String contingut;

    public CelaRefNum(AbstractMap.SimpleEntry<Integer, Integer> id, String res,Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus, String contingut) {
        super(id,new BigDecimal(res), arrodonit, num_Decimals, tipus);
        this.contingut= contingut;
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
    }
    public String getContingut() {
        return this.contingut;
    }
    public BigDecimal getResultat(){
        return this.resultat;
    }

}
