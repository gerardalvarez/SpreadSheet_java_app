package main.CapaDomini.Models;


import java.awt.*;
import java.math.BigDecimal;
import java.util.*;

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
                Celes.put(idc, new TextCela(idc, ""));
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
                cel.put(idc, new TextCela(idc, ""));
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
        if (nf <= this.Num_Files-1) IncrementarIndexFila(nf);
        ++this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(nf,i);
            Celes.put(idc, new TextCela(idc, ""));
            ++i;
        }
    };

    public void Afegir_Columna(Integer nc) {
        if (nc <= this.Num_Columnes-1) IncrementarIndexCol(nc);
        ++this.Num_Columnes;
        Integer i= 0;
        while (i < this.Num_Files) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,nc);
            Celes.put(idc, new TextCela(idc, ""));
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

    public void Esborrar_Celes(ArrayList<Cela> celes) {
        int i= 0;
        while (i < celes.size()){
            AbstractMap.SimpleEntry<Integer, Integer> idc= celes.get(i).getId();
            this.Celes.get(idc).setResultatFinal("");
            ++i;
        }
    };

    public void Modifica_Cela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) {
        int size = resultat.length();
        String a = PublicFuntions.calculaTipus(resultat);
        if(Objects.equals(a, "numeric")){
            if(Objects.equals(this.Celes.get(id).getType(), "numeric")){
                this.Celes.get(id).setResultatFinal(resultat);
            }
            else{
                Cela cel = this.Celes.get(id);
                Color colorFons = cel.getColorFons();
                Color colorLletra = cel.getColorLletra();
                String type = cel.getType();
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new Numero(id, new BigDecimal(resultat), true, 2, Tipus_Numero.numero));
                this.Celes.get(id).setType(type);
                this.Celes.get(id).setColorFons(colorFons);
                this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
        }
        else if(Objects.equals(a, "date")){
            Cela cel = this.Celes.get(id);
            Color colorFons = cel.getColorFons();
            Color colorLletra = cel.getColorLletra();
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
            this.Celes.replace(id, new DataCela(id, resultat));
            this.Celes.get(id).setType("date");
            this.Celes.get(id).setColorFons(colorFons);
            this.Celes.get(id).setColorLletra(colorLletra);
            this.Celes.get(id).setObservadors(observadors);
        }
        else if(size == 4 && resultat.startsWith("=#")  && isNumerical(resultat.substring(2))){
            Integer fil = Integer.parseInt(resultat.substring(2,3));
            Integer col = Integer.parseInt(resultat.substring(3));
            AbstractMap.SimpleEntry<Integer, Integer> he = new AbstractMap.SimpleEntry<Integer,Integer>(fil,col);
            if(Objects.equals(this.Celes.get(he).getType(), "date")){
                //CREAR CELA DATA REF
            }
            else if(Objects.equals(this.Celes.get(he).getType(), "text")){
                //CREAR  CELA TEXT REF
            }
            else{
                //CREAR NUM CELA REF
            }
            this.Celes.get(he).newObserver(id);
        }
        else if(size > 4 && resultat.charAt(0) == '='){
            String func = PublicFuntions.getOper(resultat.substring(1,4));
            if(func.equals("MAY") || func.equals("MIN")){
                //CREAR FUNCION TEXT;
            }
            else if(!func.equals("NULL")){
                //CREAR FUNCION NUM
            }
            //MIRAR OBSERVERS I PONERLOS EN LA CELDA QUE TOCA
            else{
                if(Objects.equals(this.Celes.get(id).getType(), "text")){
                    this.Celes.get(id).setResultatFinal(resultat);
                }
                else{
                    Cela cel = this.Celes.get(id);
                    Color colorFons = cel.getColorFons();Color colorLletra = cel.getColorLletra();
                    String type = cel.getType();ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                    this.Celes.replace(id, new TextCela(id, resultat));
                    this.Celes.get(id).setType(type);
                    this.Celes.get(id).setColorFons(colorFons);this.Celes.get(id).setColorLletra(colorLletra);
                    this.Celes.get(id).setObservadors(observadors);
                }
            }
        }
        else {
            if(Objects.equals(this.Celes.get(id).getType(), "text")){
                this.Celes.get(id).setResultatFinal(resultat);
            }
            else{
                Cela cel = this.Celes.get(id);
                Color colorFons = cel.getColorFons();Color colorLletra = cel.getColorLletra();
                String type = cel.getType();ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new TextCela(id, resultat));
                this.Celes.get(id).setType(type);
                this.Celes.get(id).setColorFons(colorFons);this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
        }
        //AQUI HAY QUE ACTUALIZAR TODOS LOS OBSERVERS DE LA NUEVA CELDA SI ESTA CANVIA DE TIPO TAMBIEN HABRIA QUE TENERLO
        //EN CUENTA
    }



    public void Modifica_Tipus_Numeric(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String resultat = this.Celes.get(id).getResultatFinal();
        this.Celes.replace(id, new Numero(id, new BigDecimal(resultat), true, 2, Tipus_Numero.numero));
        this.Celes.get(id).setType("numeric");
    }

    public void Modifica_Tipus_Text(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String contingut = this.Celes.get(id).getResultatFinal();
        this.Celes.replace(id, new TextCela(id, contingut));
        this.Celes.get(id).setType("text");
    }

    public void Modifica_Tipus_Data(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String contingut = this.Celes.get(id).getResultatFinal();
        this.Celes.replace(id, new DataCela(id, contingut));
        this.Celes.get(id).setType("data");
    }

    public void Modifica_Numero_Tipus(AbstractMap.SimpleEntry<Integer, Integer> id, String Type) {
        Cela c = this.Celes.get(id);
        Numero n = (Numero) c;
        n.setTipus(Tipus_Numero.valueOf(Type));
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

    public ArrayList<Numero> getBlocNumero(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = GetIdCeles(id1, id2);
        ArrayList<Numero> num = new ArrayList<>();

        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            Cela c = this.Celes.get(id);
            Numero n = (Numero) c;
            num.add(n);
        }

        return num;
    }

    public ArrayList<DataCela> getBlocData(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = GetIdCeles(id1, id2);
        ArrayList<DataCela> data = new ArrayList<>();

        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            Cela c = this.Celes.get(id);
            DataCela d = (DataCela) c;
            data.add(d);
        }

        return data;
    }

    public ArrayList<TextCela> getBlocText(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = GetIdCeles(id1, id2);
        ArrayList<TextCela> text = new ArrayList<>();

        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            Cela c = this.Celes.get(id);
            TextCela t = (TextCela) c;
            text.add(t);
        }

        return text;
    }

    public ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> GetIdCeles(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = new ArrayList<>();
        boolean found = false;
        Integer i = id1.getKey();
        Integer j = id1.getValue();
        while(!found) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(i,j);
            ids.add(idc);
            if(idc.equals(id2)) found = true;
            if(j <= id2.getValue()) {
                j++;
            }
            else {
                j = id1.getValue();
                i++;
            }
        }
        return ids;
    }

    private Boolean isNumerical(String strNum){
        if (strNum == null) return false;

        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean ExisteixId(AbstractMap.SimpleEntry<Integer, Integer> id) {
        return this.Celes.containsKey(id);
    }
};



