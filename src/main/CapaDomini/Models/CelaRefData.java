package main.CapaDomini.Models;

import java.util.AbstractMap;

/**
 * Classe de CelaRefData. Classe que extend de la classe DataCela.
 * Aquesta esta feta per les referencies ja que guarda a contingut la referencia
 * @author Gerard Castell
 */
public class CelaRefData extends DataCela{
    //VARIABLES
    /**
     * String on guardem la referencia. Exemple: "=F4"
     */
    private String contingut;

    //GETTERS AND SETTERS
    public String getContingut() {return this.contingut;}


    //CREADORA
    /**
     * Creadora Simple. Es guarda a Cela i Data Cela la informacio necesaria
     * @param id id de la cela que la identifica
     * @param contingut contingut referencia que es guarda
     * @param rf resultat final que es mostra
     */
    public CelaRefData(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut, String rf) {
        super(id, rf);
        this.contingut=contingut;

    }

    /**
     * Creadora completa CelaRef. Amb aquesta es completen els camps de la classe abstracta Cela
     * @param dd es dona una CelaRefData
     * @param id id que li correspon a aquesta Cela
     */
    public CelaRefData(CelaRefData dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
        super(id,dd.getResultatFinal());
        this.type=dd.getType();
        this.colorFons=dd.getColorFons();
        this.colorLletra=dd.getColorLletra();
        this.contingut=dd.getContingut();
    }


}
