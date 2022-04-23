package main.CapaPresentacio;

import main.CapaDomini.Controllers.CtrlDomini;
import main.CapaDomini.Models.Document;


import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class CtrlPresentacio {

    private final CtrlDomini Cd;
    private final VistaTerminal Vp;
    private static inout io;



    public CtrlPresentacio() throws Exception {
        Cd = new CtrlDomini();
        Vp = new VistaTerminal(this);
        io = new inout();
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
    public ArrayList<String> MostrarLlista(String doc, String full) throws Exception {
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
    public void ModificarContingutCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) {
        Cd.modificarContingutCela(doc, full, id, contingut);
    }

    public Boolean ComprovarTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) throws Exception {
        return Cd.ComprovarTipus(doc, full, id, tipus);
    }

    public String GetTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusCela(doc, full, id);
    }

    public void CanviarTipus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String nou_type) {
        Cd.CanviarTipusCela(doc, full, id, nou_type);
    }

    public String GetTipusNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusNumero(doc, full, id);
    }

    public boolean TipusValid(String s) {
        return Cd.TipusNumeroValid(s);
    }

    public void CanviarTipusNumero(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String tipus) {
        Cd.CanviarTipusNumero(doc, full, id, tipus);
    }

    public void CalculaIncrement(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Cd.CalculaIncrement(doc, full, id);
    }

    public void CalculaIncrementIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> id_Remp) throws Exception {
        Cd.CalculaIncrementIReemplaca(doc, full, id, id_Remp);
    }

    public void CalculaReduir(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Cd.CalculaReduir(doc, full, id);
    }

    public void CalculaReduirIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        Cd.CalculaReduirIReemplaca(doc, full, id, idRemp);
    }

    public void CalculaPotencia(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) {
        Cd.CalculaPotencia(doc, full, id, exp);
    }

    public void CalculaPotenciaIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        Cd.CalculaPotenciaIReemplaca(doc, full, id, exp, idRemp);
    }

    public void CalculaArrel(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp) {
        Cd.CalculaArrel(doc, full, id, exp);
    }

    public void CalculaArrelIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Double exp, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        Cd.CalculaArrelIReemplaca(doc, full, id, exp, idRemp);
    }

    public void CalculaValorAbs(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Cd.CalculaValorAbs(doc, full, id);
    }

    public void CalculaValorAbsIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        Cd.CalculaValorAbsIReemplaca(doc, full, id, idRemp);
    }

    public void CalculaConversio(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c) {
        Cd.CalculaConversio(doc, full, id, c);
    }

    public void CalculaConversioIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String c, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        Cd.CalculaConversioIReemplaca(doc, full, id, c, idRemp);
    }

    public void CanviarDecimals(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Integer dec) {
        Cd.CanviarDecimals(doc, full, id, dec);
    }

    public void CanviarArrodonit(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, Boolean arrodonir) {
        Cd.CanviarArrodonit(doc, full, id, arrodonir);
    }

    public void AllMayus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Cd.AllMayus(doc, full, id);
    }

    public void AllMayusIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        Cd.AllMayusIReemplaca(doc, full, id, idRemp);
    }

    public void AllMinus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        Cd.AllMinus(doc, full, id);
    }

    public void AllMinusIReemplaca(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> idRemp) {
        Cd.AllMinusIReemplaca(doc, full, id, idRemp);
    }

    public String getDia(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.getDia(doc, full, id);
    }
}
