package main.CapaDomini.Controllers;

import main.CapaDades.DataParser;
import main.CapaDomini.Models.*;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.XYChart;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Ctrl Domini es la classe encarregada de comunicar la Capa de Domini amb les altres capes, la d ePresentacio i la de Dades.
 * @author Gerard Castell, Marc Castells i Gerard Alvarez
 */
public class CtrlDomini {
    /**
     * El ctrl domini treballa sobre un document que és sobre el qual estarem realitzant les operacions
     */
    private Document docu;

    /**
     * Path per utilitzar tenint en compte la C.Dades
     */
    private String path;

    /**
     * Intancia del Data parser
     */
    private DataParser dp;

    /**
     * Instancia singleton del ctrl
     */
    private static CtrlDomini singletonObject;

    //CONTROLADOR

    /**
     * Funcio que retorna una intancia Singleton del Ctrl
     * @return Instancia Singleton Controlador Domini
     */
    public static CtrlDomini getInstance() {
        if (singletonObject == null)
            singletonObject = new CtrlDomini() {
            };
        return singletonObject;
    }

    /**
     * Creadora que inicialitza el ctrl
     */
    public CtrlDomini() {
        InicialitzarCtrlDomini();
    }

    /**
     * Funcio que inicialitza els valors del Crl Domini
     */
    private void InicialitzarCtrlDomini() {
        docu = new Document("Document sense títol");
        Full nou = new Full("Full 1", 25, 25);
        docu.afegir_full(nou);
        dp = new DataParser();
    }

    //DOCUMENTS
    /**
     * Funcio que permet crear un nou document de zero.
     * Aquest també tindra un full buit, "Full 1"
     */
    public void CrearDocument(String doc){
        docu = new Document(doc);
        Full nou = new Full("Full 1", 20, 20);
        docu.afegir_full(nou);
    }

    /**
     * Funcio que permet canviar el nom del Document
     * @param nom nou nom que se li vol posar al Doc
     */
    public void CanviarNomDoc(String nom){
        docu.setNom(nom);
    }

    /**
     * Funcio que retorna nom del document
     * @return nom del doc
     */
    public String getnomDoc(){
        return docu.getNom();
    }

    /**
     * Funcio que retorna data creacio del doc
     * @return data
     */
    public String getdataDoc(){
        return docu.getData_creacio().toString();
    }

    /**
     * Funcio que retorna data de modificacio
     * @return data modificacio
     */
    public String getdatamodDoc(){
        return docu.getData_ultima_mod().toString();
    }

    //FULLS
    /**
     * Funcio que permet canviar el nom a un full
     * @param antic el nom actual del full al qual volem canviar el nom
     * @param nou el nou nom pel full
     */
    public void CanviarNomFull(String antic, String nou){
        docu.get_full(antic).SetNom(nou);
    }

    /**
     * Funcio per obtenir noms dels Fulls d'un doc
     * @return un array amb els noms de tots els fulls de un doc
     */
    public ArrayList<String> GetFullDoc(){
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<Full> fulls = docu.getFulls();
        for (Full full : fulls) {
            String s = full.getNom();
            temp.add(s);
        }
        return temp;
    }

    /**
     * Funcio que retorna el num de files de un full
     * @param full Nom del full del qual volem obtenir el numero de files
     * @return Num de files
     */
    public int getNum_Files(String full) {
        Full f = docu.get_full(full);
        return f.getNum_Files();
    }
    /**
     * Funcio que retorna el num de columnes de un full
     * @param full Nom del full del qual volem obtenir el numero de columnes
     * @return Num de columnes
     */
    public int getNum_Columnes(String full) {
        Full f = docu.get_full(full);
        return f.getNum_Columnes();
    }

    /**
     * Funcio que retorna totes les celes del full
     * @param full ful del qual volem obtenir tot el seu contingur
     * @return "Matriu" amb totes les celes d'un full
     */
    public String[][] Mostrar(String full) {
        Full f = docu.get_full(full);
        return f.Mostrar();
    }

    /**
     * Funcio per crear fulls
     * @param Full nom del nou full
     * @param nf numero files
     * @param nc numero columnes
     */
    public void CrearFull(String Full,Integer nf, Integer nc){
        Full nou = new Full(Full, nf, nc);
        docu.afegir_full(nou);
    }

    /**
     * Funcio que elimina un full
     * @param full nom del full que volem eliminar
     */
    public void eliminarFull(String full){
        docu.elimina_full(full);
    }

