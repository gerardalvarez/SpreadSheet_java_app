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
    private HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela_Proba> Celes;
    private Cela_Proba CelaULT;

    //Constructor
    public Full(Integer id, String n, Integer nc, Integer nf) {
        this.ID = id;
        this.nom = n;
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
        if (nf <= this.Num_Files-1) IncrementarIndexFila(nf);
        ++this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(nf,i);
            Cela_Proba ce = new Cela_Proba(idc, null);
            this.Celes.put(idc,ce);
            ++i;
        }
    };

    public void Afegir_Columna(Integer nc) {
        if (nc <= this.Num_Columnes-1) IncrementarIndexCol(nc);
        ++this.Num_Columnes;
        Integer i= 0;
        while (i < this.Num_Files) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,nc);
            Cela_Proba ce = new Cela_Proba(idc, null);
            this.Celes.put(idc,ce);
            ++i;
        }
    };

    public void Eliminar_Fila(Integer nf) {
        if (nf <= this.Num_Files-1) DecrementarIndexFila(nf);
        --this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(nf,i);
            this.Celes.remove(idc);
            ++i;
        }
    };

    public void Eliminar_Columna(Integer nc) {
        if (nc <= this.Num_Columnes-1) DecrementarIndexCol(nc);
        --this.Num_Columnes;
        Integer i= 0;
        while (i < this.Num_Files) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,nc);
            this.Celes.remove(idc);
            ++i;
        }
    };

/*
    public void Ordenar_Fulla(ArrayList<Cela_Proba> celes, String cond) {
        //Swaps segons la cond i per contingut de la cela
        if (cond == ">"){
            for (int i=1; i < celes.size(); ++i){
                if (celes.get(i-1).getContingut() < celes.get(i).getContingut()){
                    celes.get(i)
                }
            }
        }
        else if (cond == "<"){

        }
    };
*/

    public void Esborrar_Celes(ArrayList<Cela_Proba> celes) {
        Integer i= 0;
        while (i < celes.size()){
            AbstractMap.SimpleEntry<Integer, Integer> idc= celes.get(i).getId();
            this.Celes.get(idc).setContingut(null);
            ++i;
        }
    };

    public void Modifica_Cela(AbstractMap.SimpleEntry<Integer, Integer> id) {
        //Controller
        this.CelaULT= this.Celes.get(id);
    };

    public void Modifica_bloc_celes(ArrayList<Cela_Proba> celes) {
        //Controller
    };

    public String Consultar_cela(AbstractMap.SimpleEntry<Integer, Integer> id) {
        return this.Celes.get(id).getContingut();
    };

    public void Retrocedir() {
        AbstractMap.SimpleEntry<Integer, Integer> idc= this.CelaULT.getId();
        this.Celes.replace(idc, this.CelaULT);
    };

    //Métodes Privats
    private void IncrementarIndexFila(Integer nf){
        int i= nf;
        while (i < this.Num_Files) {
            for (int j= 0; j < this.Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,j);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(i+1,j);
                this.Celes.get(idc).setId(idc2);
            }
            ++i;
        }
    };

    private void IncrementarIndexCol(Integer nc){
        int i= nc;
        while (i < this.Num_Columnes) {
            for (int j= 0; j < this.Num_Files; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(j,i);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(j,i+1);
                this.Celes.get(idc).setId(idc2);
            }
            ++i;
        }
    };

    private void DecrementarIndexFila(Integer nf){
        int i= nf;
        while (i < this.Num_Files) {
            for (int j= 0; j < this.Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,j);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(i-1,j);
                this.Celes.get(idc).setId(idc2);
            }
            ++i;
        }
    };

    private void DecrementarIndexCol(Integer nc){
        int i= nc;
        while (i < this.Num_Columnes) {
            for (int j= 0; j < this.Num_Files; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(j,i);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(j,i-1);
                this.Celes.get(idc).setId(idc2);
            }
            ++i;
        }
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



