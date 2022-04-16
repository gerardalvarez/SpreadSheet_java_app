package main.CapaDomini.Models;

import java.util.Date;
import java.util.*;

public class Document {

    private String nom;
    private final Date Data_creacio;
    private Date Data_ultima_mod;
    private final ArrayList<Full> fulls;

    public Document(){
        this.nom = "Untiled_doc";
        Data_creacio= new Date();
        Data_ultima_mod =new Date();
        fulls=new ArrayList<>();
    }

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


    public void afegir_full(Full full) {

        fulls.add(full);
    }

    public void elimina_full(String Nomfull) {
        for (Full full : fulls ) {
            if (full.getNom().equals(Nomfull)) fulls.remove(full);
            break;
        }
    }

    public Date getData_ultima_mod(){
        return Data_ultima_mod;
    }

    public Date getData_creacio() {
        return Data_creacio;
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
}
