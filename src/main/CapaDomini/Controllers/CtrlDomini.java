package main.CapaDomini.Controllers;

import main.CapaDades.DataParser;
import main.CapaDomini.Models.*;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.XYChart;

import java.io.File;
import java.util.*;


public class CtrlDomini {

    private Document docu;

    private DataParser dp;

    private static CtrlDomini singletonObject;

    //CONTROLADOR
    public static CtrlDomini getInstance() throws Exception {
        if (singletonObject == null)
            singletonObject = new CtrlDomini() {
            };
        return singletonObject;
    }

    public CtrlDomini() {
        InicialitzarCtrlDomini();
    }

    private void InicialitzarCtrlDomini() {
        docu = new Document("Document sense títol");
        Full nou = new Full("Full 1", 25, 25);
        docu.afegir_full(nou);
        dp = new DataParser();
    }

    //DOCUMENTS
    
    public void CrearDocument(String doc){
        docu = new Document(doc);
        Full nou = new Full("Full 1", 20, 20);
        docu.afegir_full(nou);
    }

    public void CanviarNomDoc(String nom){
        docu.setNom(nom);
    }

    //FULLS
    public void CanviarNomFull(String antic, String nou){
        docu.get_full(antic).SetNom(nou);
    }
    public ArrayList<String> GetFullDoc(){
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Full> fulls = docu.getFulls();
        for (int i = 0; i < fulls.size(); i++) {
            String s = fulls.get(i).getNom();
            temp.add(s);
        }
        return temp;
    }

    public int getNum_Files(String full) {
        Full f = docu.get_full(full);
        return f.getNum_Files();
    }

    public int getNum_Columnes(String full) {
        Full f = docu.get_full(full);
        return f.getNum_Columnes();
    }

    public String[][] Mostrar(String full) { //El full hauria de retornar una ArrayList i així no haver de col·locar tot aixó al controller
        Full f = docu.get_full(full);
        return f.Mostrar();
    }

    public void CrearFull(String Full,Integer nf, Integer nc){
        Full nou = new Full(Full, nf, nc);
        docu.afegir_full(nou);
    }
    public void eliminarFull(String full){
        docu.elimina_full(full);
    }

