package main.CapaDomini.Models;

import main.CapaPresentacio.inout;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;

public class Full {
    private String nom;
    private Integer Num_Columnes;
    private Integer Num_Files;
    private Cela[] Cel;
    private  HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela> Celes;
    private ArrayList<Cela> CelaULT;

    //Constructor
    public Full(String n, Integer nc, Integer nf) {
        Celes = new HashMap<>();
        this.nom = n;
        this.Num_Columnes = nc;
        this.Num_Files = nf;
        HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela> cel= new HashMap<>();
        for (Integer i=0; i < Num_Files; ++i) {
            for (Integer j = 0; j < Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(i, j);
                Celes.put(idc, new TextCela(idc, "."));
            }

        }
    };

    public Full(Integer nc, Integer nf) {
        this.nom = "Full sense nom";
        this.Num_Columnes = nc;
        this.Num_Files = nf;
        HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela> cel= new HashMap<>();
        for (Integer i=0; i < Num_Files; ++i) {
            for (Integer j = 0; j < Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(i, j);
                cel.put(idc, new TextCela(idc, "."));
            }
        }
        this.Celes= cel;
    };

    //Setters
    public void SetNom(String n){
        this.nom= n;
    };
    public String getNom(){return this.nom;}
    //Mètodes Públics
    public void Afegir_Fila(Integer nf) {
        if (nf <= this.Num_Files-1) //IncrementarIndexFila(nf);
        ++this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(nf,i);
            this.Celes.put(idc, new TextCela(idc, "."));
            ++i;
        }
    };

    public void Afegir_Columna(Integer nc) {
        if (nc <= this.Num_Columnes-1) IncrementarIndexCol(nc);
        ++this.Num_Columnes;
        Integer i= 0;
        while (i < this.Num_Files) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,nc);
            this.Celes.put(idc, new TextCela(idc, "."));
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

    public void Ordenar_Fulla(ArrayList<Cela> celes, String cond) {
        //enviar a controller la array + la cond
    };

    public void Esborrar_Celes(ArrayList<Cela> celes) {
        int i= 0;
        while (i < celes.size()){
            AbstractMap.SimpleEntry<Integer, Integer> idc= celes.get(i).getId();
            this.Celes.get(idc).setContingut("nocont");
            ++i;
        }
    };

    public void Modifica_Cela(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) {
        Cela c = this.Celes.get(id);
        c.setContingut(contingut);

        //S'ha de guardar la cela a algun lloc
    };

    public void Modifica_Tipus_Numeric(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String contingut = this.Celes.get(id).getContingut();
        this.Celes.replace(id, new Numero(id, contingut, true, 2, Tipus_Numero.numero));
        this.Celes.get(id).setType("numeric");
    }

    public void Modifica_bloc_celes(ArrayList<Cela> celes) {
        //Controller
    };

    public Cela Consultar_cela(AbstractMap.SimpleEntry<Integer, Integer> id) {
        return this.Celes.get(id);
    };

    public void Retrocedir(ArrayList<Cela> celes) {
        for (int i= 0; i < celes.size(); ++i){
            this.Celes.remove(celes.get(i).getId());
            this.Celes.put(celes.get(i).getId(), celes.get(i));
            celes.remove(i);
        }
    };

    public HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Cela> getCeles() throws Exception {
        return Celes;
    }

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


    public Integer getNum_Columnes() {

        return Num_Columnes;
    };

    public Integer getNum_Files() {

        return Num_Files;
    };
};



