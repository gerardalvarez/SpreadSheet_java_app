package main.CapaPresentacio;

import java.util.*;

public class VistaTerminal {

    private final inout io;
    private CtrlPresentacio Cp;

    public VistaTerminal(CtrlPresentacio cp) throws Exception {
        io = new inout();
        Cp = cp;
        InicialitzaVistaTerminal();
        MostrarMenu();
        DemanarOpcionsMenu();
    }

    public void InicialitzaVistaTerminal() throws Exception {
        io.writeln("Benvingut al Full de Càlcul");
        io.writeln("Per defecte s'ha creat un document amb nom 'Doc 1' el qual té un full de 20x20 amb el nom 'Full 1'");
        io.writeln("ATENCIÓ: per tornar enrere quan et trobis a un menú prem la tecla '0';");
        io.writeln();
    }

    public void MostrarMenu() throws Exception {
        io.writeln("Aquí pot veure les opcions que te disponibles a seleccionar");
        io.writeln("0. Tancar Programa");
        io.writeln("1. Obrir Documents");
        io.writeln("2. Nou Document");
        io.writeln("3. Eliminar Document");
        //io.writeln("3. Guardar Document");
        io.writeln();
    }

    private void DemanarOpcionsMenu () throws Exception {
            io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");

            Integer s = io.readint();
            switch (s) {
                case 1:
                    String doc = ObtenirNomDocument(); //la id del document de moment
                    if (Objects.equals(doc, "null")) {
                        MostrarMenu();
                        DemanarOpcionsMenu();
                    } else {
                        MostrarOpcionsDocument();
                        DemanarOpcionsDocument(doc);
                    }
                    break;
                case 2:
                    String nouDoc = ObtenirNouDocument();
                    CrearNouDoc(nouDoc);
                    io.writeln("Document Afegit amb Exit");
                    MostrarOpcionsDocument();
                    DemanarOpcionsDocument(nouDoc);
                    break;
                case 3:
                    String elimDoc = ObtenirDocumentEliminar();
                    EliminarDoc(elimDoc);
                    io.writeln("Document Eliminat amb Exit");
                    MostrarMenu();
                    DemanarOpcionsMenu();

                default:
                    io.writeln("ERROR: L'opció seleccionada no existeix");

                    DemanarOpcionsMenu();
            }
    }

    public void MostrarOpcionsDocument() throws Exception {
        io.writeln("A continuació pot veure quines opcions pot fer dintre d'un document");
        io.writeln("0. Retrocedir");
        io.writeln("1. Seleccionar Full");
        io.writeln("2. Afegir Full");
        io.writeln("3. Eliminar Full");
        io.writeln("4. Canviar Nom Document");
        io.writeln();
    }

    public void DemanarOpcionsDocument(String doc) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        Integer s = io.readint();
        switch (s) {
            case 0:
                MostrarMenu();
                DemanarOpcionsMenu();
                break;


            case 1:
                String full = ObtenirNomFull(doc);
                if(Objects.equals(full, "null")){
                    MostrarOpcionsDocument();
                    DemanarOpcionsDocument(doc);
                }
                else{
                    MostrarOpcionsFull();
                    DemanarOpcionsFull(doc, full);
                }
                break;

            case 2:
                Integer files = 1, columnes = 1;
                String nouFull = ObtenirNouFull(doc, files, columnes);
                Cp.CrearNouFull(doc, nouFull, files, columnes);
                io.writeln("Full Afegit amb Exit");
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc,nouFull);
                break;

            case 3:
                String elimFull = ObtenirFullEliminar(doc);
                Cp.EliminarFull(doc, elimFull);
                io.writeln("Document Eliminat amb Exit");
                MostrarOpcionsDocument();
                DemanarOpcionsDocument(doc);
                break;

