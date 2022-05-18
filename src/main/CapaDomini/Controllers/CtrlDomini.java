package main.CapaDomini.Controllers;

import main.CapaDades.DataParser;
import main.CapaDomini.Models.*;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.XYChart;

import java.util.*;


public class CtrlDomini {

    private HashMap<String, Document> Documents;

    private DataParser dp;

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
        Full nou = new Full("Full 1", 25, 25);
        Documents.get("Doc 1").afegir_full(nou);
        dp = new DataParser();
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

    public HashMap<String, Document> gg(){
        return Documents;
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

    public String[][] Mostrar(String doc, String full) throws Exception { //El full hauria de retornar una ArrayList i així no haver de col·locar tot aixó al controller
        ArrayList<String> temp = new ArrayList<>();
        Full f = Documents.get(doc).get_full(full);
        return f.Mostrar();
    }

    public void CrearFull(String doc, String Full,Integer nf, Integer nc){
        Full nou = new Full(Full, nf, nc);
        Documents.get(doc).afegir_full(nou);
    }
    public void eliminarFull(String doc, String full){
        Documents.get(doc).elimina_full(full);
    }

    //CELA
    public void modificarContingutCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) throws Exception {

        Full f = Documents.get(doc).get_full(full);
        String a = PublicFuntions.analiza(resultat,f.getNum_Files(),f.getNum_Columnes());
        ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> l=new ArrayList<>();
        if (f.Consultar_cela(id) instanceof CelaRefNum){
            l=PublicFuntions.analizaops(((CelaRefNum) f.Consultar_cela(id)).getContingut(),f.getNum_Files(),f.getNum_Columnes());
            f.borraref(id,l);
        } else if (f.Consultar_cela(id) instanceof CelaRefText){
            l=PublicFuntions.analizaops(((CelaRefText) f.Consultar_cela(id)).getContingut(),f.getNum_Files(),f.getNum_Columnes());
            f.borraref(id,l);
        }else if (f.Consultar_cela(id) instanceof CelaRefData){
            l=PublicFuntions.analizaops(((CelaRefData) f.Consultar_cela(id)).getContingut(),f.getNum_Files(),f.getNum_Columnes());
            f.borraref(id,l);
        }
        if (!resultat.equals("")){

            switch (a){
                case "text":
                    f.Modifica_Cela(id,resultat);
                    break;
                case "numeric":
                    f.Modifica_Cela(id,resultat);
                    break;
                case "data":
                    f.Modifica_Cela(id,resultat);
                    break;
                case "REFNUM":
                    l=PublicFuntions.analizaops(resultat,f.getNum_Files(),f.getNum_Columnes());
                    f.opera(id,l,resultat);
                    break;
                case "REFTEXT":
                    l=PublicFuntions.analizaops(resultat,f.getNum_Files(),f.getNum_Columnes());
                    f.opera(id,l,resultat);
                    break;
                case "ref a otra celda":
                    l=PublicFuntions.analizaops(resultat,f.getNum_Files(),f.getNum_Columnes());
                    f.opera(id,l,resultat);
                    break;
                case "referencia pero #ERROR":
                    f.Modifica_Cela(id,"#ERROR");
                    break;
            }
        }

        CheckObs(doc, full, id);
    }

