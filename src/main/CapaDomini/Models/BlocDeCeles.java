package main.CapaDomini.Models;


import java.text.DecimalFormat;
import java.util.*;
import main.CapaDomini.Models.*;

public class BlocDeCeles {

    public BlocDeCeles(){}

    public void ordena_A_Z() {

    }

    public void ordena_Z_A() {

    }

    public double calculaMitjana(ArrayList<Cela> inputs) {
        double res=0;
        for(Cela c : inputs){
            res+= c.get();
        }
        return res/inputs.size();

    }

    public double calculaMediana(ArrayList<Cela> inputs) {
        //sort array
        Collections.sort(inputs, new Comparator<Cela>() {
            @Override
            public int compare(Cela c1, Cela c2) {
                return c1.get().compareTo(c2.get());
            }
        });

        double resultat;
        if(inputs.size() % 2 == 0){
            int sumaMedios = inputs.get(inputs.size()/2).get() + inputs.get(inputs.size()/2).get();
            resultat = (double)sumaMedios / 2;
        } else {
            resultat = inputs.get(inputs.size()/2).get();
        }
        return resultat;
    }


    public double calculaModa(ArrayList<Cela> inputs) {

        HashMap<Integer, Integer> mapa = new HashMap<>();
        for (Cela c : inputs) {
            Integer n = c.get();
            if (mapa.containsKey(n)) {
                mapa.put(n, mapa.get(n) + 1);
            } else {
                mapa.put(n, 1);
            }
        }
        Integer moda = 0, mayor = 0;
        for (HashMap.Entry<Integer, Integer> entry : mapa.entrySet()) {
            if (entry.getValue() > mayor) {
                mayor = entry.getValue();
                moda = entry.getKey();
            }
        }
        return moda;
    }


    public double calculaVariança(ArrayList<Cela> inputs) {
        double media = this.calculaMitjana(inputs);
        double dVar = 0;
        // Encuentra la varianza
        for (Cela c : inputs) {
            dVar += (c.get() - media) * (c.get() - media);
        }
        return dVar /inputs.size();


    }

    public double calculaDesviació(ArrayList<Cela> inputs) {
        return Math.sqrt(this.calculaVariança(inputs));
    }

    public void copiar_contingut() {

    }

    public void moure_contingut() {

    }

    public void remplaçar_majuscules(ArrayList<Cela> inputs) {
        for (Cela c: inputs){
            c.remplaçarmaj();
        }
    }

    public void remplaçar_minuscules(ArrayList<Cela> inputs) {
        for (Cela c: inputs){
            c.remplaçarmin();
        }
    }

    public void mesura_longituds() {

    }


}
