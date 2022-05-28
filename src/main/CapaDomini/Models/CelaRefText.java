/**
 * @file CelaRefText.java
 * @author Miguel Frias
 * @date 28/05/2022
 * @brief Implementació de la classe CelaRefText
 */

package main.CapaDomini.Models;
import java.util.AbstractMap;


public class CelaRefText extends TextCela{
    private String contingut;

    /**
     * Funció que crea una cel·la de tipus referència de tipus Text a partir del seu "id", "contingut" i referència "rf"
     * @param id
     * @param contingut
     * @param rf
     */
    public CelaRefText(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut, String rf) {
        super(id, rf);
        this.contingut=contingut;
    }

    /**
     * Funció que crea una cel·la de tipus referència de tipus Text a partir del seu "id" i de la seva cel·la pare
     * @param dd
     * @param id
     */
    public CelaRefText(CelaRefText dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
        super(id,dd.getResultatFinal());
        this.type=dd.getType();
        this.colorFons=dd.getColorFons();
        this.colorLletra=dd.getColorLletra();
        this.contingut=dd.getContingut();
        //Avaluar();
    }

   public String getContingut() {
        return this.contingut;
    }

}
