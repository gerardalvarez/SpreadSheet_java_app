package main.CapaPresentacio;

import com.formdev.flatlaf.FlatIntelliJLaf;
import main.CapaDomini.Controllers.CtrlDomini;
import main.CapaDomini.Models.Document;
import main.CapaDomini.Models.Numero;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.XYChart;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;

public class CtrlPresentacio {

    private final CtrlDomini Cd;
    private final VistaPrincipal vc;
    private static inout io;



    public CtrlPresentacio() throws Exception {
        Cd = new CtrlDomini();
        FlatIntelliJLaf.setup();
        io = new inout();
        vc = new VistaPrincipal("Excel", this);
        vc.setVisible(true);
    }
    //DOCUMENTS
    public ArrayList<String> GetDocs(){
        return Cd.GetDocuments();
    }

    public void crearDoc(String doc){
        Cd.CrearDocument(doc);
    }

    public void EliminarDoc(String elimDoc){
        Cd.EliminarDocument(elimDoc);
    }

    public void NouNomDoc(String doc, String antic){
        Cd.CanviarNomDoc(doc, antic);
    }

    public ArrayList<String> GetFulls(String doc){
        return Cd.GetFullDoc(doc);
    }
    //FULLS
    public String[][] MostrarLlista(String doc, String full) throws Exception {
        return Cd.Mostrar(doc, full);
    }

    public int GetFiles(String doc, String full) {
        return Cd.getNum_Files(doc, full);
    }

    public int GetColumnes(String doc, String full) {
        return Cd.getNum_Columnes(doc, full);
    }

    public void CrearNouFull(String doc, String full, Integer nf, Integer nc){
        Cd.CrearFull(doc,full,nf,nc);
    }
    public void EliminarFull(String doc, String elimFull){
        Cd.eliminarFull(doc,elimFull);
    }

