package main.CapaDomini.Models;

import java.util.Date;
import java.util.*;

public class Document {

    private String nom;
    private final Date Data_creacio;
    private Date Data_ultima_mod;
    private Integer numfulls =1;
    private final ArrayList<Full> fulls;

    public Document(){
        this.nom = "Untiled_doc";
        Data_creacio= new Date();
        Data_ultima_mod =new Date();
        fulls=new ArrayList<>();
        fulls.add(new Full(3,3));
    }

    public Document(String nom) {
        this.nom = nom;
        Data_creacio= new Date();
        Data_ultima_mod =new Date();
        fulls=new ArrayList<>();
        fulls.add(new Full(3,3));
    }

    public String getNom() {
        return nom;
    }

    public void setNom (String nom) {
        this.nom = nom;
    }


    public void afegir_full(Full full) {

        fulls.add(full);
        ++numfulls;
    }

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
