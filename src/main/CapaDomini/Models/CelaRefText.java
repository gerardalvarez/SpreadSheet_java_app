package main.CapaDomini.Models;
import java.util.AbstractMap;


public class CelaRefText extends TextCela{
    private String contingut;


    public CelaRefText(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut, String rf) {
        super(id, rf);
        this.contingut=contingut;
    }
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
