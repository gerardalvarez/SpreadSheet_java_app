package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;

public class CelaRefNum extends Numero{
    private String contingut;

    public CelaRefNum(AbstractMap.SimpleEntry<Integer, Integer> id, String res,Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus, String contingut) {
        super(id,new BigDecimal(res), arrodonit, num_Decimals, tipus);
        this.contingut= contingut;
        //Avaluar();
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


    /*private void Pearson(){
        //Blocs
        ArrayList<Numero> n1= new ArrayList<>();
        ArrayList<Numero> n2= new ArrayList<>();
        int o= 0;
        int i= 4;
        boolean t= true;
        while (i < contingut.length()){
            if (contingut.charAt(i) == ';') t= false;
            else if (contingut.charAt(i) == '#') {
                if (t) n1.add(operadors.get(o));
                else n2.add(operadors.get(o));
                ++o;
            }
            ++i;
        }
        Bloc_celes bc= new Bloc_celes();
        this.resultat= BigDecimal.valueOf(bc.coeficient_Pearson(n1,n2));
    }*/


}
