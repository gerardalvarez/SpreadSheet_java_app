package main.CapaDomini.Models;


import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Map;
import java.util.*;


public class Bloc_celes {

    public Bloc_celes() {
    }


    /////////
    //Funciones de ordenar que devuelven una matriz con nuevas celdas creadas (diferentes ref a las pasadas) ordenadas
    //
    /////////

    public Cela[][] ordena_A_Z_matrix(Cela[][] arr, ArrayList<Integer> cols) {

        Cela[][] aux = new Cela[arr.length][arr[0].length];
        AbstractMap.SimpleEntry<Integer, Integer>[][] auxindex = new AbstractMap.SimpleEntry[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] instanceof Numero) {
                    aux[i][j] = new Numero(((Numero) arr[i][j]).getId(), ((Numero) arr[i][j]).getResultat(), ((Numero) arr[i][j]).getArrodonit(), ((Numero) arr[i][j]).getNum_Decimals(), ((Numero) arr[i][j]).getTipus());
                } else if (arr[i][j] instanceof TextCela) {
                    aux[i][j] = new TextCela(((TextCela) arr[i][j]).getId(), ((TextCela) arr[i][j]).getResultatFinal());
                } else if (arr[i][j] instanceof CelaRefNum) {
                    aux[i][j] = new CelaRefNum((CelaRefNum) arr[i][j], arr[i][j].getId());

                } else if (arr[i][j] instanceof CelaRefText) {
                    aux[i][j] = new CelaRefText((CelaRefText) arr[i][j], arr[i][j].getId());

                } else if (arr[i][j] instanceof CelaRefData) {
                    aux[i][j] = new CelaRefData((CelaRefData) arr[i][j], arr[i][j].getId());
                }
                auxindex[i][j] = arr[i][j].getId();
            }
        }
        Arrays.sort(aux, new Comparator<Cela[]>() {

            @Override
            // Compare values according to columns
            public int compare(final Cela[] entry1, final Cela[] entry2) {
                int i = 0;
                while (i < cols.size() - 1) {
                    if (!entry1[cols.get(i)].getResultatFinal().equals(entry2[cols.get(i)].getResultatFinal())) break;
                    i++;
                }
                return entry1[cols.get(i)].getResultatFinal().compareTo(entry2[cols.get(i)].getResultatFinal());
            }
        });

        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
                if (aux[i][j] instanceof Numero) {
                    aux[i][j].setId(auxindex[i][j]);
                } else if (aux[i][j] instanceof TextCela) {
                    aux[i][j].setId(auxindex[i][j]);
                }
            }
        }
        return aux;
    }

    public Cela[][] ordena_Z_A_matrix(Cela arr[][], ArrayList<Integer> cols) {


        Cela[][] aux = new Cela[arr.length][arr[0].length];
        AbstractMap.SimpleEntry<Integer, Integer>[][] auxindex = new AbstractMap.SimpleEntry[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] instanceof Numero) {
                    aux[i][j] = new Numero(((Numero) arr[i][j]).getId(), ((Numero) arr[i][j]).getResultat(), ((Numero) arr[i][j]).getArrodonit(), ((Numero) arr[i][j]).getNum_Decimals(), ((Numero) arr[i][j]).getTipus());
                } else if (arr[i][j] instanceof TextCela) {
                    aux[i][j] = new TextCela(((TextCela) arr[i][j]).getId(), ((TextCela) arr[i][j]).getResultatFinal());
                } else if (arr[i][j] instanceof CelaRefNum) {
                    aux[i][j] = new CelaRefNum((CelaRefNum) arr[i][j], arr[i][j].getId());

                } else if (arr[i][j] instanceof CelaRefText) {
                    aux[i][j] = new CelaRefText((CelaRefText) arr[i][j], arr[i][j].getId());

                } else if (arr[i][j] instanceof CelaRefData) {
                    aux[i][j] = new CelaRefData((CelaRefData) arr[i][j], arr[i][j].getId());
                }
                auxindex[i][j] = arr[i][j].getId();
            }
        }
        Arrays.sort(aux, new Comparator<Cela[]>() {

            @Override
            // Compare values according to columns
            public int compare(final Cela[] entry1, final Cela[] entry2) {
                int i = 0;
                while (i < cols.size() - 1) {
                    if (!entry2[cols.get(i)].getResultatFinal().equals(entry1[cols.get(i)].getResultatFinal())) break;
                    i++;
                }
                return entry2[cols.get(i)].getResultatFinal().compareTo(entry1[cols.get(i)].getResultatFinal());
            }
        });

        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux[i].length; j++) {
                if (aux[i][j] instanceof Numero) {
                    aux[i][j].setId(auxindex[i][j]);
                } else if (aux[i][j] instanceof TextCela) {
                    aux[i][j].setId(auxindex[i][j]);
                }
            }
        }
        return aux;
    }


    /////////
    //Funciones de ordenar que devuelven una matriz con las celdas antiguas con los id cambiados a ordenado
    /////////

    public void ordena_A_Z(Cela arr[][], ArrayList<Integer> cols) {

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
                else if(arr[i][j] instanceof DataCela){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new DataCela(((DataCela) arr[i][j]).getId(),((DataCela) arr[i][j]).getResultatFinal()));
                }
                //Aunque son ref solo ordena por resultadofinal asi que con celas textoauxiliares vale
                else if(arr[i][j] instanceof CelaRefNum){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new TextCela(((TextCela) arr[i][j]).getId(),((TextCela) arr[i][j]).getResultatFinal()));
                }
                else if(arr[i][j] instanceof CelaRefText){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new TextCela(((TextCela) arr[i][j]).getId(),((TextCela) arr[i][j]).getResultatFinal()));
                }
                else if(arr[i][j] instanceof CelaRefData){
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
                String a1="";
                String a2="";
                while(i<cols.size()-1){
                    a1=entry1[cols.get(i)].getValue().getResultatFinal();
                    a2=entry2[cols.get(i)].getValue().getResultatFinal();
                    if (!a1.equals(a2)) break;
                    i++;
                }
                a1=entry1[cols.get(i)].getValue().getResultatFinal();
                a2=entry2[cols.get(i)].getValue().getResultatFinal();
                Double aa,ab;
                try {
                    aa=Double.parseDouble(a1);
                    ab=Double.parseDouble(a2);
                }catch (NumberFormatException e){
                    System.out.println("a");
                    return a2.compareTo(a1);
                }
                System.out.println(a1+" "+a2+" "+a1.compareTo(a2));
                return Double.compare(aa,ab);
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


    public void ordena_Z_A(Cela arr[][], ArrayList<Integer> cols) {

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
                else if(arr[i][j] instanceof DataCela){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new DataCela(((DataCela) arr[i][j]).getId(),((DataCela) arr[i][j]).getResultatFinal()));
                }
                //Aunque son ref solo ordena por resultadofinal asi que con celas textoauxiliares vale
                else if(arr[i][j] instanceof CelaRefNum){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new TextCela(((TextCela) arr[i][j]).getId(),((TextCela) arr[i][j]).getResultatFinal()));
                }
                else if(arr[i][j] instanceof CelaRefText){
                    aux[i][j] = new AbstractMap.SimpleEntry(uniqueId(i,j),new TextCela(((TextCela) arr[i][j]).getId(),((TextCela) arr[i][j]).getResultatFinal()));
                }
                else if(arr[i][j] instanceof CelaRefData){
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
                String a1="";
                String a2="";
                while(i<cols.size()-1){
                    a1=entry2[cols.get(i)].getValue().getResultatFinal();
                    a2=entry1[cols.get(i)].getValue().getResultatFinal();
                    if (!a1.equals(a2)) break;
                    i++;
                }
                a1=entry2[cols.get(i)].getValue().getResultatFinal();
                a2=entry1[cols.get(i)].getValue().getResultatFinal();

                Double aa,ab;
                try {
                    aa=Double.parseDouble(a1);
                    ab=Double.parseDouble(a2);
                }catch (NumberFormatException e){
                    System.out.println("a");
                    return a2.toLowerCase(Locale.ROOT).compareTo(a1.toLowerCase());
                }
                System.out.println(a1+" "+a2+" "+a1.compareTo(a2));
                return Double.compare(aa,ab);
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
        for(Cela c : inputs){
            res+= Double.parseDouble(c.getResultatFinal());
        }
        if(inputs.size()==0)return res;
        return res/ inputs.size();
    }

    public double calculaMediana(ArrayList<Numero> inputs) {
        //sort array
        Collections.sort(inputs, new Comparator<Cela>() {
            @Override
            public int compare(Cela c1, Cela c2) {
                return c1.getResultatFinal().compareTo(c2.getResultatFinal());
            }
        });
        double resultat;
        if(inputs.size()==0)return 0.0;
        if(inputs.size() % 2 == 0){
            Double sumaMedios = Double.parseDouble(inputs.get(inputs.size()/2).getResultatFinal()) + Double.parseDouble(inputs.get((inputs.size()/2)-1).getResultatFinal());
            resultat = (double)sumaMedios / 2;
        } else {
            resultat = Double.parseDouble(inputs.get(inputs.size()/2).getResultatFinal());
        }
        return resultat;
    }



    public double calculaModa(ArrayList<Numero> inputs) {

        HashMap<Double, Double> mapa = new HashMap<>();
        for (Cela c : inputs) {
            Double n = Double.parseDouble(c.getResultatFinal());
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
        if(inputs.size()==0)return 0.0;
        return moda;
    }


    public double calculaVariança(ArrayList<Numero> inputs) {
        //double media = this.calculaMitjana(inputs);
        double dVar = 0;
        double acMedia = 0, acMedia2 = 0;
        int n = 0;
        // Encuentra la varianza
        for (Cela c : inputs) {
            acMedia = acMedia + Double.parseDouble(c.getResultatFinal());
            acMedia2 = acMedia2 + Double.parseDouble(c.getResultatFinal()) * Double.parseDouble(c.getResultatFinal());
            n++;
        }
        double varianza =0.0;
        if (inputs.size()<2 ) return varianza;
        varianza = acMedia2/(n-1) - (acMedia * acMedia) / (n * (n-1));
        return varianza;

    }

    public double maxim(ArrayList<Numero> inputs){
        double max=0.0;
        for (Cela c : inputs) {
            double aux=Double.parseDouble(c.getResultatFinal());
            if(aux>max) max=aux;
        }
        return max;
    }

    public double calculaDesviació(ArrayList<Numero> inputs) {
        return Math.sqrt(this.calculaVariança(inputs));
    }




    public void copiar_contingut(Cela[][] org, Cela[][] dest) {
        for (int i = 0; i < org.length; i++) {
            for (int j = 0; j < org[i].length; j++) {
                Cela cd = dest[i][j];
                Cela dd = org[i][j];
                if (org[i][j] instanceof CelaRefNum) {
                    System.out.println(((CelaRefNum) dd).getContingut());
                    dest[i][j] = new TextCela(cd.getId(),((CelaRefNum) dd).getContingut());

                } else if (org[i][j] instanceof CelaRefText) {
                    dest[i][j] = new TextCela(cd.getId(),((CelaRefText) dd).getContingut());

                } else if (org[i][j] instanceof CelaRefData) {
                    dest[i][j] = new TextCela(cd.getId(),((CelaRefData) dd).getContingut());
                }else if (org[i][j] instanceof Numero) {
                    System.out.println("a");
                    dest[i][j] = new Numero((Numero) dd, cd.getId());
                } else if (org[i][j] instanceof TextCela) {
                    dest[i][j] = new TextCela((TextCela) dd, cd.getId());
                } else if (org[i][j] instanceof DataCela) {
                    dest[i][j] = new DataCela((DataCela) dd, cd.getId());

                }
            }
        }
    }

    public void moure_contingut(Cela org[][], Cela dest[][]) {
        for (int i = 0; i < org.length; i++) {
            for (int j = 0; j < org[i].length; j++){
                Cela cd=dest[i][j];
                Cela dd=org[i][j];
                if(org[i][j] instanceof Numero){
                    cd = new Numero((Numero) dd,cd.getId());
                    dd= new TextCela(dd.getId(),"");
                }
                else if(org[i][j] instanceof TextCela){
                    cd= new TextCela((TextCela)dd,cd.getId());
                    dd=new TextCela(dd.getId(),"");
                }
                else if(org[i][j] instanceof DataCela){
                    cd= new DataCela((DataCela)dd,cd.getId());
                    dd=new TextCela(dd.getId(),"");

                }else if (org[i][j] instanceof CelaRefNum) {
                    cd = new DataCela((DataCela) dd, cd.getId());
                    dd=new TextCela(dd.getId(),"");
                } else if (org[i][j] instanceof CelaRefText) {
                    cd = new DataCela((DataCela) dd, cd.getId());
                    dd=new TextCela(dd.getId(),"");
                } else if (org[i][j] instanceof CelaRefData) {
                    cd = new DataCela((DataCela) dd, cd.getId());
                    dd=new TextCela(dd.getId(),"");
                }
            }
        }
    }

    public void operar_bloc(Cela[][] org, Cela[][] dest, String operacio, Double oper) {
        switch (operacio){
            case "suma":
                for (int i = 0; i < org.length; i++) {
                    for (int j = 0; j < org[i].length; j++) {
                        Cela cd = dest[i][j];
                        Cela dd = org[i][j];
                        Double aux = Double.parseDouble(dest[i][j].getResultatFinal()) + oper;
                        dest[i][j]=new Numero(dest[i][j].getId(),BigDecimal.valueOf(aux),true,2,Tipus_Numero.numero);
                    }
                }
                break;
            case "resta":
                for (int i = 0; i < org.length; i++) {
                    for (int j = 0; j < org[i].length; j++) {
                        Cela cd = dest[i][j];
                        Cela dd = org[i][j];
                        Double aux = Double.parseDouble(dest[i][j].getResultatFinal()) -oper;
                        dest[i][j]=new Numero(dest[i][j].getId(),BigDecimal.valueOf(aux),true,2,Tipus_Numero.numero);
                    }
                }
                break;
            case "mult":
                for (int i = 0; i < org.length; i++) {
                    for (int j = 0; j < org[i].length; j++) {
                        Cela cd = dest[i][j];
                        Cela dd = org[i][j];
                        Double aux = Double.parseDouble(dest[i][j].getResultatFinal()) * oper;
                        dest[i][j]=new Numero(dest[i][j].getId(), BigDecimal.valueOf(aux),true,2,Tipus_Numero.numero);
                    }
                }
                break;
            case "div":
                for (int i = 0; i < org.length; i++) {
                    for (int j = 0; j < org[i].length; j++) {
                        Cela cd = dest[i][j];
                        Cela dd = org[i][j];
                        Double aux = Double.parseDouble(dest[i][j].getResultatFinal()) / oper;
                        dest[i][j]=new Numero(dest[i][j].getId(),BigDecimal.valueOf(aux),true,2,Tipus_Numero.numero);
                    }
                }
                break;
            case "may":
                for (int i = 0; i < org.length; i++) {
                    for (int j = 0; j < org[i].length; j++) {
                        Cela cd = dest[i][j];
                        Cela dd = org[i][j];
                        dest[i][j]=new TextCela(dest[i][j].getId(),dest[i][j].getResultatFinal().toUpperCase(Locale.ROOT));
                    }
                }
                break;
            case "min":
                for (int i = 0; i < org.length; i++) {
                    for (int j = 0; j < org[i].length; j++) {
                        Cela cd = dest[i][j];
                        Cela dd = org[i][j];
                        dest[i][j]=new TextCela(dest[i][j].getId(),dest[i][j].getResultatFinal().toLowerCase(Locale.ROOT));
                    }
                }
                break;
        }
    }

    public static ArrayList<Cela> buscar(ArrayList<Cela> inputs, String b){
        ArrayList<Cela> a = new ArrayList<>();
        for (Cela c : inputs) {
            if(c.buscarElement(b)) a.add(c);
        }
        return a;
    }

    public ArrayList<Cela> buscar_y_remplazar(ArrayList<Cela> inputs,String b,String r ) {
        ArrayList<Cela> a = buscar(inputs,b);
        ArrayList<Cela> ret = new ArrayList<>();
        for (Cela c : a) {
            if(Objects.equals(c.getType(), "text")) {
                if (c.buscarElement(b)){
                    TextCela t =(TextCela) c;
                    t.remplacarElement(b, r);
                    ret.add(c);
                }
            }
        }
        return ret;
    }




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
    public double covariança(ArrayList<Numero> a1, ArrayList<Numero> b1) {
        double x = 0, y = 0, sum = 0;
        //SUMATORI
        x = calculaMitjana(a1);
        y = calculaMitjana(b1);

        for(int i = 0; i < a1.size(); i++)
            sum += (a1.get(i).getResultat().doubleValue()-x)* (b1.get(i).getResultat().doubleValue()-y);

        return sum/a1.size();
    }

    public double coeficient_Pearson(ArrayList<Numero> a1, ArrayList<Numero> b1) {
        ArrayList<Numero> union = new ArrayList<Numero>();
        union.addAll(a1);
        union.addAll(b1);
        double res=calculaVariança(union)/(calculaDesviació(a1)*calculaDesviació(b1));
        return res;
    }
    public String COUNTIF(String LogicOP , String comparedOP,String IfFalse, String IfTrue, String CellContent){
        switch(LogicOP){
            case "==":
                if(Objects.equals(CellContent, comparedOP))return IfTrue;
                else return IfFalse;
            case ">":
                if(Double.parseDouble(CellContent) > Double.parseDouble(comparedOP))return IfTrue;
                else return IfFalse;
            case "<":
                if(Double.parseDouble(CellContent) < Double.parseDouble(comparedOP))return IfTrue;
                else return IfFalse;
            case ">=":
                if(Double.parseDouble(CellContent) >= Double.parseDouble(comparedOP))return IfTrue;
                else return IfFalse;
            case "<=":
                if(Double.parseDouble(CellContent) <= Double.parseDouble(comparedOP))return IfTrue;
                else return IfFalse;
            case "=":
                if(Objects.equals(Double.parseDouble(CellContent), Double.parseDouble(comparedOP)))return IfTrue;
                else return IfFalse;
            default:
                return "null";
        }
    }

    public Integer longitud(ArrayList<TextCela> l){
        Integer res= 0;
        for(int i=0;i<l.size();i++) res+=l.get(i).getResultatFinal().length();
        return res;
    }

    public double suma(ArrayList<Numero> inputs){
        double suma = 0;
        for (Cela c : inputs) {
            suma += Double.parseDouble(c.getResultatFinal());
        }
        return suma;
    }

    public double resta(ArrayList<Numero> inputs){
        double resta = 0;
        for (Cela c : inputs) {
            resta -= Double.parseDouble(c.getResultatFinal());
        }
        return resta;
    }
    public double mult(ArrayList<Numero> inputs){
        double mult = 1;
        for (Cela c : inputs) {
            mult *= Double.parseDouble(c.getResultatFinal());
        }
        return mult;
    }

    public static XYChart linearChart(double[] input1, double[] input2){
        return QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", input1, input2);
    }
    public static PieChart PieChart(ArrayList<String> input1, double[] input2){
        PieChart chart = new PieChartBuilder().width(800).height(600).title("Graf Circular").build();
        HashMap<String, Integer> grafic = new HashMap<>();
        for(String s : input1){
            grafic.put(s,0);
        }

        for(int i = 0; i < input1.size(); i++) {
            grafic.put(input1.get(i), grafic.get(input1.get(i))+(int) input2[i]);
        }
        for(HashMap.Entry<String, Integer> entry : grafic.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }
        return chart;
    }

    public static CategoryChart HistoChart(ArrayList<String> input1, double[] input2){
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Histograma").xAxisTitle("Score").yAxisTitle("Number").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        ///
        HashMap<String, Integer> graf = new HashMap<>();
        for(String s : input1){
            graf.put(s,0);
        }

        for(int i = 0; i < input1.size(); i++) {
            graf.put(input1.get(i), graf.get(input1.get(i))+(int) input2[i]);
        }

        ArrayList<String> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        for(HashMap.Entry<String, Integer> entry : graf.entrySet()) {
            first.add(entry.getKey());
            second.add(entry.getValue());
        }

        chart.addSeries("Histograma", first, second);

        return chart;
    }

}





