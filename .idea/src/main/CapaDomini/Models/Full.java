package main.CapaDomini.Models;

public class Full {
    int ID;
    String nom;
    int Num_Columnes;
    int Num_Files;

    public Full(int id, String n, int nc, int nf) {
        ID = id;
        nom = n;
        Num_Columnes = nc;
        Num_Files = nf;
    };

    public Full(int id, int nc, int nf) {
        ID = id;
        nom = null;
        Num_Columnes = nc;
        Num_Files = nf;
    };

    public void SetNom(String n){
        this.nom= n;
    };

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

};



