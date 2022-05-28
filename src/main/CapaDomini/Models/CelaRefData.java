/**
 * @file CelaRefData.java
 * @author Miguel Frias
 * @date 28/05/2022
 * @brief Implementació de la classe CelaRefData
 */

package main.CapaDomini.Models;

import java.util.AbstractMap;
import java.util.ArrayList;

public class CelaRefData extends DataCela{
    private String contingut;

    public CelaRefData(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut, String rf) {
        super(id, rf);
        this.contingut=contingut;

    }
    public CelaRefData(CelaRefData dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
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
