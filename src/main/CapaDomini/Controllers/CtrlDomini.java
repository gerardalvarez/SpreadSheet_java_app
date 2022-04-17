package main.CapaDomini.Controllers;

import main.CapaDomini.Models.*;
import main.CapaPresentacio.inout;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CtrlDomini {

    private HashMap<String, Document> Documents;
    private inout io;

    private static CtrlDomini singletonObject;

    //CONTROLADOR
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
        Documents.put("Doc 1",new Document("Doc 1"));
        Full nou = new Full("Full 1", 20, 20);
        Documents.get("Doc 1").afegir_full(nou);
        io = new inout();
    }

    //DOCUMENTS
    public ArrayList<String> GetDocuments(){
        ArrayList<String> temp = new ArrayList<>();
        for (Map.Entry<String, Document> entry : Documents.entrySet()) {
            String s = (entry.getKey());
            temp.add(s);
        }
        return temp;
    }

    public void CrearDocument(String doc){
        Documents.put(doc,new Document(doc));
        Full nou = new Full("Full 1", 20, 20);
        Documents.get(doc).afegir_full(nou);
    }

    public void CanviarNomDoc(String nom, String antic){
        Document a = Documents.get(antic);
        a.setNom(nom);
        Documents.remove(antic);
        Documents.put(nom, a);
    }

    public void EliminarDocument(String doc){
        Documents.remove(doc);
    }

    //FULLS
    public void CanviarNomFull(String doc, String antic, String nou){
        Documents.get(doc).get_full(antic).SetNom(nou);
    }
    public ArrayList<String> GetFullDoc(String doc){
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Full> fulls = Documents.get(doc).getFulls();
        for (int i = 0; i < fulls.size(); i++) {
            String s = fulls.get(i).getNom();
            temp.add(s);
        }
        return temp;
    }

    public int getNum_Files(String doc, String full) {
        Full f = Documents.get(doc).get_full(full);
        return f.getNum_Files();
    }

    public int getNum_Columnes(String doc, String full) {
        Full f = Documents.get(doc).get_full(full);
        return f.getNum_Columnes();
    }

    public ArrayList<String> Mostrar(String doc, String full) throws Exception { //El full hauria de retornar una ArrayList i així no haver de col·locar tot aixó al controller
        ArrayList<String> temp = new ArrayList<>();
        Full f = Documents.get(doc).get_full(full);
        HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela> Celes = f.getCeles();
        Integer nf = f.getNum_Files();
        Integer nc = f.getNum_Columnes();
        for(int i = 0; i < nf; i++) {
            for(int j = 0; j < nc; j++) {
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(i, j);
                Cela c = Celes.get(id);
                if (c instanceof Numero) {
                    Numero n = (Numero) c;
                    BigDecimal d = n.getResultat();
                    Integer Num_Dec = n.getNum_Decimals();
                    if(n.getArrodonit()) {
                       d = d.setScale(Num_Dec, RoundingMode.HALF_UP);
                    }
                    else {
                        d = d.setScale(Num_Dec, RoundingMode.DOWN);
                    }
                    temp.add(d.toString());
                }
                else if (c instanceof Text) {
                    temp.add(c.getContingut());
                }
                else {
                    temp.add(c.getContingut());
                }
            }
        }
        return temp;
    }

    public void CrearFull(String doc, String Full,Integer nf, Integer nc){
        Full nou = new Full(Full, nf, nc);
        Documents.get(doc).afegir_full(nou);
    }
    public void eliminarFull(String doc, String full){
        Documents.get(doc).elimina_full(full);
    }
    //CELA
    public void modificarContingutCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) {
        Full f = Documents.get(doc).get_full(full);
        f.Modifica_Cela(id, contingut);
    }

    public Boolean ComprovarTipus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) {
        String t = GetTipusCela(doc, full, id);
        return (t.equals(tipus));
    }

    public String GetTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        if(c instanceof Numero) {
            return "numero";
        }
        else if (c instanceof DataCela) {
            return "data";
        }
        else return "text";
    }

    public void CanviarTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String nou_type) {
        Full f = Documents.get(doc).get_full(full);
        if (nou_type.equals("numero")) {
            f.Modifica_Tipus_Numeric(id);
        }
    }

    public String GetTipusNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Numero n = GetNumero(doc, full, id);
        Tipus_Numero type = n.getTipus();
        return type.name();
    }

    public boolean TipusNumeroValid(String s) {
        for (Tipus_Numero tipus : Tipus_Numero.values()) {
            if (tipus.name().equals(s)) return true;
        }
        return false;
    }

    public void CanviarTipusNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) {
        Numero n = GetNumero(doc, full, id);
        n.setTipus(Tipus_Numero.valueOf(tipus));
    }

    public void CalculaIncrement(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Numero n = GetNumero(doc, full, id);
        n.incrementar();
    }

    public void CalculaIncrementIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaIncrement(doc, full, idRemp);
    }

    public void CalculaReduir(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Numero n = GetNumero(doc, full, id);
        n.reduir();
    }

    public void CalculaReduirIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaReduir(doc, full, idRemp);
    }

    public void CalculaPotencia(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) {
        Numero n = GetNumero(doc, full, id);
        n.potencia(exp);
    }

    public void CalculaPotenciaIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaPotencia(doc, full, idRemp, exp);
    }

    public void CalculaArrel(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) {
        Numero n = GetNumero(doc, full, id);
        n.arrel(exp);
    }

    public void CalculaArrelIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaArrel(doc, full, idRemp, exp);
    }

    public void CalculaValorAbs(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Numero n = GetNumero(doc, full, id);
        n.valor_absolut();
    }

    public void CalculaValorAbsIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaValorAbs(doc, full, idRemp);
    }

    public void CalculaConversio(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c) {
        Numero n = GetNumero(doc, full, id);
        n.conversio(Tipus_Numero.valueOf(c));
        CanviarTipusNumero(doc, full, id, c);

    }

    public void CalculaConversioIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaConversio(doc, full, idRemp, c);
    }

    public void CanviarDecimals(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Integer dec) {
        Numero n = GetNumero(doc, full, id);
        n.setNum_Decimals(dec);
    }

    public void CanviarArrodonit(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonir) {
        Numero n = GetNumero(doc, full, id);
        n.setArrodonit(arrodonir);
    }

    private Numero GetNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        return (Numero) c;
    }

    private void ReemplacaNum(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        Numero n = GetNumero(doc, full, id);
        String result = n.getResultat().toString();
        modificarContingutCela(doc, full, idRemp, result);
        if (!ComprovarTipus(doc, full, idRemp, "numero")) {
            CanviarTipusCela(doc, full, idRemp, "numero");
        }
    }
}
