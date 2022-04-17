package main.CapaDomini.Controllers;

import main.CapaDomini.Models.*;
import main.CapaPresentacio.inout;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CtrlDomini {

    private HashMap<Integer, Document> Documents;
    private inout io;

    private static CtrlDomini singletonObject;

    public static CtrlDomini getInstance() throws Exception {
        if (singletonObject == null)
            singletonObject = new CtrlDomini() {
            };
        return singletonObject;
    }

    public CtrlDomini() throws Exception {
        InicialitzarCtrlDomini();
    }

    private void InicialitzarCtrlDomini() throws Exception {
        Documents = new HashMap<>();
        Documents.put(1,new Document("Doc 1"));
        Full nou = new Full(1,"Full 1", 20, 20);
        Documents.get(1).afegir_full(nou);
        io = new inout();
    }

    public ArrayList<String> Mostrar() throws Exception { //El full hauria de retornar una ArrayList i així no haver de col·locar tot aixó al controller
        ArrayList<String> temp = new ArrayList<>();
        Full f = Documents.get(1).get_full("Full 1");
        HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela> Celes = f.getCeles();
        Integer nf = f.getNum_Files();
        Integer nc = f.getNum_Columnes();
        for(int i = 0; i < nf; i++) {
            for(int j = 0; j < nc; j++) {
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(i, j);
                temp.add(Celes.get(id).getContingut());
            }
        }
        return temp;
    }

    public int getNum_Files() {
        Full f = Documents.get(1).get_full("Full 1");
        return f.getNum_Files();
    }

    public int getNum_Columnes() {
        Full f = Documents.get(1).get_full("Full 1");
        return f.getNum_Columnes();
    }

    public void modificarContingutCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) {
        Integer document = Integer.parseInt(doc);
        Full f = Documents.get(document).get_full(full);
        f.Modifica_Cela(id, contingut);
    }

    public Boolean ComprovarTipus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Integer document = Integer.parseInt(doc);
        Full f = Documents.get(document).get_full(full);
        Cela c = f.Consultar_cela(id);
        return c.getType().equals("numeric");
    }

    public String GetTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Integer document = Integer.parseInt(doc);
        Full f = Documents.get(document).get_full(full);
        Cela c = f.Consultar_cela(id);
        return c.getType();
    }

    public void CanviarTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String nou_type) {
        Integer document = Integer.parseInt(doc);
        Full f = Documents.get(document).get_full(full);
        if (nou_type.equals("numeric")) {
            f.Modifica_Tipus_Numeric(id);
        }
    }

    public ArrayList<String> GetDocuments(){
        ArrayList<String> temp = new ArrayList<>();
        for (Map.Entry<Integer, Document> entry : Documents.entrySet()) {
            String s = ( entry.getKey() + ". " + entry.getValue().getNom());
            temp.add(s);
        }
        return temp;
    }

    public ArrayList<String> GetIdDoc(){
        ArrayList<String> temp = new ArrayList<>();
        for (Map.Entry<Integer, Document> entry : Documents.entrySet()) {
            String s = (String.valueOf(entry.getKey()));
            temp.add(s);
        }
        return temp;
    }
}
