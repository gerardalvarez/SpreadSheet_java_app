package main.CapaDomini.Controllers;

import main.CapaDomini.Models.CelaEnum;
import main.CapaDomini.Models.Numero;
import main.CapaDomini.Models.Tipus_Numero;
import main.CapaPresentacio.inout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class CtrlDomini {

    private HashMap<Integer, Numero> Document;

    private static CtrlDomini singletonObject;

    public static CtrlDomini getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlDomini() {
            };
        return singletonObject;
    }

    public CtrlDomini() {
        InicialitzarCtrlDomini();
    }

    private void InicialitzarCtrlDomini() {
        Document = new HashMap<>();
    }

    public BigDecimal getResultat(Integer i) {
        BigDecimal b = Document.get(i).getResultat();
        Integer Decimals = Document.get(i).getNum_Decimals();
        return b.setScale(Decimals, RoundingMode.HALF_UP);
    }

    public void InsertarNumero(String s, Integer i, String tipus) {
        Document.put(i, new Numero(s,false, 2, Tipus_Numero.valueOf(tipus)));
    }
}
