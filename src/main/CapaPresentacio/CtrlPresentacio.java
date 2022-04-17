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

    public ArrayList<String> GetFulls(String doc){
        return Cd.GetFullDoc(doc);
    }
    //FULLS
    public ArrayList<String> MostrarLlista() throws Exception {
        return Cd.Mostrar();
    }

    public int GetFiles() {
        return Cd.getNum_Files();
    }

    public int GetColumnes() {
        return Cd.getNum_Columnes();
    }

    public void CrearNouFull(String doc, String full, Integer nf, Integer nc){
        Cd.CrearFull(doc,full,nf,nc);
    }
    public void EliminarFull(String doc, String elimFull){
        Cd.eliminarFull(doc,elimFull);
    }
    //CELA
    public void ModificarContingutCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) {
        Cd.modificarContingutCela(doc, full, id, contingut);
    }

    public Boolean ComprovarTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.ComprovarTipus(doc, full, id);
    }

    public String GetTipusCela(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id) {
        return Cd.GetTipusCela(doc, full, id);
    }

    public void CanviarTipus(String doc, String full, AbstractMap.SimpleEntry<Integer, Integer> id, String nou_type) {
        Cd.CanviarTipusCela(doc, full, id, nou_type);
    }

}
