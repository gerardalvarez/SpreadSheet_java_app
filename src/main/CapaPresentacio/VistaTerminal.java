package main.CapaPresentacio;

import main.CapaDomini.Models.Document;

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
        io.writeln();
    }

    public void MostrarMenu() throws Exception {
        io.writeln("Aquí pot veure les opcions que te disponibles a seleccionar");
        io.writeln("1. Obrir Documents");
        io.writeln("2. Nou Document");
        io.writeln("3. Eliminar Document");
        //io.writeln("3. Guardar Document");
        io.writeln();
    }

    private void DemanarOpcionsMenu () throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        int s = io.readint();
        switch (s) {
            case 1:
                String doc = ObtenirNomDocument(); //la id del document de moment
                if(Objects.equals(doc, "null")){
                    MostrarMenu();
                    DemanarOpcionsMenu();
                }
                else {
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
        io.writeln("1. Seleccionar Full");
        io.writeln("2. Afegir Full");
        io.writeln("3. Eliminar Full");
        io.writeln();
    }

    public void DemanarOpcionsDocument(String doc) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        int s = io.readint();
        switch (s) {
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
            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsDocument(doc);
        }
    }

    public void MostrarOpcionsFull() throws Exception {
        io.writeln("A continuació pot veure quines opcions pot fer dintre d'un document");
        io.writeln("1. Gestionar Cel·les");
        io.writeln("2. Afegir Fila");
        io.writeln("3. Eliminar Fila");
        io.writeln("4. Afegir Columna");
        io.writeln("5. Eliminar Fila");
        io.writeln("Esborrar Celes");
        io.writeln();
    }

    private void DemanarOpcionsFull(String doc, String full) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        int s = io.readint();
        switch (s) {
            case 1:
                MostrarOpcionsCela();
                AbstractMap.SimpleEntry<Integer, Integer> id = ObtenirIdCela();
                DemanarOpcionsCela(doc, full, id);
                break;

            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsFull(doc, full);
                break;
        }
    }

    public void MostrarOpcionsCela() throws Exception {
        io.writeln("A continuació pot veure quines opcions pot fer sobre una cel·la");
        io.writeln("1. Modificar contingut Ce·la");
        io.writeln("2. Eliminar contingut Cel·la");
        io.writeln("3. Canviar Tipus Cel·la");
        io.writeln("4. Funcions de Numero");
        io.writeln("5. Funcions de Data");
        io.writeln("6. Funcions de Text");
        io.writeln();
    }

    public void DemanarOpcionsCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        io.writeln("Seleccioni una de les opcions, indiqui el número al terminal");
        int s = io.readint();
        switch (s) {
            case 1:
                String contingut = ObtenirContigut();
                Cp.ModificarContingutCela(doc, full, id, contingut);
                io.writeln("Contingut modificat Correctament");
                ImprimirFull();
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 3:
                String type = Cp.GetTipusCela(doc, full, id);
                String nou_type = ObtenirTipus(type);
                Cp.CanviarTipus(doc, full, id, nou_type);
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            case 4:
                Boolean b = Cp.ComprovarTipusCela(doc, full, id);
                if (b) io.writeln("Soc Numero!");
                else io.writeln("ERROR: No soc Numero!");
                MostrarOpcionsFull();
                DemanarOpcionsFull(doc, full);
                break;

            default:
                io.writeln("ERROR: L'opció seleccionada no existeix");
                DemanarOpcionsCela(doc, full, id);
                break;

        }
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

    private AbstractMap.SimpleEntry<Integer, Integer> ObtenirIdCela() throws Exception {
        io.writeln("Indiqui l'identificador de la cel·la sobre la qual vol treballar, introdueixi'ls separats per un espai");
        int f = io.readint();
        int c = io.readint();
        return new AbstractMap.SimpleEntry<>(f - 1,c - 1);
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

    public void ImprimirFull() throws Exception {
        ArrayList<String> full = Cp.MostrarLlista();
        int nf = Cp.GetFiles();
        int nc = Cp.GetColumnes();
        Iterator <String> iter = full.listIterator();
        for (int i = 0; i < nf; i++) {
            for (int j = 0; j < nc; j++) {
                io.write(iter.next());
                io.write(" ");
            }
            io.writeln();
        }
    }

}