    //CELA
    public void modificarContingutCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) throws Exception {
        System.out.println("-------"+resultat);
        Full f = docu.get_full(full);
        f.modificarContingutCela(id,resultat);
    }

    public void CheckObs(String full , AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Full f = docu.get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> obs = f.getCeles().get(id).getObservadors();
        System.out.println("check obs V"+obs.size());
        if(obs.size()!=0){
            System.out.println("check obs "+obs.size());
            for (AbstractMap.SimpleEntry<Integer, Integer> ob : obs) {
                if (f.getCeles().get(ob) instanceof CelaRefData) {
                    CelaRefData c = (CelaRefData) f.getCeles().get(ob);
                    modificarContingutCela(full, ob, c.getContingut());
                } else if (f.getCeles().get(ob) instanceof CelaRefText) {
                    CelaRefText c = (CelaRefText) f.getCeles().get(ob);
                    modificarContingutCela(full, ob, c.getContingut());
                } else {
                    System.out.println(ob.getKey() + " " + ob.getValue());
                    CelaRefNum c = (CelaRefNum) f.getCeles().get(ob);
                    modificarContingutCela(full, ob, c.getContingut());
                }

            }

        }
    }


    private Boolean ComprovarTipus(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) {
        String t = GetTipusCela(full, id);
        return (t.equals(tipus));
    }

    public String GetTipusCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(c instanceof Numero) {
            return "numero";
        }
        else if (c instanceof DataCela) {
            return "data";
        }
        else return "text";
    }

    private void CanviarTipusCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String nou_type) throws CloneNotSupportedException {
        Full f = docu.get_full(full);
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

    public String GetTipusNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Numero n = GetNumero(full, id);
        Tipus_Numero type = n.getTipus();
        return type.name();
    }

    public void CanviarTipusNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) throws CloneNotSupportedException {
        Numero n = GetNumero(full, id);
        n.setTipus(Tipus_Numero.valueOf(tipus));
    }

    public int CalculaIncrement(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Boolean comp = ComprovarTipus(full, id, "numero");
        if (comp) {
            Numero n = GetNumero(full, id);
            n.incrementar();
            CheckObs(full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaIncrementIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaIncrement(full, idRemp);
    }

    public int CalculaReduir(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Boolean comp = ComprovarTipus(full, id, "numero");
        if (comp) {
            Numero n = GetNumero(full, id);
            n.reduir();
            CheckObs(full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaReduirIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaReduir(full, idRemp);
    }

    public int CalculaPotencia(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        Boolean comp = ComprovarTipus(full, id, "numero");
        if (comp) {
            Numero n = GetNumero(full, id);
            n.potencia(exp);
            CheckObs(full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaPotenciaIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaPotencia(full, idRemp, exp);
    }

    public int CalculaArrel(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        Boolean comp = ComprovarTipus(full, id, "numero");
        if (comp) {
            Numero n = GetNumero(full, id);
            n.arrel(exp);
            CheckObs(full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaArrelIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaArrel(full, idRemp, exp);
    }

    public int CalculaValorAbs(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        Boolean comp = ComprovarTipus(full, id, "numero");
        if (comp) {
            Numero n = GetNumero(full, id);
            n.valor_absolut();
            CheckObs(full, id);
            return 0;
        }
        else return 1;
    }

    public int CalculaValorAbsIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaValorAbs(full, idRemp);
    }

    public void CalculaConversio(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c) throws Exception {
        Numero n = GetNumero(full, id);
        n.conversio(Tipus_Numero.valueOf(c));
        CanviarTipusNumero(full, id, c);
        CheckObs(full, id);

    }

    public void CalculaConversioIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        CalculaConversio(full, idRemp, c);
    }

    public void CanviarDecimals(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Integer dec) throws CloneNotSupportedException {
        Numero n = GetNumero(full, id);
        n.setNum_Decimals(dec);
    }

    public void CanviarArrodonit(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonir) throws CloneNotSupportedException {
        Numero n = GetNumero(full, id);
        n.setArrodonit(arrodonir);
    }

    private Numero GetNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        return (Numero) c;
    }

    private void ReemplacaNum(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Numero n = GetNumero(full, id);
        String result = n.getResultat().toString();
        modificarContingutCela(full, idRemp, result);
        if (!ComprovarTipus(full, idRemp, "numero")) {
            CanviarTipusCela(full, idRemp, "numero");
        }
    }

    //Operacions Data

    public String getDia(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(full, id);
        return d.getDia();
    }

    public String getMes(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(full, id);
        return d.getMes();
    }

    public String getAny(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(full, id);
        return d.getAny();
    }
    public String getWeekday(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(full, id);
        return d.getWeekDay();
    }
    public String getDataCompleta(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        DataCela d = GetData(full, id);
        return d.getWeekDay() + " " + d.getDia() + " " + d.getMes() + " " + d.getAny();
    }

    public Boolean transformaText(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return false;
        DataCela d = GetData(full, id);
        d.changeToText();
        return true;
    }

    public boolean transformaData(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return false;
        DataCela d = GetData(full, id);
        d.changeToDate();
        return true;
    }

    private DataCela GetData(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        return (DataCela) c;
    }

    //Operacions text

    public void AllMayus(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        TextCela t = GetText(full, id);
        t.AllMayus();
    }

    public void AllMinus(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        TextCela t = GetText(full, id);
        t.AllMinus();
    }

    private TextCela GetText(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        return (TextCela) c;
    }

    public ArrayList<Cela> Busca(String full, String busc){
        System.out.println(busc);
        return Bloc_celes.buscar(docu.get_full(full).CelesArray(), busc);
    }



    public boolean ComprovaCelaNoOcupa(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(c.getResultatFinal().equals("")) return false;
        else return true;
    }


    public void copiar(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2) throws Exception {
        Full f = docu.get_full(full);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = f.GetIdCeles(id1, id2);
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> idsfin = f.GetIdCeles(idfin1, idfin2);
        ArrayList<Cela> celes= new ArrayList<>();
        Cela [][] mat1 = GetMatriu(full, id1, id2);
       Cela [][] mat2 = GetMatriu(full, idfin1, idfin2);
       Bloc_celes bc = new Bloc_celes();
       bc.copiar_contingut(mat1, mat2);
        for (int ah=0;ah< mat2.length;ah++){
            for (int jf = 0; jf < mat2[ah].length; jf++){
                if (mat1[ah][jf] instanceof CelaRefNum || mat1[ah][jf] instanceof CelaRefText || mat1[ah][jf] instanceof CelaRefData){
                    modificarContingutCela(full,mat2[ah][jf].getId(),mat2[ah][jf].getResultatFinal());
                }else {
                    modificarContingutCela(full,mat2[ah][jf].getId(),mat2[ah][jf].getResultatFinal());
                }
            }
        }
    }

    private Cela[][] GetMatriu(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {

        Full fu = docu.get_full(full);
        ArrayList<Cela> C= fu.getBlocCeles(id1,id2);
        Integer nf = id2.getKey() - id1.getKey();
        Integer nc = id2.getValue() - id1.getValue();
        return fu.getBlocEnMatriu(C,nf,nc);
    }



    public boolean ComprovarId(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        return f.ExisteixId(id);
    }

    public void AfegirFila(String full, Integer fila) throws Exception {
        Full f = docu.get_full(full);
        f.Afegir_Fila(fila);
    }

    public void AfegirCol(String full, Integer c) {
        Full f = docu.get_full(full);
        f.Afegir_Columna(c);
    }

    public void EliminarFila(String full, Integer fi) throws Exception {
        Full f = docu.get_full(full);
        f.Eliminar_Fila(fi);
    }

    public void EliminarCol(String full, Integer co) throws Exception {
        Full f = docu.get_full(full);
        f.Eliminar_Columna(co);
    }

    public String ValorTotal(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        return f.ValorTotal(id);
    }

    public void guardarDocument(String fileName, File path) throws Exception {
        System.out.println(fileName);
        String p = path.toString();
        p = p.replace("\\", "/");
        System.out.println(p);
        dp.guarda(docu, p, fileName);
    }

    public void obrirDocument(String fileName, File path) throws Exception {
        String p = path.toString();
        p = p.replace("\\", "/");
        Document nou = dp.carrega(fileName, p);
        docu = nou;
    }

    public XYChart LinearChart(String full, Integer Col1, Integer filI1, Integer filF1,Integer Col2, Integer filI2, Integer filF2) throws Exception {
        Full f = docu.get_full(full);
        if(!NumericColumn(f,Col1, filI1,filF1)
                || !NumericColumn(f,Col2,filI2, filF2))return null;
        return Bloc_celes.linearChart(f.getColNumero(Col1, filI1,filF1),f.getColNumero(Col2,filI2, filF2));
    }
    public PieChart PieChart(String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        Full f = docu.get_full(full);
        System.out.println("aaaaaaaaaaaaaaaaaas");
        if(!NumericColumn(f,Col2,filI2, filF2))return null;
        return Bloc_celes.PieChart(f.getColText(Col1, filI1,filF1),f.getColNumero(Col2,filI2, filF2));
    }
    public CategoryChart Histograma(String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        Full f = docu.get_full(full);
        if(!NumericColumn(f,Col2,filI2, filF2))return null;
        return Bloc_celes.HistoChart(f.getColText(Col1, filI1,filF1),f.getColNumero(Col2,filI2, filF2));
    }

    Boolean NumericColumn(Full f,Integer col, Integer fIni, Integer fFi) throws Exception {
        System.out.println("aa1aaa");
        for(int i = fIni; i <= fFi; i++){
            System.out.println(f.getCeles().get(new AbstractMap.SimpleEntry<>(i,col)).getResultatFinal()+" "+ f.getCeles().get(new AbstractMap.SimpleEntry<>(i,col)).getType());
            if(!Objects.equals(f.getCeles().get(new AbstractMap.SimpleEntry<>(i,col)).getType(), "numeric")) return false;
        }
        System.out.println("suuuu");
        System.out.println("Valid Column");
        return true;

    }

    public void ImportarCSV(String fileName, File path) throws Exception {
        String p = path.toString();
        p = p.replace("\\", "/");
        List<List<String>> nou = DataParser.readCsv(fileName, p);

        Full f = new Full("Full 1", nou.size(), nou.get(0).size());

        docu = new Document(fileName);
        docu.afegir_full(f);

        int i = 0;
        for (List <String> lists: nou) {
            int j = 0;
            for (String s : lists) {
                if (PublicFuntions.isNum(s)) s = s.trim();
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(i, j);
                modificarContingutCela(f.getNom(), id, s);
                j++;
            }
            i++;
        }

    }

    public void exportarCSV(String fileName, File path, String full) throws Exception {
        String p = path.toString();
        p = p.replace("\\", "/");
        DataParser.exportaCsv(fileName, p, Mostrar(full));
    }

    public int Operar_bloc(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2, String operacio, Double oper) throws Exception {
        Full f = docu.get_full(full);
        Cela [][] mat1 = GetMatriu(full, id1, id2);
        Cela [][] mataux = GetMatriu(full, id1, id2);
        Cela [][] mat2 = GetMatriu(full, idfin1, idfin2);
        if ( operacio.equals("suma") || operacio.equals("resta") || operacio.equals("mult") || operacio.equals("div")){

            for (int ah=0;ah< mat1.length;ah++){ //COMPROBAR QUE TODAS SON NUM
                for (int jf = 0; jf < mat1[ah].length; jf++){
                    if (!(mat1[ah][jf] instanceof CelaRefNum) && ! (mat1[ah][jf] instanceof Numero) ) return -1;
                }
            }

        }else if (operacio.equals("min") || operacio.equals("may")){ //COMPROBAR QUE TODAS SON TEXT
            for (int ah=0;ah< mat1.length;ah++){
                for (int jf = 0; jf < mat1[ah].length; jf++){
                    if (! (mat1[ah][jf] instanceof TextCela) ) return -1;
                }
            }
        }
        Bloc_celes bc = new Bloc_celes();
        bc.operar_bloc(mat1, mataux,operacio,oper);
        for (int ah=0;ah< mat2.length;ah++){
            for (int jf = 0; jf < mat2[ah].length; jf++){
                if (mat1[ah][jf] instanceof CelaRefNum || mat1[ah][jf] instanceof CelaRefText || mat1[ah][jf] instanceof CelaRefData){
                    modificarContingutCela(full,mat2[ah][jf].getId(),mataux[ah][jf].getResultatFinal());
                }else {
                    modificarContingutCela(full,mat2[ah][jf].getId(),mataux[ah][jf].getResultatFinal());
                    f.getCeles().replace(mat2[ah][jf].getId(),mataux[ah][jf]);
                }
            }
        }
        return 0;
    }

    public void ordena_bloc(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, ArrayList<Integer> cols, String cont) throws Exception {
        Full f = docu.get_full(full);
        f.ordena_bloc(id1,id2,cols,cont);


    }
}