    public void CheckObs(String doc, String full , AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> obs = f.getCeles().get(id).getObservadors();
        if(obs.size()!=0){
            for(int i = 0; i < obs.size(); i++){
                if(f.getCeles().get(obs.get(i)) instanceof CelaRefData){
                    CelaRefData c =  (CelaRefData) f.getCeles().get(obs.get(i));
                    modificarContingutCela(doc,full, obs.get(i), c.getContingut());
                }
                else  if(f.getCeles().get(obs.get(i)) instanceof CelaRefText){
                    CelaRefText c =  (CelaRefText) f.getCeles().get(obs.get(i));
                    modificarContingutCela(doc,full, obs.get(i), c.getContingut());
                }
                else {
                    System.out.println(obs.get(i).getKey()+" "+obs.get(i).getValue());
                    CelaRefNum c =  (CelaRefNum) f.getCeles().get(obs.get(i));
                    modificarContingutCela(doc,full,obs.get(i), c.getContingut());
                }
            }
        }
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

    public void CanviarTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String nou_type) throws CloneNotSupportedException {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("canviartipuscela", celes);
        f.Afegir_Accio(a);
        if (nou_type.equals("numero")) {
            f.Modifica_Tipus_Numeric(id);
        }
        else if (nou_type.equals("text")) {
            f.Modifica_Tipus_Text(id);
        }
        else if (nou_type.equals("data")){
            f.Modifica_Tipus_Data(id);
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

    public void CanviarTipusNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) throws CloneNotSupportedException {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("canviartipusnum", celes);
        f.Afegir_Accio(a);
        Numero n = GetNumero(doc, full, id);
        n.setTipus(Tipus_Numero.valueOf(tipus));
    }

    public int CalculaIncrement(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("calculaincrement", celes);
        f.Afegir_Accio(a);
        Boolean comp = ComprovarTipus(doc, full, id, "numero");
        if (comp) {
            Numero n = GetNumero(doc, full, id);
            n.incrementar();
            CheckObs(doc, full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaIncrementIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        int codi = CalculaIncrement(doc, full, idRemp);
        return codi;
    }

    public int CalculaReduir(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("calculareduir", celes);
        f.Afegir_Accio(a);
        Boolean comp = ComprovarTipus(doc, full, id, "numero");
        if (comp) {
            Numero n = GetNumero(doc, full, id);
            n.reduir();
            CheckObs(doc, full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaReduirIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        int codi = CalculaReduir(doc, full, idRemp);
        return codi;
    }

    public int CalculaPotencia(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("calculapotencia", celes);
        f.Afegir_Accio(a);
        Boolean comp = ComprovarTipus(doc, full, id, "numero");
        if (comp) {
            Numero n = GetNumero(doc, full, id);
            n.potencia(exp);
            CheckObs(doc, full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaPotenciaIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        int codi = CalculaPotencia(doc, full, idRemp, exp);
        return codi;
    }

    public int CalculaArrel(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("calculaarrel", celes);
        f.Afegir_Accio(a);
        Boolean comp = ComprovarTipus(doc, full, id, "numero");
        if (comp) {
            Numero n = GetNumero(doc, full, id);
            n.arrel(exp);
            CheckObs(doc, full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaArrelIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        int codi = CalculaArrel(doc, full, idRemp, exp);
        return codi;
    }

    public int CalculaValorAbs(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("calculavalorabs", celes);
        f.Afegir_Accio(a);
        Boolean comp = ComprovarTipus(doc, full, id, "numero");
        if (comp) {
            Numero n = GetNumero(doc, full, id);
            n.valor_absolut();
            CheckObs(doc, full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaValorAbsIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        int codi = CalculaValorAbs(doc, full, idRemp);
        return codi;
    }

    public void CalculaConversio(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("calculaconversio", celes);
        f.Afegir_Accio(a);
        Numero n = GetNumero(doc, full, id);
        n.conversio(Tipus_Numero.valueOf(c));
        CanviarTipusNumero(doc, full, id, c);
        CheckObs(doc, full, id);

    }

    public void CalculaConversioIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaConversio(doc, full, idRemp, c);
    }

    public void CanviarDecimals(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Integer dec) throws CloneNotSupportedException {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("canviardecimal", celes);
        f.Afegir_Accio(a);
        Numero n = GetNumero(doc, full, id);
        n.setNum_Decimals(dec);
    }

    public void CanviarArrodonit(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonir) throws CloneNotSupportedException {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("canviararrodonit", celes);
        f.Afegir_Accio(a);
        Numero n = GetNumero(doc, full, id);
        n.setArrodonit(arrodonir);
    }

    private Numero GetNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        return (Numero) c;
    }

    private void ReemplacaNum(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Numero n = GetNumero(doc, full, id);
        String result = n.getResultat().toString();
        modificarContingutCela(doc, full, idRemp, result);
        if (!ComprovarTipus(doc, full, idRemp, "numero")) {
            CanviarTipusCela(doc, full, idRemp, "numero");
        }
    }

    //Operacions Data

    public String getDia(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(doc, full, id);
        return d.getDia();
    }

    public String getMes(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(doc, full, id);
        return d.getMes();
    }

    public String getAny(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(doc, full, id);
        return d.getAny();
    }
    public String getWeekday(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(doc, full, id);
        return d.getWeekDay();
    }
    public String getDataCompleta(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        DataCela d = GetData(doc, full, id);
        return d.getWeekDay() + " " + d.getDia() + " " + d.getMes() + " " + d.getAny();
    }

    public Boolean transformaText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        try {
            celes.add((Cela) f.Consultar_cela(id).clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        Accio a= new Accio("transformatext", celes);
        f.Afegir_Accio(a);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return false;
        DataCela d = GetData(doc, full, id);
        d.changeToText();
        return true;
    }

    public void transformaTextIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaData(doc, full, id, idRemp);
        transformaText(doc, full, idRemp);
    }

    public boolean transformaData(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        try {
            celes.add((Cela) f.Consultar_cela(id).clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        Accio a= new Accio("transformadata", celes);
        f.Afegir_Accio(a);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return false;
        DataCela d = GetData(doc, full, id);
        d.changeToDate();
        return true;
    }

    public void transformaDataIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaData(doc, full, id, idRemp);
        transformaData(doc, full, idRemp);
    }

    private DataCela GetData(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        return (DataCela) c;
    }

    private void ReemplacaData(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        DataCela t = GetData(doc, full, id);
        String result = t.getResultatFinal();
        modificarContingutCela(doc, full, idRemp, result);
        if (!ComprovarTipus(doc, full, idRemp, "data")) {
            CanviarTipusCela(doc, full, idRemp, "data");
        }
    }

    //Operacions text

    public void AllMayus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("mayus", celes);
        f.Afegir_Accio(a);
        TextCela t = GetText(doc, full, id);
        t.AllMayus();
    }

    public void AllMayusIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaText(doc, full, id, idRemp);
        AllMayus(doc, full, idRemp);
    }

    public void AllMinus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("minus", celes);
        f.Afegir_Accio(a);
        TextCela t = GetText(doc, full, id);
        t.AllMinus();
    }

    public void AllMinusIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaText(doc, full, id, idRemp);
        AllMinus(doc, full, idRemp);
    }

    private TextCela GetText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        return (TextCela) c;
    }

    private void ReemplacaText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        TextCela t = GetText(doc, full, id);
        String result = t.getResultatFinal();
        modificarContingutCela(doc, full, idRemp, result);
        if (!ComprovarTipus(doc, full, idRemp, "text")) {
            CanviarTipusCela(doc, full, idRemp, "text");
        }
    }

    //Operacions de bloc
    public void CalculaMitjana(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        celes.add((Cela) f.getCeles().get(idfin).clone());
        Accio a= new Accio("calcularmitjana", celes);
        f.Afegir_Accio(a);
        Bloc_celes bc = new Bloc_celes();
        double d = bc.calculaMitjana(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }

    private ArrayList<Numero> ObtenirBlocNumeric(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        Full f = Documents.get(doc).get_full(full);
        return f.getBlocNumero(id1, id2);
    }

    public Boolean ComprovaNumeric(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        System.out.println(doc +" " + full + " " + id1 + " " +id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            String t = GetTipusCela(doc, full, id);
            if(!t.equals("numero")) {
                return false;
            }
        }
        return true;
    }

    public void CalculaMediana(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        celes.add((Cela) f.getCeles().get(idfin).clone());
        Accio a= new Accio("calcularmediana", celes);
        f.Afegir_Accio(a);
        Bloc_celes bc = new Bloc_celes();
        double d = bc.calculaMediana(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }

    public void CalculaModa(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        celes.add((Cela) f.getCeles().get(idfin).clone());
        Accio a= new Accio("calcularmoda", celes);
        f.Afegir_Accio(a);
        Bloc_celes bc = new Bloc_celes();
        double d = bc.calculaModa(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }

    public void CalculaVariança(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        celes.add((Cela) f.getCeles().get(idfin).clone());
        Accio a= new Accio("calcularvariança", celes);
        f.Afegir_Accio(a);
        Bloc_celes bc = new Bloc_celes();
        double d = bc.calculaVariança(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }

    public void BuscaMaxim(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        celes.add((Cela) f.getCeles().get(idfin).clone());
        Accio a= new Accio("buscamaxim", celes);
        f.Afegir_Accio(a);
        Bloc_celes bc = new Bloc_celes();
        double d = bc.maxim(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }


    public void CalculaDesviacio(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        celes.add((Cela) f.getCeles().get(idfin).clone());
        Accio a= new Accio("calculardesviació", celes);
        f.Afegir_Accio(a);
        Bloc_celes bc = new Bloc_celes();
        double d = bc.calculaDesviació(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }

    public boolean ComprovaText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            String t = GetTipusCela(doc, full, id);
            if(!t.equals("text")) {
                return false;
            }
        }
        return true;
    }

    public void AllMayusBloc(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) throws Exception {
        ArrayList<TextCela> bloc = ObtenirBlocText(doc, full, id1, id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        Accio a= new Accio("mayusb", celes);
        f.Afegir_Accio(a);
        Bloc_celes bc = new Bloc_celes();
        bc.remplaçar_majuscules(bloc);
    }

    private ArrayList<TextCela> ObtenirBlocText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        Full f = Documents.get(doc).get_full(full);
        return f.getBlocText(id1, id2);
    }

    public void AllMinusBloc(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) throws Exception {
        ArrayList<TextCela> bloc = ObtenirBlocText(doc, full, id1, id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        Accio a= new Accio("minusb", celes);
        f.Afegir_Accio(a);
        Bloc_celes bc = new Bloc_celes();
        bc.remplaçar_minuscules(bloc);
    }

    public void BuscaRemp(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, String buscar, String remp) throws Exception {
        ArrayList<TextCela> bloc = ObtenirBlocText(doc, full, id1, id2);
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        Accio a= new Accio("buscaremp", celes);
        f.Afegir_Accio(a);
        Bloc_celes bc = new Bloc_celes();
        bc.buscar_y_remplazar(bloc, buscar, remp);
    }

    public boolean ComprovaCelaNoOcupa(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        if(c.getResultatFinal().equals("")) return false;
        else return true;
    }


    public void copiar(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> idsfin = f.GetIdCeles(idfin1, idfin2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        for (int i= 0; i < idsfin.size(); ++i){
            celes.add((Cela) f.getCeles().get(idsfin.get(i)).clone());
        }
        Accio a= new Accio("copiar", celes);
        f.Afegir_Accio(a);
        Cela [][] mat1 = GetMatriu(doc, full, id1, id2);
       Cela [][] mat2 = GetMatriu(doc, full, idfin1, idfin2);
       Bloc_celes bc = new Bloc_celes();
       bc.copiar_contingut(mat1, mat2);
    }

    private Cela[][] GetMatriu(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {

        Full fu = Documents.get(doc).get_full(full);
        ArrayList<Cela> C= fu.getBlocCeles(id1,id2);
        Integer nf = id2.getKey() - id1.getKey();
        Integer nc = id2.getValue() - id1.getValue();
        Cela [][] mat = fu.getBlocEnMatriu(C,nf,nc);

        /*
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = fu.GetIdCeles(id1, id2);
        Integer nf = id2.getKey() - id1.getKey();
        Integer nc = id2.getValue() - id1.getValue();
        Cela [][] mat = new Cela[nf + 1][nc + 1];
        System.out.println(mat.length);
        System.out.println(mat[0].length);
        int f = 0;
        int c = 0;

        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            mat[f][c] = fu.Consultar_cela(id);
            if(c < nc) c++;
            else {
                c = 0;
                f++;
            }
        }*/

        return mat;
    }



    public boolean ComprovarId(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        return f.ExisteixId(id);
    }

    public void EliminarCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<Cela> celes= new ArrayList<>();
        celes.add((Cela) f.Consultar_cela(id).clone());
        Accio a= new Accio("eliminarcela", celes);
        f.Afegir_Accio(a);
        f.Modifica_Cela(id, "");
    }

    public void AfegirFila(String doc, String full, Integer fila) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        f.Afegir_Fila(fila);
        int numcol= f.getNum_Columnes();
        AbstractMap.SimpleEntry<Integer,Integer> id1= new AbstractMap.SimpleEntry<>(fila,0);
        AbstractMap.SimpleEntry<Integer,Integer> id2= new AbstractMap.SimpleEntry<>(fila,numcol-1);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        Accio a= new Accio("afegirfila", celes);
        f.Afegir_Accio(a);
    }

    public void AfegirCol(String doc, String full, Integer c) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        f.Afegir_Columna(c);
        int numfiles= f.getNum_Files();
        AbstractMap.SimpleEntry<Integer,Integer> id1= new AbstractMap.SimpleEntry<>(0,c);
        AbstractMap.SimpleEntry<Integer,Integer> id2= new AbstractMap.SimpleEntry<>(numfiles-1,c);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        Accio a= new Accio("afegircol", celes);
        f.Afegir_Accio(a);
    }

    public void EliminarFila(String doc, String full, Integer fi) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        int numcol= f.getNum_Columnes();
        AbstractMap.SimpleEntry<Integer,Integer> id1= new AbstractMap.SimpleEntry<>(fi,0);
        AbstractMap.SimpleEntry<Integer,Integer> id2= new AbstractMap.SimpleEntry<>(fi,numcol-1);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        Accio a= new Accio("eliminarfila", celes);
        f.Afegir_Accio(a);
        f.Eliminar_Fila(fi);
    }

    public void EliminarCol(String doc, String full, Integer co) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        int numfiles= f.getNum_Files();
        AbstractMap.SimpleEntry<Integer,Integer> id1= new AbstractMap.SimpleEntry<>(0,co);
        AbstractMap.SimpleEntry<Integer,Integer> id2= new AbstractMap.SimpleEntry<>(numfiles-1,co);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<Cela> celes= new ArrayList<>();
        for (int i= 0; i < ids.size(); ++i){
            celes.add((Cela) f.getCeles().get(ids.get(i)).clone());
        }
        Accio a= new Accio("eliminarcol", celes);
        f.Afegir_Accio(a);
        f.Eliminar_Columna(co);
    }
    public void Undo(String doc, String full) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        f.Undo();
    }

    public String ValorTotal(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        return f.ValorTotal(id);
    }

    public void guardarDocument() throws Exception {
        Document d = Documents.get("Doc 1");
        dp.guarda(d);
    }

    public void obrirDocument() throws Exception {
        Document nou = dp.carrega("Doc 1");
        Documents.replace("Doc 1", nou);
    }

    public XYChart LinearChart(String doc, String full, Integer Col1, Integer filI1, Integer filF1,Integer Col2, Integer filI2, Integer filF2) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        if(!NumericColumn(doc,f,Col1, filI1,filF1)
                || !NumericColumn(doc,f,Col2,filI2, filF2))return null;
        return Bloc_celes.linearChart(f.getColNumero(Col1, filI1,filF1),f.getColNumero(Col2,filI2, filF2));
    }
    public PieChart PieChart(String doc, String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        System.out.println("aaaaaaaaaaaaaaaaaas");
        if(!NumericColumn(doc,f,Col2,filI2, filF2))return null;
        return Bloc_celes.PieChart(f.getColText(Col1, filI1,filF1),f.getColNumero(Col2,filI2, filF2));
    }
    public CategoryChart Histograma(String doc, String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        if(!NumericColumn(doc,f,Col2,filI2, filF2))return null;
        return Bloc_celes.HistoChart(f.getColText(Col1, filI1,filF1),f.getColNumero(Col2,filI2, filF2));
    }

    Boolean NumericColumn(String doc,Full f,Integer col, Integer fIni, Integer fFi) throws Exception {
        System.out.println("aa1aaa");
        for(int i = fIni; i <= fFi; i++){
            System.out.println(f.getCeles().get(new AbstractMap.SimpleEntry<>(i,col)).getResultatFinal()+" "+ f.getCeles().get(new AbstractMap.SimpleEntry<>(i,col)).getType());
            if(!Objects.equals(f.getCeles().get(new AbstractMap.SimpleEntry<>(i,col)).getType(), "numeric")) return false;
        }
        System.out.println("suuuu");
        System.out.println("Valid Column");
        return true;

    }
}
