package main.CapaPresentacio;

import main.CapaDomini.Controllers.CtrlDomini;

import java.math.BigDecimal;

public class CtrlPresentacio {

    private CtrlDomini ControladorDomini;
    private VistaPresentacio Vp;
    private inout io;

    public void InsertarNumero() throws Exception {
        String numero = Vp.demanarNumero();
        Integer decimals = Vp.demanarDecimals();
        String tipus = Vp.demanarTipus();
        ControladorDomini.InsertarNumero(numero, decimals, tipus);
    }

    public void MostrarResultat() throws Exception {
        Integer numero = Vp.demanarId();
        BigDecimal b = ControladorDomini.getResultat(numero);
        String s = b.toString();
        io.write(s);
    }

}
