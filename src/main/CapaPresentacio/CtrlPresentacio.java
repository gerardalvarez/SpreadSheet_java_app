package main.CapaPresentacio;

import com.formdev.flatlaf.FlatIntelliJLaf;
import main.CapaDomini.Controllers.CtrlDomini;
import main.CapaDomini.Models.Cela;
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


    public CtrlPresentacio() throws Exception {
        Cd = new CtrlDomini();
        FlatIntelliJLaf.setup();
        vc = new VistaPrincipal("Excel", this);
        vc.setVisible(true);
    }
    //DOCUMENTS

    public void crearDoc(String doc){
        Cd.CrearDocument(doc);
    }

    public void NouNomDoc(String doc){
        Cd.CanviarNomDoc(doc);
    }

    public ArrayList<String> GetFulls(){
        return Cd.GetFullDoc();
    }
    //FULLS
    public String[][] MostrarLlista(String full) {
        return Cd.Mostrar(full);
    }

    public int GetFiles(String full) {
        return Cd.getNum_Files(full);
    }

    public int GetColumnes(String full) {
        return Cd.getNum_Columnes(full);
    }

    public void CrearNouFull(String full, Integer nf, Integer nc){
        Cd.CrearFull(full,nf,nc);
    }

    public void EliminarFull(String elimFull){
        Cd.eliminarFull(elimFull);
    }

    public void NouNomFull(String antic, String nou){
        Cd.CanviarNomFull(antic, nou);
    }
    //CELA
    public void ModificarContingutCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) throws Exception {
        Cd.modificarContingutCela(full, id, contingut);
    }

    public String GetTipusCela(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusCela(full, id);
    }

    public String GetTipusNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusNumero(full, id);
    }

    public void CanviarTipusNumero(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) throws CloneNotSupportedException {
        Cd.CanviarTipusNumero(full, id, tipus);
    }

    public int CalculaIncrement(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        return Cd.CalculaIncrement(full, id);
    }

    public int CalculaIncrementIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> id_Remp) throws Exception {
        return Cd.CalculaIncrementIReemplaca(full, id, id_Remp);
    }

    public int CalculaReduir(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        return Cd.CalculaReduir(full, id);
    }

    public int CalculaReduirIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaReduirIReemplaca(full, id, idRemp);
    }

    public int CalculaPotencia(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        return Cd.CalculaPotencia(full, id, exp);
    }

    public int CalculaPotenciaIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaPotenciaIReemplaca(full, id, exp, idRemp);
    }

    public int CalculaArrel(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) throws Exception {
        return Cd.CalculaArrel(full, id, exp);
    }

    public int CalculaArrelIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaArrelIReemplaca(full, id, exp, idRemp);
    }

    public int CalculaValorAbs(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        return Cd.CalculaValorAbs(full, id);
    }

    public int CalculaValorAbsIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        return Cd.CalculaValorAbsIReemplaca(full, id, idRemp);
    }

    public void CalculaConversio(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c) throws Exception {
        Cd.CalculaConversio(full, id, c);
    }

    public void CalculaConversioIReemplaca(String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c, AbstractMap.SimpleEntry<Integer, Integer> idRemp) throws Exception {
        Cd.CalculaConversioIReemplaca(full, id, c, idRemp);
    }

    public void CanviarDecimals(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Integer dec) throws CloneNotSupportedException {
        Cd.CanviarDecimals(full, id, dec);
    }

    public void CanviarArrodonit(String full, AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonir) throws CloneNotSupportedException {
        Cd.CanviarArrodonit(full, id, arrodonir);
    }

    public void AllMayus(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Cd.AllMayus(full, id);
    }

    public void AllMinus(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        Cd.AllMinus(full, id);
    }

    public String getDia(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getDia(full, id);
    }

    public String getMes(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getMes(full, id);
    }
    public String getWeekday(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getWeekday(full, id);
    }

    public String getAny(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getAny(full, id);
    }

    public String getDataCompleta(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getDataCompleta(full, id);
    }

    public boolean transformaText(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        return Cd.transformaText(full, id);
    }

    public boolean transformaData(String full, AbstractMap.SimpleEntry<Integer, Integer> id) throws CloneNotSupportedException {
        return Cd.transformaData(full, id);
    }

    public boolean ComprovaCelaNoOcupa(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ComprovaCelaNoOcupa(full, id);
    }

    public void Copiar(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2) throws Exception {
        Cd.copiar(full, id1, id2, idfin1, idfin2);
    }

    public boolean ComprovarId(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ComprovarId(full, id);
    }

    public void AfegirFila(String full, Integer f) throws Exception {
        Cd.AfegirFila(full, f);
    }

    public void AfegirCol(String full, Integer c) throws Exception {
        Cd.AfegirCol(full, c);
    }

    public void EliminarFila(String full, Integer fi) throws Exception {
        Cd.EliminarFila(full, fi);
    }

    public void EliminarCol(String full, Integer co) throws Exception {
        Cd.EliminarCol(full, co);
    }

    public String ValorTotal(String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ValorTotal(full, id);
    }

    public void guardarDocument(String fileName, File path) throws Exception {
        Cd.guardarDocument(fileName, path);
    }

    public void obrirDocument(String fileName, File path) throws Exception {
        Cd.obrirDocument(fileName, path);
    }

    public XYChart LinearChart(String full, Integer Col1, Integer filI1, Integer filF1,Integer Col2, Integer filI2, Integer filF2) throws Exception {
        return Cd.LinearChart(full, Col1, filI1, filF1, Col2, filI2, filF2);
    }
    public PieChart PieChart(String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        return Cd.PieChart(full, Col1, filI1, filF1, Col2, filI2, filF2);
    }
    public CategoryChart Histograma(String full, Integer Col1, Integer filI1, Integer filF1, Integer Col2, Integer filI2, Integer filF2) throws Exception {
        return Cd.Histograma(full, Col1, filI1, filF1, Col2, filI2, filF2);
    }

    public void ImportarCSV(String fileName, File path) throws Exception {
        Cd.ImportarCSV(fileName, path);
    }

    public void exportarCSV(String fileName, File path, String full) throws Exception {
        Cd.exportarCSV(fileName, path, full);
    }
    public int Opera_bloc (String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, AbstractMap.SimpleEntry<Integer, Integer> idfin1, AbstractMap.SimpleEntry<Integer, Integer> idfin2, String operacio, Double oper) throws Exception {
        return Cd.Operar_bloc(full, id1, id2, idfin1, idfin2, operacio, oper);
    }

    public void ordena_bloc(String full, AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer >id2, ArrayList<Integer> cols, String cont) throws Exception {
    Cd.ordena_bloc(full, id1, id2, cols, cont);
    }
    public ArrayList<Cela> Busca(String full, String busc){
        return Cd.Busca(full, busc);
    }


    public ArrayList<Cela> BuscarRemp(String full, String buscar, String remp) throws Exception {
        return Cd.BuscaRemp(full, buscar, remp);
    }
    public String countWords(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return Cd.countWords(full, id);
    }
    public String countChars(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return Cd.countChars(full, id);
    }
    public String countVowels(String full, AbstractMap.SimpleEntry<Integer, Integer> id){
        return Cd.countVowels(full, id);
    }



}
