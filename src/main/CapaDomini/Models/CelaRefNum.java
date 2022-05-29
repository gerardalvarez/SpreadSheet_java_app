/**
 * Implementacio de la classe CelaRefNum seguint el nostre UML. Classe que implementaran les Celes Ref de Numero
 * @file CelaRefNum.java
 * @author Gerard Castell
 * @date 2022
 */
package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
/**
 * Classe de CelaRefNum. Classe que extend de la classe Numero.
 * Aquesta esta feta per les referencies ja que guarda a contingut la referencia
 */
public class CelaRefNum extends Numero{
    //VARIABLE
    /**
     * String on guardem la referencia. Exemple: "=F4"
     */
    private String contingut;

    //GETTERS AND SETTERS
    public String getContingut() {
        return this.contingut;
    }
    public BigDecimal getResultat(){
        return this.resultat;
    }


    //CREADORA

    /**
     * Creadora de CelaRefNum que completa els camps de Numero
     * @param id id de la cela
     * @param res resultat que es colocara a Numero
     * @param arrodonit Boolean que diu a Numero si esta arrodonit
     * @param num_Decimals Num de decimals que es veuen a resultat final
     * @param tipus tipus de numero que es la cela
     * @param contingut contingut de la ref
     */
    public CelaRefNum(AbstractMap.SimpleEntry<Integer, Integer> id, String res,Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus, String contingut) {
        super(id,new BigDecimal(res), arrodonit, num_Decimals, tipus);
        this.contingut= contingut;
        //Avaluar();
    }

    /**
     * Creadora completa CelaRefNum. Amb aquesta es completen els camps de la classe abstracta Cela
     * @param dd es dona una CelaRefNum
     * @param id id que li correspon a aquesta Cela
     */
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
}
