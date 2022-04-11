package main.CapaDomini.Models;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;


public class Full {
    private Integer ID;
    private String nom;
    private Integer Num_Columnes;
    private Integer Num_Files;
    private Cela_Proba[] Cela;
    private ArrayList<Cela_Proba> Celes;

    //Constructor
    public Full(Integer id, String n, Integer nc, Integer nf) {
        ID = id;
        nom = n;
        Num_Columnes = nc;
        Num_Files = nf;
    };

    public Full(Integer id, Integer nc, Integer nf) {
        ID = id;
        nom = null;
        Num_Columnes = nc;
        Num_Files = nf;
    };

    //Setters
    public void SetNom(String n){
        this.nom= n;
    };

    //Mètodes
    public void Afegir_Fila() {
        ++this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            //add new Cela_Prova<nfiles-1,i>
        }
    };

    public void Afegir_Columna() {
        ++this.Num_Columnes;
        Integer i= 0;
        while (i < this.Num_Files) {
            //add new Cela_Prova<i,ncolum-1>
        }
    };

    public void Eliminar_Fila() {
        --this.Num_Files;
    };

    public void Eliminar_Columna() {
        --this.Num_Columnes;
    };

    public void Ordenar_Fulla() {
    };

    public void Esborrar_Celes(ArrayList<Cela_Proba> celes) {
        Integer i= 0;
        while (i < celes.size()){
            Integer j= 0;
            while (j < this.Celes.size()){
                if (this.Celes.get(j).getId() == celes.get(i).getId()) {
                    this.Celes.remove(j);
                }
                ++j;
            }
            ++i;
        }
    };

    public void Modifica_Cela(AbstractMap<Integer, Integer> id) {
    };

    public void Modifica_bloc_celes(ArrayList<Cela_Proba> celes) {

    };

    public String Consultar_cela(AbstractMap<Integer, Integer> id) {
        String cont = null;
        boolean t= false;
        Integer i= 0;
        while (i < this.Celes.size() && !t){
            if (this.Celes.get(i).getId() == id) {
                t = true;
                cont = this.Celes.get(i).getContingut();
            }
        }
        return cont;
    };

    public void Retrocedir() {
    };

    //Getters
    public Integer getID(){
        return ID;
    }

    public Integer getNum_Columnes() {

        return Num_Columnes;
    }

    public Integer getNum_Files() {

        return Num_Files;
    }
};



