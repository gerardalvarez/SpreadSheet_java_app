package main.CapaDomini.Models;

import java.util.Date;
import java.util.*;

public class Document {

    private String nom;
    private final Date Data_creacio;
    private Date Data_ultima_mod;
    private ArrayList<Full> fulls;

    public Document(){
        this.nom = "Untiled_doc";
        Data_creacio= new Date();
        Data_ultima_mod =new Date();
        fulls=new ArrayList<>();
    }

    public Document(String nom){
        this.nom = nom;
        Data_creacio= new Date();
        Data_ultima_mod =new Date();
        fulls=new ArrayList<>();
    }


    public void set_nom (String nom){
        this.nom = nom;

    }

    public void afegir_full(Full full){
        fulls.add(full);
    }

    public void elimina_full(String nomfull){
        for (Full full : fulls ){
            if (full.get_nom().equals(nomfull)) fulls.remove(full);
            break;
        }
    }

    public void setData_ultima_mod(Date data_ultima_mod) {
        Data_ultima_mod = data_ultima_mod;
    }

    public ArrayList<Full> getFulls() {
        return fulls;
    }

    public Full get_full(String nomfull) {
        for (Full full : fulls ) {
            if (full.get_nom().equals(nomfull)) return full;
        }
        return null;
    }


    static private class Full {
        private String nom="x";
        Full(){}
        public String get_nom() {
            return  this.nom;
        }
    }
}
