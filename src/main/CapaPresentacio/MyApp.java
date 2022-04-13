package main.CapaPresentacio;

import main.CapaPresentacio.CtrlPresentacio;
import main.CapaPresentacio.inout;

public class MyApp {

    public static void main(String[] args) throws Exception {
        CtrlPresentacio cp = new CtrlPresentacio();
        inout io = new inout();
        while(true) {
            io.writeln("Escrigui Insertar o Mostrar");
            String s = io.readline();

            if (s.equals("Insertar")) {
                cp.InsertarNumero();
            }

            else if (s.equals("Mostrar")) {
                cp.MostrarResultat();
            }
        }
    }
}
