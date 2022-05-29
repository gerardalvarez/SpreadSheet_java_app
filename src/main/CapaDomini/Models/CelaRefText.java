/**
 * Implementacio de la classe CelaRefText seguint el nostre UML. Classe que implementaran les Celes Ref de Text
 * @file CelaRefText.java
 * @author Gerard Castell
 * @date 2022
 */

package main.CapaDomini.Models;
import java.util.AbstractMap;

/**
 * Classe de CelaRefText. Classe que extend de la classe TextCela.
 * Aquesta esta feta per les referencies ja que guarda a contingut la referencia
 */
public class CelaRefText extends TextCela{
    //VARIABLE
    /**
     * String on guardem la referencia. Exemple: "=F4"
     */
    private String contingut;

    //GETTERS AND SETTERS
    public String getContingut() {
        return this.contingut;
    }



    //CREADORA

    /**
     * Creadora de CelaRefNum que completa los campos de TextCela
     * @param id id de la Cela
     * @param contingut contingut de la ref
     * @param rf resultat final que es veu al Full de Calcul
     */
    public CelaRefText(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut, String rf) {
        super(id, rf);
        this.contingut=contingut;
    }

    /**
     * Creadora completa CelaRefText. Amb aquesta es completen els camps de la classe abstracta Cela
     * @param dd es dona una CelaRefText
     * @param id id que li correspon a aquesta Cela
     */
    public CelaRefText(CelaRefText dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
        super(id,dd.getResultatFinal());
        this.type=dd.getType();
        this.colorFons=dd.getColorFons();
        this.colorLletra=dd.getColorLletra();
        this.contingut=dd.getContingut();
    }
}
