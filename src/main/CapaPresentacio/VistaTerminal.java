package main.CapaPresentacio;

public class VistaTerminal {

    private final inout io;
    private CtrlPresentacio Cp;

    public VistaTerminal(CtrlPresentacio cp) {
        io = new inout();
        Cp = cp;
    }

    public String demanarNumero() throws Exception {
        io.writeln("Escrigui el valor del numero");
        return io.readword();
    }

    public Integer demanarDecimals() throws Exception {
        io.writeln("Escrigui l'identificador que vol donar al numero");
        return io.readint();
    }

    public String demanarTipus() throws Exception {
        io.writeln("Escrigui el tipus de numero");
        return io.readword();
    }

    public Integer demanarId() throws Exception {
        io.writeln("Escrigui l'identificador a mostrar");
        return io.readint();
    }
}
