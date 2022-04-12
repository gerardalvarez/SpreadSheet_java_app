package main.CapaDomini.Models;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class Full {
    private Integer ID;
    private String nom;
    private Integer Num_Columnes;
    private Integer Num_Files;
    private Cela_Proba[] Cela;
    private HashMap<AbstractMap.SimpleEntry<String,Integer>, Cela_Proba> Celes;

    //Constructor
    public Full(Integer id, String n, Integer nc, Integer nf) {
        this.ID = id;
        this.nom = n;
        this.Num_Columnes = nc;
        this.Num_Files = nf;
        String f= "A";
        for (Integer i=0; i < Num_Files; ++i) {
            for (Integer j = 0; j < Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<String, Integer> idc = new AbstractMap.SimpleEntry<String, Integer>(f, j);
                Cela_Proba ce = new Cela_Proba(idc, null);
                this.Celes.put(idc, ce);
            }
            f= f+1;
        }
    };

    public Full(Integer id, Integer nc, Integer nf) {
        this.ID = id;
        this.nom = null;
        this.Num_Columnes = nc;
        this.Num_Files = nf;
        for (Integer i=0; i < Num_Files; ++i) {
            for (Integer j = 0; j < Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i, j);
                Cela_Proba ce = new Cela_Proba(idc, null);
                this.Celes.put(idc, ce);
            }
        }
    };

    //Setters
    public void SetNom(String n){
        this.nom= n;
    };

    //Mètodes Públics
    public void Afegir_Fila(Integer nf) {
        ++this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(nf,i);
            Cela_Proba ce = new Cela_Proba(idc, null);
            this.Celes.put(idc,ce);
            ++i;
        }
        if (nf < this.Num_Files-1) IncrementarIndexFila(nf);
    };

    public void Afegir_Columna(Integer nc) {
        ++this.Num_Columnes;
        Integer i= 0;
        while (i < this.Num_Files) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,nc);
            Cela_Proba ce = new Cela_Proba(idc, null);
            this.Celes.put(idc,ce);
            ++i;
        }
        if (nc < this.Num_Columnes-1) IncrementarIndexCol(nc);
    };

    public void Eliminar_Fila(Integer nf) {
        --this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(nf,i);
            this.Celes.remove(idc);
            ++i;
        }
        if (nf < this.Num_Files-1) DecrementarIndexFila(nf);
    };

    public void Eliminar_Columna(Integer nc) {
        --this.Num_Columnes;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,nc);
            this.Celes.remove(idc);
            ++i;
        }
        if (nc < this.Num_Columnes-1) DecrementarIndexCol(nc);
    };

    public void Ordenar_Fulla(ArrayList<Cela_Proba> celes, String cond) {
        //Swaps segons la cond i per contingut de la cela
    };

    public void Esborrar_Celes(ArrayList<Cela_Proba> celes) {
        Integer i= 0;
        while (i < celes.size()){
            AbstractMap.SimpleEntry<Integer, Integer> idc= celes.get(i).getId();
            this.Celes.get(idc).setContingut(null);
            ++i;
        }
    };

    public void Modifica_Cela(AbstractMap.SimpleEntry<Integer, Integer> id) {
    };

    public void Modifica_bloc_celes(ArrayList<Cela_Proba> celes) {
    };

    public void Consultar_cela(AbstractMap.SimpleEntry<Integer, Integer> id) {
    };

    public void Retrocedir() {
    };

    //Métodes Privats
    private void IncrementarIndexFila(Integer nf){

    };

    private void IncrementarIndexCol(Integer nc){

    };

    private void DecrementarIndexFila(Integer nf){

    };

    private void DecrementarIndexCol(Integer nc){

    };

    //Getters
    public Integer getID(){
        return ID;
    };

    public Integer getNum_Columnes() {

        return Num_Columnes;
    };

    public Integer getNum_Files() {

        return Num_Files;
    };
};



