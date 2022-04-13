package main.CapaPresentacio;

public class VistaPresentacio {

    protected inout io = new inout();

    public String demanarNumero() throws Exception {
        io.write("Escrigui el valor del numero");
        return io.readword();
    }

    public Integer demanarDecimals() throws Exception {
        io.write("Escrigui la quantitat de decimals a mostrar");
        return io.readint();
    }

    public String demanarTipus() throws Exception {
        io.write("Escrigui el tipus de numero");
        return io.readword();
    }

    public Integer demanarId() throws Exception {
        io.write("Escrigui l'identificador a mostrar");
        return io.readint();
    }
}
