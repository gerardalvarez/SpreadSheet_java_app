package main.CapaDomini.Controllers;

import main.CapaDomini.Models.*;
import main.CapaPresentacio.inout;
import org.w3c.dom.Text;

import java.awt.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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
        String a = PublicFuntions.calculaTipus(resultat);
        if(Objects.equals(a, "numeric")){
           f.ModificaCelaNum(id, resultat);
            System.out.println( f.getCeles().get(id).getType() + " " + f.getCeles().get(id).getResultatFinal());
        }
        else if(Objects.equals(a, "date")){
            f.ModificaCelaDate(id,resultat);
        }
        else if(PublicFuntions.esRef(resultat,f.getNum_Files(),f.getNum_Columnes())){
            AbstractMap.SimpleEntry<Integer, Integer> pare = PublicFuntions.getNumIdRef(resultat);
            String pareType = f.getCeles().get(pare).getType();
            System.out.println(pareType + " " + f.getCeles().get(pare).getResultatFinal());
            f.ModificaCelaRef(id,resultat,pareType,pare);

        }
        else {
            f.ModificaCelaText(id,resultat);
        }
        CheckObs(doc, full, id);
    }

    public void CheckObs(String doc, String full , AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Full f = Documents.get(doc).get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> obs = f.getCeles().get(id).getObservadors();
        if(obs != null){
            for(int i = 0; i < obs.size(); i++){
                if(Objects.equals(f.getCeles().get(obs.get(i)).getType(), "date")){
                    CelaRefData c =  (CelaRefData) f.getCeles().get(obs.get(i));
                    modificarContingutCela(doc,full, obs.get(i), c.getContingut());
                }
                else  if(Objects.equals(f.getCeles().get(obs.get(i)).getType(), "text")){
                    CelaRefText c =  (CelaRefText) f.getCeles().get(obs.get(i));
                    modificarContingutCela(doc,full, obs.get(i), c.getContingut());
                }
                else {
                    CelaRefNum c =  (CelaRefNum) f.getCeles().get(obs.get(i));
                    modificarContingutCela(doc,full, obs.get(i), c.getContingut());
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

    public void CanviarTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String nou_type) {
        Full f = Documents.get(doc).get_full(full);
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

    public void CanviarTipusNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) {
        Numero n = GetNumero(doc, full, id);
        n.setTipus(Tipus_Numero.valueOf(tipus));
    }

    public void CalculaIncrement(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Numero n = GetNumero(doc, full, id);
        n.incrementar();
        CheckObs(doc, full, id);
    }

    public void CalculaIncrementIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaIncrement(doc, full, idRemp);
    }

    public void CalculaReduir(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Numero n = GetNumero(doc, full, id);
        n.reduir();
        CheckObs(doc, full, id);
    }

    public void CalculaReduirIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaReduir(doc, full, idRemp);
    }

    public void CalculaPotencia(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        Numero n = GetNumero(doc, full, id);
        n.potencia(exp);
        CheckObs(doc, full, id);
    }

    public void CalculaPotenciaIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaPotencia(doc, full, idRemp, exp);
    }

    public void CalculaArrel(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        Numero n = GetNumero(doc, full, id);
        n.arrel(exp);
        CheckObs(doc, full, id);
    }

    public void CalculaArrelIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaArrel(doc, full, idRemp, exp);
    }

    public void CalculaValorAbs(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Numero n = GetNumero(doc, full, id);
        n.valor_absolut();
        CheckObs(doc, full, id);
    }

    public void CalculaValorAbsIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(doc, full, id, idRemp);
        CalculaValorAbs(doc, full, idRemp);
    }

    public void CalculaConversio(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c) throws Exception {
        Numero n = GetNumero(doc, full, id);
        n.conversio(Tipus_Numero.valueOf(c));
        CanviarTipusNumero(doc, full, id, c);
        CheckObs(doc, full, id);

    }

    public void CalculaConversioIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
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

    private void ReemplacaNum(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Numero n = GetNumero(doc, full, id);
        String result = n.getResultat().toString();
        System.out.println(result);
        System.out.println(idRemp);
        modificarContingutCela(doc, full, idRemp, result);
        if (!ComprovarTipus(doc, full, idRemp, "numero")) {
            CanviarTipusCela(doc, full, idRemp, "numero");
        }
    }

    //Operacions Data

    public String getDia(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        DataCela d = GetData(doc, full, id);
        return d.getWeekDay() + " " + d.getDia();
    }

    public String getMes(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        DataCela d = GetData(doc, full, id);
        return d.getMes();
    }

    public String getAny(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        DataCela d = GetData(doc, full, id);
        return d.getAny();
    }

    public String getDataCompleta(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        DataCela d = GetData(doc, full, id);
        return d.getWeekDay() + " " + d.getDia() + " " + d.getMes() + " " + d.getAny();
    }

    public void transformaText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        DataCela d = GetData(doc, full, id);
        d.changeToText();
    }

    public void transformaTextIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaData(doc, full, id, idRemp);
        transformaText(doc, full, idRemp);
    }

    public void transformaData(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        DataCela d = GetData(doc, full, id);
        d.changeToDate();
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

    public void AllMayus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        TextCela t = GetText(doc, full, id);
        t.AllMayus();
    }

    public void AllMayusIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaText(doc, full, id, idRemp);
        AllMayus(doc, full, idRemp);
    }

    public void AllMinus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
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
        Bloc_celes bc = new Bloc_celes();
        double d = bc.calculaMitjana(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }

    private ArrayList<Numero> ObtenirBlocNumeric(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        Full f = Documents.get(doc).get_full(full);
        return f.getBlocNumero(id1, id2);
    }

    public Boolean ComprovaNumeric(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
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
        Bloc_celes bc = new Bloc_celes();
        double d = bc.calculaMediana(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }

    public void CalculaModa(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
        Bloc_celes bc = new Bloc_celes();
        double d = bc.calculaModa(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }

    public void CalculaVariança(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
        Bloc_celes bc = new Bloc_celes();
        double d = bc.calculaVariança(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }

    public void BuscaMaxim(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
        Bloc_celes bc = new Bloc_celes();
        double d = bc.maxim(bloc);
        modificarContingutCela(doc, full, idfin, Double.toString(d));
    }


    public void CalculaDesviacio(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        ArrayList<Numero> bloc = ObtenirBlocNumeric(doc, full, id1, id2);
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

    public void AllMayusBloc(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<TextCela> bloc = ObtenirBlocText(doc, full, id1, id2);
        Bloc_celes bc = new Bloc_celes();
        bc.remplaçar_majuscules(bloc);
    }

    private ArrayList<TextCela> ObtenirBlocText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        Full f = Documents.get(doc).get_full(full);
        return f.getBlocText(id1, id2);
    }

    public void AllMinusBloc(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<TextCela> bloc = ObtenirBlocText(doc, full, id1, id2);
        Bloc_celes bc = new Bloc_celes();
        bc.remplaçar_minuscules(bloc);
    }

    public void BuscaRemp(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, String buscar, String remp) {
        ArrayList<TextCela> bloc = ObtenirBlocText(doc, full, id1, id2);
        Bloc_celes bc = new Bloc_celes();
        bc.buscar_y_remplazar(bloc, buscar, remp);
    }

    public boolean ComprovaCelaNoOcupa(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        Cela c = f.Consultar_cela(id);
        if(c.getResultatFinal().equals("")) return false;
        else return true;
    }


    public void copiar(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2) {
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

    public void EliminarCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = Documents.get(doc).get_full(full);
        f.Modifica_Cela(id, "");
    }

    public void AfegirFila(String doc, String full, Integer fila) {
        Full f = Documents.get(doc).get_full(full);
        f.Afegir_Fila(fila);
    }

    public void AfegirCol(String doc, String full, Integer c) {
        Full f = Documents.get(doc).get_full(full);
        f.Afegir_Columna(c);
    }

    public void EliminarFila(String doc, String full, Integer fi) {
        Full f = Documents.get(doc).get_full(full);
        f.Eliminar_Fila(fi);
    }

    public void EliminarCol(String doc, String full, Integer co) {
        Full f = Documents.get(doc).get_full(full);
        f.Eliminar_Columna(co);
    }
}
