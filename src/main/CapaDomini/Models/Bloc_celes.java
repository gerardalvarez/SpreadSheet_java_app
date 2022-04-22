package main.CapaDomini.Models;


import java.util.AbstractMap;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.*;


public class Bloc_celes {

    public Bloc_celes(){}


    /////////
    //Funciones de ordenar que devuelven una matriz con nuevas celdas creadas (diferentes ref a las pasadas) ordenadas
    //
    /////////

    public Cela[][] ordena_A_Z_matrix(Cela arr[][],ArrayList<Integer> cols) {

        Cela[][] aux = new Cela[arr.length][arr[0].length];
        AbstractMap.SimpleEntry<Integer,Integer>[][] auxindex = new AbstractMap.SimpleEntry[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] instanceof Numero){
                    aux[i][j] = new Numero(((Numero) arr[i][j]).getId(), ((Numero) arr[i][j]).getResultat().toString(),((Numero) arr[i][j]).getArrodonit(),((Numero) arr[i][j]).getNum_Decimals(),((Numero) arr[i][j]).getTipus());
                }
                else if(arr[i][j] instanceof TextCela){
                    aux[i][j] = new TextCela(((TextCela) arr[i][j]).getId(),((TextCela) arr[i][j]).getResultatFinal());
                }
                auxindex[i][j]=arr[i][j].getId();
            }
        }
        Arrays.sort(aux, new Comparator<Cela[]>() {

            @Override
            // Compare values according to columns
            public int compare(final Cela[] entry1, final Cela[] entry2) {
                int i=0;
                while(i<cols.size()-1){
                    if (!entry1[cols.get(i)].getResultatFinal().equals(entry2[cols.get(i)].getResultatFinal())) break;
                    i++;
                }
                return entry1[cols.get(i)].getResultatFinal().compareTo(entry2[cols.get(i)].getResultatFinal());
            }
        });

        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
                if(aux[i][j] instanceof Numero){
                    aux[i][j].setId(auxindex[i][j]);
                }
                else if(aux[i][j] instanceof TextCela){
                    aux[i][j].setId(auxindex[i][j]);
                }
            }
        }
        return aux;
    }

    public Cela[][] ordena_Z_A_matrix(Cela arr[][],ArrayList<Integer> cols) {


        Cela[][] aux = new Cela[arr.length][arr[0].length];
        AbstractMap.SimpleEntry<Integer,Integer>[][] auxindex = new AbstractMap.SimpleEntry[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] instanceof Numero){
                    aux[i][j] = new Numero(((Numero) arr[i][j]).getId(), ((Numero) arr[i][j]).getResultat().toString(),((Numero) arr[i][j]).getArrodonit(),((Numero) arr[i][j]).getNum_Decimals(),((Numero) arr[i][j]).getTipus());
                }
                else if(arr[i][j] instanceof TextCela){
                    aux[i][j] = new TextCela(((TextCela) arr[i][j]).getId(),((TextCela) arr[i][j]).getResultatFinal());
                }
                auxindex[i][j]=arr[i][j].getId();
            }
        }
        Arrays.sort(aux, new Comparator<Cela[]>() {

            @Override
            // Compare values according to columns
            public int compare(final Cela[] entry1, final Cela[] entry2) {
                int i=0;
                while(i<cols.size()-1){
                    if (!entry2[cols.get(i)].getResultatFinal().equals(entry1[cols.get(i)].getResultatFinal())) break;
                    i++;
                }
                return entry2[cols.get(i)].getResultatFinal().compareTo(entry1[cols.get(i)].getResultatFinal());
            }
        });

        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
                if(aux[i][j] instanceof Numero){
                    aux[i][j].setId(auxindex[i][j]);
                }
                else if(aux[i][j] instanceof TextCela){
                    aux[i][j].setId(auxindex[i][j]);
                }
            }
        }
        return aux;
    }


    /////////
    //Funciones de ordenar que devuelven una matriz con nuevas celdas creadas (diferentes ref a las pasadas) ordenadas
    /////////

    public void ordena_A_Z(Cela arr[][],ArrayList<Integer> cols) {

        AbstractMap.SimpleEntry<Long,Cela>[][] aux = new AbstractMap.SimpleEntry[arr.length][arr[0].length];
        AbstractMap.SimpleEntry<Integer,Integer>[][] auxindex = new AbstractMap.SimpleEntry[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] instanceof Numero){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new Numero(((Numero) arr[i][j]).getId(), ((Numero) arr[i][j]).getResultat().toString()));
                }
                else if(arr[i][j] instanceof TextCela){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new TextCela(((TextCela) arr[i][j]).getId(),((TextCela) arr[i][j]).getResultatFinal()));
                }
                auxindex[i][j]=arr[i][j].getId();
            }
        }
        Arrays.sort(aux, new Comparator< AbstractMap.SimpleEntry<Long,Cela>[]>() {

            @Override
            // Compare values according to columns
            public int compare(final  AbstractMap.SimpleEntry<Long,Cela>[] entry1, final  AbstractMap.SimpleEntry<Long,Cela>[] entry2) {
                int i=0;
                while(i<cols.size()-1){
                    if (!entry1[cols.get(i)].getValue().getResultatFinal().equals(entry2[cols.get(i)].getValue().getResultatFinal())) break;
                    i++;
                }
                return entry1[cols.get(i)].getValue().getResultatFinal().compareTo(entry2[cols.get(i)].getValue().getResultatFinal());
            }
        });

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++){
                for (int ii = 0; ii < aux.length; ii++) {
                    for (int jj = 0; jj < aux[0].length; jj++){
                        if (aux[ii][jj].getKey()==uniqueId(i,j)) {
                            arr[i][j].setId(auxindex[ii][jj]);
                            break;
                        }
                    }
                }
            }
        }
    }


    public void ordena_Z_A(Cela arr[][],ArrayList<Integer> cols) {

        AbstractMap.SimpleEntry<Long,Cela>[][] aux = new AbstractMap.SimpleEntry[arr.length][arr[0].length];
        AbstractMap.SimpleEntry<Integer,Integer>[][] auxindex = new AbstractMap.SimpleEntry[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] instanceof Numero){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new Numero(((Numero) arr[i][j]).getId(), ((Numero) arr[i][j]).getResultat().toString()));
                }
                else if(arr[i][j] instanceof TextCela){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new TextCela(((TextCela) arr[i][j]).getId(),((TextCela) arr[i][j]).getResultatFinal()));
                }
                auxindex[i][j]=arr[i][j].getId();
            }
        }
        Arrays.sort(aux, new Comparator< AbstractMap.SimpleEntry<Long,Cela>[]>() {

            @Override
            // Compare values according to columns
            public int compare(final  AbstractMap.SimpleEntry<Long,Cela>[] entry1, final  AbstractMap.SimpleEntry<Long,Cela>[] entry2) {
                int i=0;
                while(i<cols.size()-1){
                    if (!entry2[cols.get(i)].getValue().getResultatFinal().equals(entry1[cols.get(i)].getValue().getResultatFinal())) break;
                    i++;
                }
                return entry2[cols.get(i)].getValue().getResultatFinal().compareTo(entry1[cols.get(i)].getValue().getResultatFinal());
            }
        });
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++){
                for (int ii = 0; ii < aux.length; ii++) {
                    for (int jj = 0; jj < aux[0].length; jj++){
                        if (aux[ii][jj].getKey()==uniqueId(i,j)) {
                            arr[i][j].setId(auxindex[ii][jj]);
                            break;
                        }
                    }
                }
            }
        }
    }


    public double calculaMitjana(ArrayList<Numero> inputs) {
        double res=0;
        for(Numero c : inputs){
            res+= c.getResultat().doubleValue();
        }
        return res/ inputs.size();
    }

    public double calculaMediana(ArrayList<Numero> inputs) {
        //sort array
        Collections.sort(inputs, new Comparator<Numero>() {
            @Override
            public int compare(Numero c1, Numero c2) {
                return c1.getResultat().compareTo(c2.getResultat());
            }
        });
        double resultat;
        if(inputs.size() % 2 == 0){
            Double sumaMedios = inputs.get(inputs.size()/2).getResultat().doubleValue() + inputs.get((inputs.size()/2)-1).getResultat().doubleValue();
            resultat = (double)sumaMedios / 2;
        } else {
            resultat = inputs.get(inputs.size()/2).getResultat().doubleValue();
        }
        return resultat;
    }



    public double calculaModa(ArrayList<Numero> inputs) {

        HashMap<Double, Double> mapa = new HashMap<>();
        for (Numero c : inputs) {
            Double n = c.getResultat().doubleValue();
            if (mapa.containsKey(n)) {
                mapa.put(n, mapa.get(n) + 1);
            } else {
                mapa.put(n, Double.valueOf(1));
            }
        }
        Double moda = Double.valueOf(0), mayor = Double.valueOf(0);
        for (HashMap.Entry<Double, Double> entry : mapa.entrySet()) {
            if (entry.getValue() > mayor) {
                mayor = entry.getValue();
                moda = entry.getKey();
            }
        }
        return moda;
    }


    public double calculaVariança(ArrayList<Numero> inputs) {
        double media = this.calculaMitjana(inputs);
        double dVar = 0;
        double acMedia = 0, acMedia2 = 0;
        int n = 0;
        // Encuentra la varianza
        for (Numero c : inputs) {
            acMedia = acMedia + c.getResultat().doubleValue();
            acMedia2 = acMedia2 + c.getResultat().doubleValue() * c.getResultat().doubleValue();
            n++;
        }
        double varianza = acMedia2/(n-1) - (acMedia * acMedia) / (n * (n-1));
        return varianza;


    }

    public double calculaDesviació(ArrayList<Numero> inputs) {
        return Math.sqrt(this.calculaVariança(inputs));
    }

  /*  public void copiar_contingut(Cela org[][], Cela dest[][]) {
        for (int i = 0; i < org.length; i++) {
            for (int j = 0; j < org[i].length; j++)
                dest[i][j].copiaCela(org[i][j]);
        }
    }*/
/*
    public void moure_contingut(Cela org[][], Cela dest[][]) {
        for (int i = 0; i < org.length; i++) {
            for (int j = 0; j < org[i].length; j++) {
                dest[i][j].copiaCela(org[i][j]);
                org[i][j]=new Cela(org[i][j].getId(),org[i][j].getContingut()) ;
            }
        }
    }*/

    public void remplaçar_majuscules(ArrayList<TextCela> inputs) {
        for (TextCela c: inputs){
            c.AllMayus();
        }
    }

    public void remplaçar_minuscules(ArrayList<TextCela> inputs) {
        for (TextCela c : inputs) {
            c.AllMinus();
        }
    }



            //UTILS UTILIZADOS EN LAS CLASES

    class Pair
    {
        // Return a map entry (key-value pair) from the specified values
        public <T, U> Map.Entry<T, U> of(T first, U second) {
            return new AbstractMap.SimpleEntry<>(first, second);
        }
    }

    private long uniqueId(int left, int right) {
        long uniqueId = (long) left;
        uniqueId = uniqueId << 32 << 16;
        uniqueId += (long) right;
        return uniqueId;
    }
}