    //CELA

    /**
     * Funcio que canvia el contingut de una Cela de un full
     * @param full full on pertany la cela
     * @param id identificardor amb la posicio de la cela al full
     * @param resultat npu contingut de la Cela
     * @throws Exception Exepcio en cas d'error
     */
    public void modificarContingutCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) throws Exception {
        System.out.println("-------"+resultat);
        Full f = docu.get_full(full);
        f.modificarContingutCela(id,resultat);
    }

    /**
     * Funcio que mira els observadors d'una Cela i els modifica després d'un canvi en l'original
     * @param full full on pertany la Cela
     * @param id id de la Cela que s'ha modificat
     * @throws Exception Exepcio en cas d'error
     */
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

    /**
     * Funcio privada per comprovar el tipus d'una Cela amb el tipus desitjat
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param tipus el tipus amb la que la volem comparar
     * @return true si son la cela es del tipus indicat, fals si no
     */
    private Boolean ComprovarTipus(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) {
        String t = GetTipusCela(full, id);
        return (t.equals(tipus));
    }

    /**
     * Funcio que retorna el tipus de una Cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return el tipus de la Cela: data, text o numeric
     */
    public String GetTipusCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if (c instanceof Numero) {
            return "numero";
        } else if (c instanceof DataCela) {
            return "data";
        } else return "text";
    }

    /**
     * Funcio que retorna el tipus de una cela de manera completa incloent subtipus
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return el tipus de la Cela: data, text o numeric amb el subtipus corresponent
     */
    public String GetTipusCelaComplete(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if (c instanceof Numero) {
            System.out.println("HOLA:"+GetTipusNumero(full,id));
            if(Objects.equals(GetTipusNumero(full, id), "numero")) return "numero";
            else return "numero: " + GetTipusNumero(full,id);
        } else if (c instanceof DataCela) {
            return "data";
        } else return "text";
    }

    /**
     * Funcio per canviar el tipus de una Cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param nou_type el nou tipus que li volem posar a la Cela
     */
    private void CanviarTipusCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String nou_type){
        Full f = docu.get_full(full);
        switch (nou_type) {
            case "numero":
                f.Modifica_Tipus_Numeric(id);
                break;
            case "text":
                f.Modifica_Tipus_Text(id);
                break;
            case "data":
                f.Modifica_Tipus_Data(id);
                break;
        }
    }

    /**
     * Funcio que retorna el subtipus de una cela numerica
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return subtipus de cela numerica
     */
    public String GetTipusNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Numero n = GetNumero(full, id);
        Tipus_Numero type = n.getTipus();
        return type.name();
    }

    /**
     * Canviar el subtipus de un numero
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param tipus nou subtipus numero
     */
    public void CanviarTipusNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus){
        Numero n = GetNumero(full, id);
        n.setTipus(Tipus_Numero.valueOf(tipus));
    }

    /**
     * Funcio que calcula el increment de una Cela numerica: ++Cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'Error
     */
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

    /**
     * Funcio que calcula el increment de una Cela numerica: ++Cela. I coloca el resultat en una altra Cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param idRemp  identificador de la cela on colocarem el nou valor
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'Error
     */
    public int CalculaIncrementIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaIncrement(full, idRemp);
    }

    /**
     * Funcio que calcula la reduccio de una cela numerica: --Cela.
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'error
     */
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

    /**
     * Funcio que calcula la reudccio de una Cela numerica: --Cela. I coloca el resultat en una altra Cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param idRemp  identificador de la cela on colocarem el nou valor
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'Error
     */
    public int CalculaReduirIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaReduir(full, idRemp);
    }

    /**
     * Funcio que calcula la potencia de una Cela numerica
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param exp exponent de la potencia
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'Error
     */
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

    /**
     * Funcio que calcula la potencia de una Cela numerica. I coloca el resultat en una altra Cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param idRemp  identificador de la cela on colocarem el nou valor
     * @param exp  exponent de la potencia
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'Error
     */
    public int CalculaPotenciaIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaPotencia(full, idRemp, exp);
    }

    /**
     * Funcio que calcula la arrel de una Cela numerica
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param exp radical de l'arrel
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'Error
     */
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

    /**
     * Funcio que calcula la arrel de una Cela numerica. I coloca el resultat en una altra Cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param idRemp  identificador de la cela on colocarem el nou valor
     * @param exp  radical de l'arrel
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'Error
     */
    public int CalculaArrelIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaArrel(full, idRemp, exp);
    }

    /**
     * Funcio que calcula valor absolut de una Cela numerica
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'Error
     */
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

    /**
     * Funcio que calcula valor absolut de una Cela numerica. I coloca el resultat en una altra Cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param idRemp  identificador de la cela on colocarem el nou valor
     * @return nombre segons l'estat
     * @throws Exception Exepcio en cas d'Error
     */
    public int CalculaValorAbsIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        return CalculaValorAbs(full, idRemp);
    }

    /**
     * Funcio que calcula una conversio numerica
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param c nom de la conversio
     * @throws Exception Exeepcio en cas d'Error
     */
    public void CalculaConversio(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c) throws Exception {
        Numero n = GetNumero(full, id);
        n.conversio(Tipus_Numero.valueOf(c));
        CanviarTipusNumero(full, id, c);
        CheckObs(full, id);

    }

    /**
     * Funcio que calcula una conversio numerica i la remplaça a una altra cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param idRemp  identificador de la cela on colocarem el nou valor
     * @param c nom de la conversio
     * @throws Exception Exepcio en cas d'Error
     */
    public void CalculaConversioIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        ReemplacaNum(full, id, idRemp);
        CalculaConversio(full, idRemp, c);
    }

    /**
     * Funcio que canvia el numero de decimals de una cela numerica
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param dec numero de decimals que s'han de mostrar
     */
    public void CanviarDecimals(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Integer dec){
        Numero n = GetNumero(full, id);
        n.setNum_Decimals(dec);
    }

    /**
     * Funcio que canvia el tipo d'arrodoniment d'una Cela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param arrodonir tipus arrodoniment: truncament o arrodoniment
     */
    public void CanviarArrodonit(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonir){
        Numero n = GetNumero(full, id);
        n.setArrodonit(arrodonir);
    }

    /**
     * Funcio que retorna el numero de una Cela numerica
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return el numero es retorna
     */
    private Numero GetNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        return (Numero) c;
    }

    /**
     * Funcio que retorna el numero de una Cela numerica i el remplaça
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @param idRemp cela on es coloca
     */
    private void ReemplacaNum(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Numero n = GetNumero(full, id);
        String result = n.getResultat().toString();
        modificarContingutCela(full, idRemp, result);
        if (!ComprovarTipus(full, idRemp, "numero")) {
            CanviarTipusCela(full, idRemp, "numero");
        }
    }

    //OPERACIONS CELA DATA

    /**
     * Funcio que retorna el dia de una Cela Data
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return retorna el dia d'una data
     */
    public String getDia(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(full, id);
        return d.getDia();
    }

    /**
     * Funcio que retorna el mes de una Cela Data
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return retorna el mes d'una data
     */
    public String getMes(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(full, id);
        return d.getMes();
    }

    /**
     * Funcio que retorna el any de una Cela Data
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return retorna el any d'una data
     */
    public String getAny(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(full, id);
        return d.getAny();
    }
    /**
     * Funcio que retorna el dia de la setmana de una Cela Data
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return retorna el dia de la setmana d'una data
     */
    public String getWeekday(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return "null";
        DataCela d = GetData(full, id);
        return d.getWeekDay();
    }
    /**
     * Funcio que retorna una data amb el dia de la setmana de una Cela Data
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return retorna la data completa
     */
    public String getDataCompleta(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        DataCela d = GetData(full, id);
        return d.getWeekDay() + " " + d.getDia() + " " + d.getMes() + " " + d.getAny();
    }

    /**
     * Funcio que transforma una data en format data a text
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return retorna el string en format text
     */
    public Boolean transformaText(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return false;
        DataCela d = GetData(full, id);
        d.changeToText();
        return true;
    }

    /**
     * Funcio que transforma una data en format text a data
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return retorna el string en format data
     */
    public boolean transformaData(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        if(!Objects.equals(c.getType(), "date"))return false;
        DataCela d = GetData(full, id);
        d.changeToDate();
        return true;
    }

    /**
     * Funcio que retorna la data de una DataCela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return retorna la data
     */
    private DataCela GetData(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        return (DataCela) c;
    }

    //OPERACIONS

    /**
     * Funcio que canvia un text a tot majuscules
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     */
    public void AllMayus(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        TextCela t = GetText(full, id);
        t.AllMayus();
    }

    /**
     * Funcio que canvia un text a tot minuscules
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     */
    public void AllMinus(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        TextCela t = GetText(full, id);
        t.AllMinus();
    }

    /**
     * Funcio que retorna lun text d'una TextCela
     * @param full full de la Cela que volem comprovar
     * @param id identificador cela
     * @return retorna el text
     */
    private TextCela GetText(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        return (TextCela) c;
    }

    /**
     * Busca a totes les celes del full una String
     * @param full on volem trobar les celes
     * @param busc string que volem que les celes continguin
     * @return array amb les celes que continguin el string
     */
    public ArrayList<Cela> Busca(String full, String busc){
        System.out.println(busc);
        return Bloc_celes.buscar(docu.get_full(full).CelesArray(), busc);
    }


    /**
     * Comprova si una cela esta buida o no
     * @param full on volem trobar la cela
     * @param id identificador cela
     * @return true si esta buit, si no fals
     */
    public boolean ComprovaCelaNoOcupa(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        Cela c = f.Consultar_cela(id);
        return !c.getResultatFinal().isBlank() && !c.getResultatFinal().isEmpty();
    }

    /**
     * Retorna el resultat final de una de les celes
     * @param full on volem trobar la cela
     * @param id identificador cela
     * @return resultat final a mostrar
     */
    public String resultatfinal(String full,AbstractMap.SimpleEntry<Integer, Integer> id){
        return docu.get_full(full).Consultar_cela(id).getResultatFinal();
    }

    /**
     * Funcio que copa un bloc de celes a un altre lloc del full
     * @param full full on volem trobar les celes
     * @param id1 cela inici del bloc a copiar
     * @param id2 cela inici del bloc on colocarem
     * @param idfin1 cela final del bloc a copiar
     * @param idfin2 cela final del bloc on colocarem
     * @throws Exception Exepcio en cas d'Error
     */
    public void copiar(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2) throws Exception {Cela [][] mat1 = GetMatriu(full, id1, id2);
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

    /**
     * Funcio que retorna un bloc de celes
     * @param full full on volem trobar les celes
     * @param id1 cela inici del bloc
     * @param id2 cela final del bloc
     * @return "matriu" de celes d'un bloc
     */
    private Cela[][] GetMatriu(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {

        Full fu = docu.get_full(full);
        ArrayList<Cela> C= fu.getBlocCeles(id1,id2);
        Integer nf = id2.getKey() - id1.getKey();
        Integer nc = id2.getValue() - id1.getValue();
        return fu.getBlocEnMatriu(C,nf,nc);
    }


    /**
     * Funcio que comprova si existeix una id
     * @param full on volem trobar la cela
     * @param id identificador cela
     * @return si existeix true, si no fals
     */
    public boolean ComprovarId(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        return f.ExisteixId(id);
    }

    /**
     * Funico que afegeix una fila a una posicio en concret al full
     * @param full on volem colocar la fila
     * @param fila numero de fila on la volem colocar
     * @throws Exception En cas de error, Exepcio
     */
    public void AfegirFila(String full, Integer fila) throws Exception {
        Full f = docu.get_full(full);
        f.Afegir_Fila(fila);
    }

    /**
     * Funico que afegeix una columna a una posicio en concret al full
     * @param full on volem colocar la columna
     * @param c numero de columna on la volem colocar
     * @throws Exception En cas de error, Exepcio
     */
    public void AfegirCol(String full, Integer c) throws Exception {
        Full f = docu.get_full(full);
        f.Afegir_Columna(c);
    }

    /**
     * Funico que eliminar una fila a una posicio en concret al full
     * @param full on volem colocar la fila
     * @param fi numero de fila on la volem treure
     * @throws Exception En cas de error, Exepcio
     */
    public void EliminarFila(String full, Integer fi) throws Exception {
        Full f = docu.get_full(full);
        f.Eliminar_Fila(fi);
    }

    /**
     * Funico que treu una columna a una posicio en concret al full
     * @param full on volem colocar la columna
     * @param co numero de columna on la volem treure
     * @throws Exception En cas de error, Exepcio
     */
    public void EliminarCol(String full, Integer co) throws Exception {
        Full f = docu.get_full(full);
        f.Eliminar_Columna(co);
    }

    /**
     * Funcio que retorna el contingut de una Cela. Si es referencia contingut, si no resultat final
     * @param full on volem trobar la cela
     * @param id identificador cela
     * @return valor total de la cela
     */
    public String ValorTotal(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Full f = docu.get_full(full);
        return f.ValorTotal(id);
    }

    /**
     * Funcio que guarda els canvis de un document
     * @param fileName nom del Doc
     * @param path path on es vol guardar el document
     * @throws Exception Exepcio en cas d'Error
     */
    public void guardarDocument(String fileName, File path) throws Exception {
        System.out.println(fileName);
        String p = path.toString();
        p = p.replace("\\", "/");
        this.path = p;
        CanviarNomDoc(fileName);
        System.out.println(p);
        dp.guarda(docu, p, fileName);
    }

    /**
     * Funcio que ens permet obrir un document d'un path en concret
     * @param fileName nom del doc
     * @param path path on es troba el document a obrir
     * @throws Exception Exepcio en cas d'error
     */
    public void obrirDocument(String fileName, File path) throws Exception {
        String p = path.toString();
        p = p.replace("\\", "/");
        docu = dp.carrega(fileName, p);
        this.path = p;
    }

    //FUNCIONS DE CELA, GRAFS
    /**
     * Funcio que a partir de dues columnes fa un graf linear
     * @param full full on es troben le celes
     * @param Col1 nombre de la primera col
     * @param filI1 de la primera col la fila inicial
     * @param filF1 de la primera col la fila final
     * @param Col2 segona columna
     * @param filI2 de la segona col la fila inicial
     * @param filF2 de la segona col la fila final
     * @return retorna un graf linear
     */
    public XYChart LinearChart(String full, Integer Col1, Integer filI1, Integer filF1,Integer Col2, Integer filI2, Integer filF2){
        Full f = docu.get_full(full);
        if(!NumericColumn(f,Col1, filI1,filF1)
                || !NumericColumn(f,Col2,filI2, filF2))return null;
        return Bloc_celes.linearChart(f.getColNumero(Col1, filI1,filF1),f.getColNumero(Col2,filI2, filF2));
    }

    /**
     * Funcio que a partir de dues columnes fa un graf circular
     * @param full full on es troben le celes
     * @param Col1 nombre de la primera col
     * @param filI1 de la primera col la fila inicial
     * @param filF1 de la primera col la fila final
     * @param Col2 segona columna
     * @param filI2 de la segona col la fila inicial
     * @param filF2 de la segona col la fila final
     * @return retorna un graf circular
     */
    public PieChart PieChart(String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2){
        Full f = docu.get_full(full);
        System.out.println("aaaaaaaaaaaaaaaaaas");
        if(!NumericColumn(f,Col2,filI2, filF2))return null;
        return Bloc_celes.PieChart(f.getColText(Col1, filI1,filF1),f.getColNumero(Col2,filI2, filF2));
    }

    /**
     * Funcio que a partir de dues columnes fa un graf de barres
     * @param full full on es troben le celes
     * @param Col1 nombre de la primera col
     * @param filI1 de la primera col la fila inicial
     * @param filF1 de la primera col la fila final
     * @param Col2 segona columna
     * @param filI2 de la segona col la fila inicial
     * @param filF2 de la segona col la fila final
     * @return retorna un graf de barres
     */
    public CategoryChart Histograma(String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2){
        Full f = docu.get_full(full);
        if(!NumericColumn(f,Col2,filI2, filF2))return null;
        return Bloc_celes.HistoChart(f.getColText(Col1, filI1,filF1),f.getColNumero(Col2,filI2, filF2));
    }

    /**
     * Funcio que comprova si una columan es tota de celes numeriques
     * @param f full on estan les celes
     * @param col columna on estan les celes
     * @param fIni fila inicial de les celes
     * @param fFi fila final de les celes
     * @return true si totes son numeriques, si no fals
     */
    Boolean NumericColumn(Full f,Integer col, Integer fIni, Integer fFi) {
        System.out.println("aa1aaa");
        for(int i = fIni; i <= fFi; i++){
            System.out.println(f.getCeles().get(new AbstractMap.SimpleEntry<>(i,col)).getResultatFinal()+" "+ f.getCeles().get(new AbstractMap.SimpleEntry<>(i,col)).getType());
            if(!Objects.equals(f.getCeles().get(new AbstractMap.SimpleEntry<>(i,col)).getType(), "numeric")) return false;
        }
        System.out.println("suuuu");
        System.out.println("Valid Column");
        return true;

    }

    /**
     * Funcio que permet importar un fitxer en csv d'un path
     * @param fileName nom del fitxer a importar
     * @param path lloc on es troba el fitxer
     * @throws Exception Exepcio en cas d'error
     */
    public void ImportarCSV(String fileName, File path) throws Exception {
        String p = path.toString();
        p = p.replace("\\", "/");
        List<List<String>> nou = DataParser.readCsv(fileName, p);
        Full f = new Full("Full CSV", nou.size(), nou.get(0).size());
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

    /**
     * Funcio que permet exportar un full a un fitxer CSV
     * @param fileName nom amb el qual es guarda el fitxer
     * @param path lloc on guardarem el fitxer
     * @param full nom del full on es guardara
     * @throws Exception Exepcio en cas d'error
     */
    public void exportarCSV(String fileName, File path, String full) throws Exception {
        String p = path.toString();
        p = p.replace("\\", "/");
        DataParser.exportaCsv(fileName, p, Mostrar(full));
    }

    /**
     * Funcio per operar diferents operacions en un bloc de celes
     * @param full full on estan
     * @param id1 ids inicial bloc 1
     * @param id2 id inicial bloc 2
     * @param idfin1 id final bloc 1
     * @param idfin2 id final bloc 2
     * @param operacio operacio que volem realitzar
     * @param oper operands
     * @return retorna el resultat de la operacio numerica
     * @throws Exception Exepcio en cas d'error
     */
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

    /**
     * Funcio que ordena un bloc
     * @param full full on estan celes
     * @param id1 id inical
     * @param id2 id final
     * @param cols array amb nombre columnes
     * @param cont a contar
     * @throws Exception Exepcio en cas d'Error
     */
    public void ordena_bloc(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, ArrayList<Integer> cols, String cont) throws Exception {
        Full f = docu.get_full(full);
        f.ordena_bloc(id1,id2,cols,cont);
    }

    /**
     * Funcio per buscar un string i reemplaçar-lo per un altre
     * @param full full al qual busquem la particula
     * @param buscar paraula o caracters a buscar
     * @param remp en cas de trobar els caracters es reemplacen per aquest string
     * @return un array amb les celes que si compleixen la condicio
     */
    public ArrayList<Cela> BuscaRemp( String full, String buscar, String remp){
        Bloc_celes bc = new Bloc_celes();
        return bc.buscar_y_remplazar(docu.get_full(full).CelesArray(), buscar, remp);
    }

    /**
     * Funcio que conta el numero de paraules de una cela
     * @param full full on es troba la cela
     * @param id identificador de la cela al full
     * @return el nombre de paraules que conte
     */
    public String countWords(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return docu.get_full(full).getCeles().get(id).countWords();
    }
    /**
     * Funcio que conta el numero de caracters d'una cela
     * @param full full on es troba la cela
     * @param id identificador de la cela al full
     * @return el nombre de caracters que conte
     */
    public String countChars(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return docu.get_full(full).getCeles().get(id).countChars();
    }
    /**
     * Funcio que conta el numero de vocals d'una cela
     * @param full full on es troba la cela
     * @param id identificador de la cela al full
     * @return el nombre de vocals que conte
     */
    public String countVowels(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return docu.get_full(full).getCeles().get(id).countVowels();
    }

    /**
     * Funcio per guardar els canvis en un doc ja guardat previament
     * @return retorna un int per indicar els estats a altres capes
     * @throws Exception Exepcio en cas d'Error
     */
    public int guardarDoc() throws Exception {
        if (path == null) return 1;
        else {
            String nomDocu = docu.getNom();
            System.out.println(nomDocu);
            System.out.println(path);
            dp.guarda(docu, path, nomDocu);
            return 0;
        }
    }

    /**
     * Funcio que comprova si un doc en path ja ha estat guardat
     * @param fileName nom del document
     * @param path path on podria ser guardat
     * @return true si ja existeix, si no fals
     * @throws IOException Exepcio en cas d'error
     */
    public Boolean ComprovaDocExisteix(String fileName, File path) throws IOException {
        String p = path.toString();
        p = p.replace("\\", "/");
        return dp.comprovaExisteix(docu, p, fileName);
    }

    /**
     * Funcio que comprova si un CSV en path ja ha estat guardat
     * @param fileName nom del document
     * @param path path on podria ser guardat
     * @return true si ja existeix, si no fals
     * @throws IOException Exepcio en cas d'error
     */
    public Boolean ComprovaExisteixCSV(String fileName, File path) throws IOException {
        String p = path.toString();
        p = p.replace("\\", "/");
        return dp.comprovaExisteixCSV(docu, p, fileName);
    }

    public int getnumfulls() {
        return docu.getFulls().size();
    }
}