    public void NouNomFull(String doc, String antic, String nou){
        Cd.CanviarNomFull(doc, antic, nou);
    }
    //CELA
    public void ModificarContingutCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) throws Exception {
        Cd.modificarContingutCela(doc, full, id, contingut);
    }

    public Boolean ComprovarTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) throws Exception {
        return Cd.ComprovarTipus(doc, full, id, tipus);
    }

    public String GetTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusCela(doc, full, id);
    }

    public void CanviarTipus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String nou_type) throws CloneNotSupportedException {
        Cd.CanviarTipusCela(doc, full, id, nou_type);
    }

    public String GetTipusNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusNumero(doc, full, id);
    }

    public boolean TipusValid(String s) {
        return Cd.TipusNumeroValid(s);
    }

    public void CanviarTipusNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) throws CloneNotSupportedException {
        Cd.CanviarTipusNumero(doc, full, id, tipus);
    }

    public int CalculaIncrement(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        return Cd.CalculaIncrement(doc, full, id);
    }

    public int CalculaIncrementIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> id_Remp) throws Exception {
        return Cd.CalculaIncrementIReemplaca(doc, full, id, id_Remp);
    }

    public int CalculaReduir(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        return Cd.CalculaReduir(doc, full, id);
    }

    public int CalculaReduirIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaReduirIReemplaca(doc, full, id, idRemp);
    }

    public int CalculaPotencia(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        return Cd.CalculaPotencia(doc, full, id, exp);
    }

    public int CalculaPotenciaIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaPotenciaIReemplaca(doc, full, id, exp, idRemp);
    }

    public int CalculaArrel(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        return Cd.CalculaArrel(doc, full, id, exp);
    }

    public int CalculaArrelIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaArrelIReemplaca(doc, full, id, exp, idRemp);
    }

    public int CalculaValorAbs(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        return Cd.CalculaValorAbs(doc, full, id);
    }

    public int CalculaValorAbsIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaValorAbsIReemplaca(doc, full, id, idRemp);
    }

    public void CalculaConversio(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c) throws Exception {
        Cd.CalculaConversio(doc, full, id, c);
    }

    public void CalculaConversioIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Cd.CalculaConversioIReemplaca(doc, full, id, c, idRemp);
    }

    public void CanviarDecimals(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Integer dec) throws CloneNotSupportedException {
        Cd.CanviarDecimals(doc, full, id, dec);
    }

    public void CanviarArrodonit(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonir) throws CloneNotSupportedException {
        Cd.CanviarArrodonit(doc, full, id, arrodonir);
    }

    public void AllMayus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Cd.AllMayus(doc, full, id);
    }

    public void AllMayusIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Cd.AllMayusIReemplaca(doc, full, id, idRemp);
    }

    public void AllMinus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Cd.AllMinus(doc, full, id);
    }

    public void AllMinusIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Cd.AllMinusIReemplaca(doc, full, id, idRemp);
    }

    public String getDia(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getDia(doc, full, id);
    }

    public String getMes(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getMes(doc, full, id);
    }
    public String getWeekday(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getWeekday(doc, full, id);
    }

    public String getAny(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getAny(doc, full, id);
    }

    public String getDataCompleta(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getDataCompleta(doc, full, id);
    }

    public boolean transformaText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        return Cd.transformaText(doc, full, id);
    }

    public void transformaTextIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Cd.transformaTextIReemplaca(doc, full, id, idRemp);
    }

    public boolean transformaData(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        return Cd.transformaData(doc, full, id);
    }

    public void transformaDataIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Cd.transformaDataIReemplaca(doc, full, id, idRemp);
    }

    public void CalculaMitjana(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        Cd.CalculaMitjana(doc, full, id1, id2, idfin);
    }

    public boolean ComprovaNumeric(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        return Cd.ComprovaNumeric(doc, full, id1, id2);
    }

    public void CalculaMediana(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        Cd.CalculaMediana(doc, full, id1, id2, idfin);
    }

    public void CalculaModa(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        Cd.CalculaModa(doc, full, id1, id2, idfin);
    }

    public void CalculaVariança(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        Cd.CalculaVariança(doc, full, id1, id2, idfin);
    }

    public void BuscaMaxim(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        Cd.BuscaMaxim(doc, full, id1, id2, idfin);
    }

    public void CalculaDesviacio(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin) throws Exception {
        Cd.CalculaDesviacio(doc, full, id1, id2, idfin);
    }

    public boolean ComprovaText(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        return Cd.ComprovaText(doc, full, id1, id2);
    }

    public void AllMayusBloc(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) throws Exception {
        Cd.AllMayusBloc(doc, full, id1, id2);
    }

    public void AllMinusBloc(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) throws Exception {
        Cd.AllMinusBloc(doc, full, id1, id2);
    }

    public void BuscarRemp(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, String buscar, String remp) throws Exception {
        Cd.BuscaRemp(doc, full, id1, id2, buscar, remp);
    }

    public boolean ComprovaCelaNoOcupa(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ComprovaCelaNoOcupa(doc, full, id);
    }

    public void Copiar(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2) throws Exception {
        Cd.copiar(doc, full, id1, id2, idfin1, idfin2);
    }

    public boolean ComprovarId(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ComprovarId(doc, full, id);
    }

    public void EliminarCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Cd.EliminarCela(doc, full, id);
    }

    public void AfegirFila(String doc, String full, Integer f) throws Exception {
        Cd.AfegirFila(doc, full, f);
    }

    public void AfegirCol(String doc, String full, Integer c) throws Exception {
        Cd.AfegirCol(doc, full, c);
    }

    public void EliminarFila(String doc, String full, Integer fi) throws Exception {
        Cd.EliminarFila(doc, full, fi);
    }

    public void EliminarCol(String doc, String full, Integer co) throws Exception {
        Cd.EliminarCol(doc, full, co);
    }

    public void Undo(String doc, String full) throws Exception {
        Cd.Undo(doc, full);
    }

    public String ValorTotal(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ValorTotal(doc, full, id);
    }

    public void guardarDocument(String fileName, File path) throws Exception {
        Cd.guardarDocument(fileName, path);
    }

    public void obrirDocument(String fileName, File path) throws Exception {
        Cd.obrirDocument(fileName, path);
    }

    public XYChart LinearChart(String doc, String full, Integer Col1, Integer filI1, Integer filF1,Integer Col2, Integer filI2, Integer filF2) throws Exception {
        return Cd.LinearChart(doc,full,Col1, filI1, filF1, Col2,filI2, filF2);
    }
    public PieChart PieChart(String doc, String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        return Cd.PieChart(doc,full,Col1, filI1, filF1, Col2,filI2, filF2);
    }
    public CategoryChart Histograma(String doc, String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        return Cd.Histograma(doc,full,Col1, filI1, filF1, Col2,filI2, filF2);
    }

    public void ImportarCSV(String fileName, File path) throws Exception {
        Cd.ImportarCSV(fileName, path);
    }

    public void exportarCSV(String fileName, File path) throws Exception {
        Cd.exportarCSV(fileName, path);
    }
}

