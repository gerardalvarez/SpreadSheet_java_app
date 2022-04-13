package main;

import main.CapaPresentacio.CtrlPresentacio;
import main.CapaPresentacio.inout;

public class Main {
    private static CtrlPresentacio Cp;

    public static void main() throws Exception {
        inout io = new inout();
        String s = null;
        while(true) {
            assert false;
            if (!s.equals("FI")) break;
            s = io.readline();

            if (s.equals("Insertar")) {
                Cp.InsertarNumero();
            }

            else if (s.equals("Mostrar")) {
                Cp.MostrarResultat();
            }
        }
    }
}
