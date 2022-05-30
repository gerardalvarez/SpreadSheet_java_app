package main.CapaDomini.Models;

import java.util.Date;
import java.util.*;


/**
 * Implementacio de la classe Document
 * @author Gerard Alvarez
 */
public class Document {

    /**
     * Nom del document
     */
    private String nom;
    /**
     * Data de creacio
     */
    private  Date Data_creacio;
    /**
     * Data ultima modificacio
     */
    private Date Data_ultima_mod;
    /**
     * Numero de fulls
     */
    private Integer numfulls =0;
    /**
     * Estructura on es guarden els fulls
     */
    private final ArrayList<Full> fulls;


    /**
     * Constructora per defecte de Document, se li assigna el nom Untiled doc
     */
    public Document(){
        this.nom = "Untiled_doc";
        Data_creacio= new Date();
        Data_ultima_mod =new Date();
        fulls=new ArrayList<>();
    }
    /**
     * Constructora de Document que li assigna el nom del parametre
     * @param nom del full
     */
    public Document(String nom) {
        this.nom = nom;
        Data_creacio= new Date();
        Data_ultima_mod =new Date();
        fulls=new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom (String nom) {
        this.nom = nom;
    }

    /**
     * Funcio que afegeix el full del parametre al document
     * @param full full a afegir
     */
    public void afegir_full(Full full) {

        fulls.add(full);
        ++numfulls;
    }

    /**
     * Funcio que elimina el full del parametre del document
     * @param Nomfull nom del full a eliminar
     */
    public void elimina_full(String Nomfull) {
        boolean f=false;
        for (Full full : fulls ) {
            if (full.getNom().equals(Nomfull)){
                fulls.remove(full);
                f=true;
                break;
            }
        }
        if (!f) System.out.println("Full no trobat");
        numfulls--;
    }

    public Date getData_ultima_mod(){
        return Data_ultima_mod;
    }

    public Date getData_creacio() {
        return Data_creacio;
    }

    public void setData_creacio(Date d){
        Data_creacio=d;
    }

    public void setData_ultima_mod(Date data_ultima_mod) {

        Data_ultima_mod = data_ultima_mod;
    }

    public ArrayList<Full> getFulls() {

        return fulls;
    }

    public Full get_full(String Nomfull) {
        for (Full full : fulls ) {
            if (full.getNom().equals(Nomfull)) return full;
        }
        return null;
    }

    public Integer getNumfulls() {
        return numfulls;
    }

    public void setNumfulls(Integer numfulls) {
        this.numfulls = numfulls;
    }
}
