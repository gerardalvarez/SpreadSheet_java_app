package main.CapaPresentacio;

import main.CapaDomini.Controllers.CtrlDomini;

import java.math.BigDecimal;

public class CtrlPresentacio {

    private final CtrlDomini ControladorDomini;
    private final VistaTerminal Vp;
    private static inout io;



    public CtrlPresentacio() {
        ControladorDomini = new CtrlDomini();
        Vp = new VistaTerminal(this);
        io = new inout();
    }

    public void InsertarNumero() throws Exception {

        String numero = Vp.demanarNumero();
        Integer decimals = Vp.demanarDecimals();
        String tipus = Vp.demanarTipus();
        ControladorDomini.InsertarNumero(numero, decimals, tipus);
        io.writeln();
    }

    public void MostrarResultat() throws Exception {
        Integer numero = Vp.demanarId();
        BigDecimal b = ControladorDomini.getResultat(numero);
        String s = b.toString();
        io.writeln(s);
        io.writeln();
    }

}
