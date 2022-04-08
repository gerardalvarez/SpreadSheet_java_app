package main.CapaDomini.Models;

import java.util.ArrayList;

public class Full {
    private Integer ID;
    private String nom;
    private Integer Num_Columnes;
    private Integer Num_Files;
    private Cela[] Cela;
    private ArrayList<ArrayList<Cela>> Celes;

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
    };

    public void Afegir_Columna() {
    };

    public void Eliminar_Fila() {
    };

    public void Eliminar_Columna() {
    };

    public void Ordenar_Fulla() {
    };

    public void Esborrar_Celles() {
    };

    public void Modifica_Cella() {
    };

    public void Modifica_bloc_celles() {
    };

    public void Consultar_cella() {
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