            case 4:
                String nouDoc = ObtenirNameDocument(doc);
                Cp.NouNomDoc(nouDoc, doc);
                io.writeln("Nom del Document Canviat amb Exit");
                MostrarOpcionsDocument();
                DemanarOpcionsDocument(nouDoc);
                break;

            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsDocument(doc);
        }
    }

    public void MostrarOpcionsFull() throws Exception {
        io.writeln("A continuació pot veure quines opcions pot fer dintre d'un Full");
        io.writeln("0. Retrocedir");
        io.writeln("1. Gestionar Cel·les");
        io.writeln("2. Gestionat Bloc de Cel·les");
        io.writeln("3. Afegir Fila");
        io.writeln("4. Afegir Columna");
        io.writeln("5. Eliminar Fila");
        io.writeln("6. Eliminar Columna");
        io.writeln("7. Veure Full");
        io.writeln("8. Esborrar Celes");
        io.writeln("9. Canviar Nom Full");
        io.writeln();
    }

    private void DemanarOpcionsFull(String doc, String full) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        Integer s = io.readint();
        switch (s) {
            case 0:
                MostrarOpcionsDocument();
                DemanarOpcionsDocument(doc);
                break;

            case 1:
                MostrarOpcionsCela();
                AbstractMap.SimpleEntry<Integer, Integer> id = ObtenirIdCela(doc, full);
                DemanarOpcionsCela(doc, full, id);
                break;

            case 2:
                MostrarOpcionsBloc();
                AbstractMap.SimpleEntry<Integer, Integer> id1 = ObtenirIdCela(doc, full);
                AbstractMap.SimpleEntry<Integer, Integer> id2 = ObtenirIdCela(doc, full);
                DemanarOpcionsBloc(doc, full, id1, id2);
                break;

            case 3:
                int files = Cp.GetFiles(doc, full);
                io.writeln("Actualment té " + files + " files");
                Integer f = ObtenirFila();
                Cp.AfegirFila(doc, full, f);
                ImprimirFull(doc, full);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 4:
                int cols = Cp.GetColumnes(doc, full);
                io.writeln("Actualment té " + cols + " columnes");
                Integer c = ObtenirColumna();
                Cp.AfegirCol(doc, full, c);
                ImprimirFull(doc, full);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 5:
                int fila = Cp.GetFiles(doc, full);
                io.writeln("Actualment té " + fila + " files");
                Integer fi = ObtenirFila();
                while(fi < 0 && fi > fila) {
                    io.writeln("Fila no valida");
                    fi = ObtenirFila();
                }
                Cp.EliminarFila(doc, full, fi);
                ImprimirFull(doc, full);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 6:
                int col = Cp.GetFiles(doc, full);
                io.writeln("Actualment té " + col + " columnes");
                Integer co = ObtenirColumna();
                while(co < 0 && co > col) {
                    io.writeln("Columna no valida");
                    co = ObtenirColumna();
                }
                Cp.EliminarCol(doc, full, co);
                ImprimirFull(doc, full);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 7:
                ImprimirFull(doc, full);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 9:
                String nouFull = ObtenirNameFull(doc, full);
                Cp.NouNomFull (doc, full, nouFull);
                io.writeln("Nom del Full Canviat amb Exit");
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc , nouFull);
                break;

            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsFull(doc, full);
                break;
        }
    }


    public void MostrarOpcionsCela() throws Exception {
        io.writeln("A continuació pot veure quines opcions pot fer sobre una cel·la");
        io.writeln("0. Retrocedir");
        io.writeln("1. Modificar contingut Cel·la");
        io.writeln("2. Eliminar contingut Cel·la");
        io.writeln("3. Canviar Tipus Cel·la");
        io.writeln("4. Funcions de Número");
        io.writeln("5. Funcions de Data");
        io.writeln("6. Funcions de Text");
        io.writeln();
    }

    public void DemanarOpcionsCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        Integer s = io.readint();
        Boolean b;
        switch (s) {
            case 0:
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 1:
                String contingut = ObtenirContigut();
                Cp.ModificarContingutCela(doc, full, id, contingut);
                ImprimirFull(doc, full);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 2:
                Cp.EliminarCela(doc, full, id);
                ImprimirFull(doc, full);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;


            case 3:
                String type = Cp.GetTipusCela(doc, full, id);
                String nou_type = ObtenirTipus(type);
                Cp.CanviarTipus(doc, full, id, nou_type);
                ImprimirFull(doc, full);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 4:
                 b = Cp.ComprovarTipusCela(doc, full, id, "numero");
                if (b) {
                    MostrarOpcionsNumero();
                    DemanarOpcionsNumero(doc, full, id);
                }
                else {
                    io.writeln("ERROR: La cel·la seleccionada no és de tipus Número");
                    io.writeln("Si us plau, seleccioni una Cel·la de tipus Número o canviï el tipus de l'actual");
                    io.writeln();
                    MostrarOpcionsCela();
                    DemanarOpcionsCela(doc, full, id);
                }
                break;

            case 5:
                b = Cp.ComprovarTipusCela(doc, full, id, "data");
                if (b) {
                    MostrarOpcionsData();
                    DemanarOpcionsData(doc, full, id);
                }
                else {
                    io.writeln("ERROR: La cel·la seleccionada no és de tipus Data");
                    io.writeln("Si us plau, seleccioni una Cel·la de tipus Data o canviï el tipus de l'actual");
                    io.writeln();
                    MostrarOpcionsCela();
                    DemanarOpcionsCela(doc, full, id);
                }
                break;


            case 6:
                 b = Cp.ComprovarTipusCela(doc, full, id, "text");
                if (b) {
                    MostrarOpcionsText();
                    DemanarOpcionsText(doc, full, id);
                }
                else {
                    io.writeln("ERROR: La cel·la seleccionada no és de tipus Text");
                    io.writeln("Si us plau, seleccioni una Cel·la de tipus Text o canviï el tipus de l'actual");
                    io.writeln();
                    MostrarOpcionsCela();
                    DemanarOpcionsCela(doc, full, id);
                }
                break;

            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsCela(doc, full, id);
                break;

        }
    }

    private void MostrarOpcionsBloc() throws Exception {
        io.writeln("A continuació pot veure quines opcions pot fer sobre una bloc de cel·les");
        io.writeln("0. Retrocedir");
        io.writeln("1. Calcula mitjana");
        io.writeln("2. Calcula mediana");
        io.writeln("3. Calcula moda");
        io.writeln("4. Calcula variança");
        io.writeln("5. Busca el màxim");
        io.writeln("6. Busca el desviació");
        io.writeln("7. Passar tot a majúscules");
        io.writeln("8. Passar tot a minúscules");
        io.writeln("9. Busca i reemplaçar");
        io.writeln("10. Copiar contingut");
        io.writeln("11. Moure contingut");
        io.writeln();
    }

    private void DemanarOpcionsBloc(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        Integer s = io.readint();
        switch (s) {
            case 0:
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 1:
                if(Cp.ComprovaNumeric(doc, full, id1, id2)) {
                    AbstractMap.SimpleEntry<Integer, Integer> idfin = ObtenirIdCela(doc, full);
                    Cp.CalculaMitjana(doc, full, id1, id2, idfin);
                    ImprimirFull(doc, full);
                }
                else {
                    io.writeln("ERROR: Algunes de les cel·les seleccionades no es numero");

                }
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 2:
                if(Cp.ComprovaNumeric(doc, full, id1, id2)) {
                    AbstractMap.SimpleEntry<Integer, Integer> idfin = ObtenirIdCela(doc, full);
                    Cp.CalculaMediana(doc, full, id1, id2, idfin);
                    ImprimirFull(doc, full);
                }
                else {
                    io.writeln("ERROR: Algunes de les cel·les seleccionades no es numero");

                }
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 3:
                if(Cp.ComprovaNumeric(doc, full, id1, id2)) {
                    AbstractMap.SimpleEntry<Integer, Integer> idfin = ObtenirIdCela(doc, full);
                    Cp.CalculaModa(doc, full, id1, id2, idfin);
                    ImprimirFull(doc, full);
                }
                else {
                    io.writeln("ERROR: Algunes de les cel·les seleccionades no es numero");

                }
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 4:
                if(Cp.ComprovaNumeric(doc, full, id1, id2)) {
                    AbstractMap.SimpleEntry<Integer, Integer> idfin = ObtenirIdCela(doc, full);
                    Cp.CalculaVariança(doc, full, id1, id2, idfin);
                    ImprimirFull(doc, full);
                }
                else {
                    io.writeln("ERROR: Algunes de les cel·les seleccionades no es numero");

                }
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;


            case 5:
                if(Cp.ComprovaNumeric(doc, full, id1, id2)) {
                    AbstractMap.SimpleEntry<Integer, Integer> idfin = ObtenirIdCela(doc, full);
                    Cp.BuscaMaxim(doc, full, id1, id2, idfin);
                    ImprimirFull(doc, full);
                }
                else {
                    io.writeln("ERROR: Algunes de les cel·les seleccionades no es numero");

                }
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 6:
                if(Cp.ComprovaNumeric(doc, full, id1, id2)) {
                    AbstractMap.SimpleEntry<Integer, Integer> idfin = ObtenirIdCela(doc, full);
                    Cp.CalculaDesviacio(doc, full, id1, id2, idfin);
                    ImprimirFull(doc, full);
                }
                else {
                    io.writeln("ERROR: Algunes de les cel·les seleccionades no es numero");

                }
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 7:
                if(Cp.ComprovaText(doc, full, id1, id2)) {
                    Cp.AllMayusBloc(doc, full, id1, id2);
                    ImprimirFull(doc, full);
                }
                else {
                    io.writeln("ERROR: Algunes de les cel·les seleccionades no es text");

                }
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 8:
                if(Cp.ComprovaText(doc, full, id1, id2)) {
                    Cp.AllMinusBloc(doc, full, id1, id2);
                    ImprimirFull(doc, full);
                }
                else {
                    io.writeln("ERROR: Algunes de les cel·les seleccionades no es text");

                }
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 9:
                if(Cp.ComprovaText(doc, full, id1, id2)) {
                    String buscar = ObtenirBusca();
                    String remp = ObtenirTextRemp();

                   // Cp.BuscarRemp(doc, full, id1, id2, buscar, remp);
                    ImprimirFull(doc, full);
                }
                else {
                    io.writeln("ERROR: Algunes de les cel·les seleccionades no es text");

                }
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 10:
                AbstractMap.SimpleEntry<Integer, Integer> idfin1 = ObteniridNouBloc(doc, full);
                AbstractMap.SimpleEntry<Integer, Integer> idfin2 = ObteniridNouBloc(doc, full);
                Cp.Copiar(doc, full, id1, id2, idfin1, idfin2);
                ImprimirFull(doc, full);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;


            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsBloc(doc, full, id1, id2);
        }
    }

    private void MostrarOpcionsNumero() throws Exception {
        io.writeln("A continuació pot veure quines opcions pot fer sobre una cel·la de tipus Número");
        io.writeln("0. Retrocedir");
        io.writeln("1. Consultar tipus Cel·la Número");
        io.writeln("2. Canviar tipus Cel·la Número");
        io.writeln("3. Incrementar");
        io.writeln("4. Reduir");
        io.writeln("5. Potència");
        io.writeln("6. Arrel");
        io.writeln("7. Valor Absolut");
        io.writeln("8. Conversió");
        io.writeln("9. Seleccionar numero de decimals a mostrar");
        io.writeln("10. Arrodonir o Truncar");
        io.writeln();
    }

    public void DemanarOpcionsNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        Integer s = io.readint();
        Double exp;
        switch (s) {
            case 1:
                String Tipus = Cp.GetTipusNumero(doc, full, id);
                io.writeln();
                io.write("La Cel·la Número es del tipus: ");
                io.writeln(Tipus);
                io.writeln();
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 2:
                MostrarTipusNumero();
                String type = ObtenirConvertir();
                if (Cp.TipusValid(type)) {
                    Cp.CanviarTipusNumero(doc, full, id, type);
                    MostrarOpcionsNumero();
                    DemanarOpcionsNumero(doc, full, id);
                }
                else {
                    io.writeln("ERROR: El tipus seleccionat no és vàlid");
                    io.writeln("Seleccioni un dels tipus vàlids mostrats");
                    DemanarOpcionsNumero(doc, full, id);
                }

            case 3:
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.CalculaIncrementIReemplaca(doc, full, id, idRemp);
                }
                else {
                    Cp.CalculaIncrement(doc, full, id);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 4:
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.CalculaReduirIReemplaca(doc, full, id, idRemp);
                }
                else {
                    Cp.CalculaReduir(doc, full, id);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 5:
                exp = ObtenirExponent();
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.CalculaPotenciaIReemplaca(doc, full, id, exp, idRemp);
                }
                else {
                    Cp.CalculaPotencia(doc, full, id, exp);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 6:
                exp = ObtenirExponent();
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.CalculaArrelIReemplaca(doc, full, id, exp, idRemp);
                }
                else {
                    Cp.CalculaArrel(doc, full, id, exp);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 7:
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.CalculaValorAbsIReemplaca(doc, full, id, idRemp);
                }
                else {
                    Cp.CalculaValorAbs(doc, full, id);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 8:
                String c = ObtenirConvertir();
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.CalculaConversioIReemplaca(doc, full, id, c, idRemp);
                }
                else {
                    Cp.CalculaConversio(doc, full, id, c);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 9:
                Integer dec = ObtenirDecimals();
                Cp.CanviarDecimals(doc, full, id, dec);
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 10:
                Boolean arrodonir = ObtenirArrodonit();
                Cp.CanviarArrodonit(doc, full, id, arrodonir);
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 0:
                MostrarOpcionsCela();
                DemanarOpcionsCela(doc, full, id);
                break;

            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsNumero(doc, full, id);
                break;

        }
    }

    private void MostrarOpcionsData() throws Exception {
        io.writeln("A continuació pot veure quines opcions pot fer sobre una cel·la de tipus Data");
        io.writeln("0. Retrocedir");
        io.writeln("1. Obtenir dia");
        io.writeln("2. Obtenir mes");
        io.writeln("3. Obtenir any");
        io.writeln("4. Obtenir data completa");
        io.writeln("5. Transformar a Text");
        io.writeln("6. Transformar a Data");
        io.writeln();
    }

    private void DemanarOpcionsData(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        Integer s = io.readint();
        switch (s) {
            case 0:
                MostrarOpcionsCela();
                DemanarOpcionsCela(doc, full, id);
                break;

            case 1:
                String dia = Cp.getDia(doc, full, id);
                io.writeln(dia);
                MostrarOpcionsData();
                DemanarOpcionsData(doc, full, id);
                break;

            case 2:
                String mes = Cp.getMes(doc, full, id);
                io.writeln(mes);
                MostrarOpcionsData();
                DemanarOpcionsData(doc, full, id);
                break;

            case 3:
                String any = Cp.getAny(doc, full, id);
                io.writeln(any);
                MostrarOpcionsData();
                DemanarOpcionsData(doc, full, id);
                break;

            case 4:
                String data = Cp.getDataCompleta(doc, full, id);
                io.writeln(data);
                MostrarOpcionsData();
                DemanarOpcionsData(doc, full, id);
                break;

            case 5:
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.transformaTextIReemplaca(doc, full, id, idRemp);
                }
                else {
                    Cp.transformaText(doc, full, id);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsData();
                DemanarOpcionsData(doc, full, id);
                break;

            case 6:
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.transformaDataIReemplaca(doc, full, id, idRemp);
                }
                else {
                    Cp.transformaData(doc, full, id);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsData();
                DemanarOpcionsData(doc, full, id);
                break;

            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsText(doc, full, id);
                break;
        }
    }

    private void MostrarOpcionsText() throws Exception {
        io.writeln("A continuació pot veure quines opcions pot fer sobre una cel·la de tipus Text");
        io.writeln("0. Retrocedir");
        io.writeln("1. Passar tot a majúscules");
        io.writeln("2. Passar tot a minúscules");
        io.writeln("Per poder fer buscar i reemplaçar ha de seleccionar un bloc de cel·les i sel·leccionar l'opció pertinent");
        io.writeln();
    }

    private void DemanarOpcionsText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        Integer s = io.readint();
        switch (s) {
            case 0:
                MostrarOpcionsCela();
                DemanarOpcionsCela(doc, full, id);
                break;

            case 1:
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.AllMayusIReemplaca(doc, full, id, idRemp);
                }
                else {
                    Cp.AllMayus(doc, full, id);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            case 2:
                if (PreguntarColocarCelaNova()) {
                    AbstractMap.SimpleEntry<Integer, Integer> idRemp = ObtenirIdRemp(doc, full);
                    Cp.AllMinusIReemplaca(doc, full, id, idRemp);
                }
                else {
                    Cp.AllMinus(doc, full, id);
                }
                ImprimirFull(doc, full);
                MostrarOpcionsNumero();
                DemanarOpcionsNumero(doc, full, id);
                break;

            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsText(doc, full, id);
                break;
        }
    }

    private void MostrarTipusNumero() throws Exception {
        io.writeln("A continuació pot veure quins tipus pot ser una cel·la de tipus Número");
        io.writeln("1. numero (predeterminada)");
        io.writeln("2. celsius");
        io.writeln("3. fahrenheit");
        io.writeln("4. kelvin");
        io.writeln("5. km");
        io.writeln("6. m");
        io.writeln("7. cm");
        io.writeln("8. mm");
        io.writeln("9. miles");
        io.writeln("10. yards");
        io.writeln("11. feet");
        io.writeln("12. inches");
        io.writeln("13. graus");
        io.writeln("14. radiants");
        io.writeln();
    }

    private String ObtenirNomDocument() throws Exception {
        ArrayList<String> Docs = Cp.GetDocs();
        if(Docs.size() == 0){
            io.writeln("ERROR: No hi ha Documents Guardats");
            return "null";
        }
        else{
            io.writeln("Indiqui el nom del document sobre el qual vol treballar");
            for(int i = 0; i < Docs.size(); ++i){
                io.writeln(i+1 + ". " + Docs.get(i));
            }
            io.readnext();
            String a = io.readline(); //Introduir la id de moment
            while(!Docs.contains(a)){
                io.writeln("ERROR: El Document seleccionat no existeix");
                io.writeln("Introdueix un identificador valid");
                io.readnext();
                a =  io.readline();
            }
            return a;
        }
    }

    private String ObtenirNouDocument() throws Exception {
        ArrayList<String> Docs = Cp.GetDocs();
        io.writeln("Els noms dels Documents existents son:");
        for(int i = 0; i < Docs.size(); ++i){
            io.write(i+1 + ". " +Docs.get(i) + " ");
            if(i%3 == 0)io.writeln();
        }
        io.writeln("Escriu el nom del nou document:");
        io.readnext();
        String a = io.readline(); //Introduir la id de moment
        while(Docs.contains(a)){
            io.writeln("ERROR: El Document seleccionat ja existeix");
            io.writeln("Introdueix un Nom valid");
            io.readnext();
            a =  io.readline();
        }
        return a;
    }
    private String ObtenirNameDocument(String Doc) throws Exception {
        ArrayList<String> Docs = Cp.GetDocs();
        io.writeln("El nom del Document existent es:");
        io.writeln("1. "+ Doc);
        io.writeln("Escriu el nom del nou document:");

        io.readnext();
        String a = io.readline(); //Introduir la id de moment
        while(Docs.contains(a)){
            io.writeln("ERROR: El Nom ja pertany a un Document Actual");
            io.writeln("Introdueix un Nom valid");
            io.readnext();
            a =  io.readline();
        }
        return a;
    }

    private String ObtenirDocumentEliminar() throws Exception {
        ArrayList<String> Docs = Cp.GetDocs();
        io.writeln("Els noms dels Documents existents son:");
        for(int i = 0; i < Docs.size(); ++i){
            io.write(i+1 + ". " +Docs.get(i) + " ");
            if(i%3 == 0)io.writeln();
        }
        io.writeln("Escriu el nom del document a eliminar:");
        io.readnext();
        String a = io.readline(); //Introduir la id de moment
        while(!Docs.contains(a)){
            io.writeln("ERROR: El Document seleccionat no existeix");
            io.writeln("Introdueix un Nom valid");
            io.readnext();
            a =  io.readline();
        }
        return a;
    }

    public void CrearNouDoc(String nouDoc){
        Cp.crearDoc(nouDoc);
    }

    public void EliminarDoc(String nouDoc){
        Cp.EliminarDoc(nouDoc);
    }

    private String ObtenirNomFull(String doc) throws Exception {
        ArrayList<String> fulls = Cp.GetFulls(doc);
        if(fulls.size() == 0){
            io.writeln("ERROR: No hi ha Fulls al Document");
            return "null";
        }
        else{
            io.writeln("Indiqui el Nom del document sobre el qual vol treballar");
            for(int i = 0; i < fulls.size(); ++i){
                io.writeln((i+1) + ". " + fulls.get(i));
            }
            io.readnext();
            String a = io.readline(); //Introduir la id de moment
            while(!fulls.contains(a)){
                io.writeln("ERROR: El Full seleccionat no existeix");
                io.writeln("Introdueix un identificador valid");
                io.readnext();
                a =  io.readline();
            }
            return a;
        }
    }

    private String ObtenirNouFull(String doc, int files, int Columnes) throws Exception {
        ArrayList<String> fulls = Cp.GetFulls(doc);
        io.writeln("El nom dels Fulls existents son:");
        for(int i = 0; i < fulls.size(); ++i){
            io.write(i+1 + ". " +fulls.get(i) + " ");
            if(i%3 == 0)io.writeln();
        }
        io.writeln("Escriu el nom del nou full:");
        io.readnext();
        String a = io.readline(); //Introduir la id de moment
        while(fulls.contains(a)){
            io.writeln("ERROR: El Full seleccionat ja existeix");
            io.writeln("Introdueix un identificador valid");
            io.readnext();
            a =  io.readline();
        }
        io.writeln("Indica en nombre de files. Recorda: 1-100:");
        io.readnext();
        Integer fil =  io.readint();
        while(fil > 100 || fil <= 0){
            io.writeln("ERROR: Nombre no valid. Prova un altre cop");
            io.readnext();
            fil =  io.readint();
        }
        files = fil;
        io.writeln("Indica en nombre de columnes. Recorda: 1-100:");
        io.readnext();
        Integer col =  io.readint();
        while(col > 100 || col <= 0){
            io.writeln("ERROR: Nombre no valid. Prova un altre cop");
            io.readnext();
            col =  io.readint();
        }
        Columnes = col;
        return a;
    }

    private String ObtenirNameFull(String doc, String full) throws Exception {
        ArrayList<String> fulls = Cp.GetFulls(doc);
        io.writeln("El nom del Full existent es:");
        io.writeln("1. " + full);

        io.writeln("Escriu el nom del nou full:");
        io.readnext();
        String a = io.readline(); //Introduir la id de moment
        while(fulls.contains(a)){
            io.writeln("ERROR: El Full seleccionat ja existeix. Introdueix un nom valid:");
            io.readnext();
            a =  io.readline();
        }
        return a;
    }

    private String ObtenirFullEliminar(String doc) throws Exception {
        ArrayList<String> fulls = Cp.GetFulls(doc);
        io.writeln("El nom dels Fulls existents son:");
        for(int i = 0; i < fulls.size(); ++i){
            io.write(i+1 + ". " +fulls.get(i) + " ");
            if(i%3 == 0)io.writeln();
        }
        io.writeln("Escriu el nom del full a eliminar:");
        io.readnext();
        String a = io.readline(); //Introduir la id de moment
        while(!fulls.contains(a)){
            io.writeln("ERROR: El Full seleccionat no existeix");
            io.writeln("Introdueix un identificador valid");
            io.readnext();
            a =  io.readline();
        }
        return a;
    }

    private AbstractMap.SimpleEntry<Integer, Integer> ObtenirIdCela(String doc, String full) throws Exception {
        io.writeln("Indiqui l'identificador de la cel·la sobre la qual vol treballar, introdueixi'ls separats per un espai");
        int f = io.readint();
        int c = io.readint();
        AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(f - 1,c - 1);
        if(Cp.ComprovarId(doc, full, id)) {
            return id;
        }
        else {
            io.writeln("El id seleccionat no existeix, seleccioni un altre");
            return ObtenirIdCela(doc, full);
        }
    }

    private AbstractMap.SimpleEntry<Integer, Integer> ObtenirIdRemp(String doc, String full) throws Exception {
        io.writeln("Indiqui la cela on vol col·locar el contingut");
        int f = io.readint();
        int c = io.readint();
        AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(f - 1,c - 1);
        if(Cp.ComprovarId(doc, full, id)) {
            if(!Cp.ComprovaCelaNoOcupa(doc, full, id)) {
                return id;
            }
            else {
                io.writeln("La cela ja te contingut escrit en ella");
                if(PreguntarColocarCelaNova()) {
                    return ObtenirIdRemp(doc, full);
                }
                else return id;
            }
        }
        else {
            io.writeln("El id seleccionat no existeix, seleccioni un altre");
            return ObtenirIdRemp(doc, full);
        }
    }

    private String ObtenirContigut() throws Exception {
        io.writeln("Introdueixi el nou contingut");
        return io.readword();
    }

    private String ObtenirTipus(String type) throws Exception {
        io.writeln("Introdueixi el nou tipus per a la cel·la");
        String nou_type = io.readword();
        if(type.equals(nou_type)) {
            io.writeln("ERROR: La cela ja es del tipus seleccionat, seleccioni una de nova");
            ObtenirTipus(type);
        }
        return nou_type;
    }

   public void ImprimirFull(String doc, String full) throws Exception {
        /*
        ArrayList<String> celes = Cp.MostrarLlista(doc, full);
        int nf = Cp.GetFiles(doc, full);
        int nc = Cp.GetColumnes(doc, full);
        Iterator <String> iter = celes.listIterator();
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                if(iter.hasNext())io.write(iter.next());
                io.write(" ");
            }
            io.writeln();
        }
         */
    }


    private boolean PreguntarColocarCelaNova() throws Exception {
        io.writeln("Vol col·locar el resultat a una altra cel·la?");
        io.writeln("Contesti yes o no, en cas negatiu es reemplaçarà el contingut de la cel·la actual amb el nou");
        String s = io.readword();
        if(s.equals("yes")) return true;
        else if(s.equals("no")) return false;
        io.writeln("ERROR: L'opció seleccionada no és vàlida");
        return PreguntarColocarCelaNova();
    }

    private Double ObtenirExponent() throws Exception {
        io.writeln("Escriu l'exponent per fer l'operació seleccionada");
        return io.readdouble();
    }

    private String ObtenirConvertir() throws Exception {
        io.writeln("Escrigui el tipus al que vol convertir, si vol recordar el tipus escrigui 'help' ");
        String s = io.readword();
        if(Cp.TipusValid(s)) return s;
        else if (s.equals("help")) {
            MostrarTipusNumero();
        }
        return ObtenirConvertir();
    }

    private Integer ObtenirDecimals() throws Exception {
        io.writeln("Escrigui el numero de decimals que vol que mostri el numero");
        return io.readint();
    }

    private Boolean ObtenirArrodonit() throws Exception {
        io.writeln("Vol que el numero s'arrodoneixi o es trunqui?");
        io.writeln("Contesti yes o no, per defecte el Número es arrodonit");
        String s = io.readword();
        if(s.equals("yes")) return true;
        else if(s.equals("no")) return false;
        io.writeln("ERROR: L'opció seleccionada no és vàlida");
        return ObtenirArrodonit();
    }

    private String ObtenirBusca() throws Exception {
        io.writeln("Escrigui la paraula que vol buscar");
        return io.readword();
    }

    private String ObtenirTextRemp() throws Exception {
        io.writeln("Escrigui la paraula que vol remplaçar");
        return io.readword();
    }

    private AbstractMap.SimpleEntry<Integer, Integer> ObteniridNouBloc(String doc, String full) throws Exception {
        io.writeln("Escrigui els identificadors del bloc");
        int f = io.readint();
        int c = io.readint();
        AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(f - 1,c - 1);
        if(Cp.ComprovarId(doc, full, id)) {
            return id;
        }
        else {
            io.writeln("El id seleccionat no existeix, seleccioni un altre");
            return ObtenirIdRemp(doc, full);
        }
    }

    private Integer ObtenirFila() throws Exception {
        io.writeln("Escrigui el numero de la nova fila a insertar");
        io.readnext();
        return io.readint();
    }

    private Integer ObtenirColumna() throws Exception {
        io.writeln("Escrigui el numero de la nova columna a insertar");
        return io.readint();
    }

}
