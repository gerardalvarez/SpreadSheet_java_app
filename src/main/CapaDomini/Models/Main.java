package main.CapaDomini.Models;

import main.CapaDades.DataParser;

import javax.print.Doc;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.util.*;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) throws Exception {

        Random rand = new Random();

        Full full = new Full(5, 5);
        for (int i = 0; i < full.getNum_Files(); ++i) {
            for (int j = 0; j < full.getNum_Columnes(); ++j) {
                full.Modifica_Cela(new AbstractMap.SimpleEntry<>(i, j), Integer.toString(rand.nextInt(10)));
            }
        }

        for (int i = 0; i < full.getNum_Files(); ++i) {
            for (int j = 0; j < full.getNum_Columnes(); ++j) {
                Cela c = full.Consultar_cela(new AbstractMap.SimpleEntry<>(i, j));
                System.out.print("(" + c.getId() + ") " + c.getResultatFinal() + "|");
            }
            System.out.println();
        }
/*
        AbstractMap.SimpleEntry<Integer,Integer> id1=new AbstractMap.SimpleEntry<>(1,1);
        AbstractMap.SimpleEntry<Integer,Integer> id2=new AbstractMap.SimpleEntry<>(3,2);
        ArrayList<Cela> ids = full.getBlocCeles(id1, id2);
        for(Cela i:ids) System.out.println(i.getId()+" ");
        Integer nf = id2.getKey() - id1.getKey();
        Integer nc = id2.getValue() - id1.getValue();
        Cela [][] mat = new Cela[nf+1][nc+1];
        System.out.println(nf);
        System.out.println(nc);
        System.out.println();

        int count=0;
        for (int i=0;i<= nf;++i) {
            for (int j = 0; j <= nc; ++j) {
                mat[i][j]=ids.get(count);
                count++;
            }
        }

        for (int i=0;i< mat.length;++i) {
            for (int j = 0; j < mat[0].length; ++j) {
                Cela c= mat[i][j];
                System.out.print("("+ c.getId()+") "+c.getResultatFinal()+ "|");
            }
            System.out.println();
        }
        Bloc_celes b=new Bloc_celes();
        ArrayList<Integer> col=new ArrayList<>();
        col.add(1);
        b.ordena_Z_A(mat,col);
        System.out.println();

        for (int i=0;i< mat.length;++i) {
            for (int j = 0; j < mat[0].length; ++j) {
                Cela c = mat[i][j];
                System.out.print("(" + c.getId() + ") " + c.getResultatFinal() + "|");
            }
            System.out.println();
        }*/
/*
        DataParser d = new DataParser();
        d.getdocs();
        Document x = d.carrega("Prueba");
        for (Full f : x.getFulls()) {
            System.out.println(f.getNom());
            for (int i = 0; i < f.getNum_Files(); ++i) {
                for (int j = 0; j < f.getNum_Columnes(); ++j) {
                    Cela c = f.Consultar_cela(new AbstractMap.SimpleEntry<>(i, j));
                    System.out.print("(" + c.getId() + ") " + c.getResultatFinal() + "|");
                }
                System.out.println();
            }
            System.out.println(x.getData_ultima_mod());
        }

        x.getNumfulls();
        ArrayList<Integer> cols = new ArrayList<>();
        cols.add(1);
        cols.add(0);
        x.get_full("Full sense nom").ordena_bloc(new AbstractMap.SimpleEntry<>(0, 0),new AbstractMap.SimpleEntry<>(2, 2),cols,"Major-menor");
        d.guarda(x);
        for (Full f : x.getFulls()) {
            System.out.println(f.getNom());
            for (int i = 0; i < f.getNum_Files(); ++i) {
                for (int j = 0; j < f.getNum_Columnes(); ++j) {
                    Cela c = f.Consultar_cela(new AbstractMap.SimpleEntry<>(i, j));
                    System.out.print("(" + c.getId() + ") " + c.getResultatFinal() + "|");
                }
                System.out.println();
            }
            System.out.println(x.getData_ultima_mod());
        }*/


        System.out.println("------");
        String s="=SUM(A2,B2)";

        analiza(s,10,10);

    }

    private static void analiza(String s, int x, int y) {
        String tipus="";
        String oper="";
        ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> l= new ArrayList<>();
        if (s.substring(0,1).equals("=")){//es ref;
            if (s.length()>6) {
                String ss=s.substring(1,5);
                if ((ss.equals("SUM(") || ss.equals("RES(") || ss.equals("PRO(") || ss.equals("DIV(") || ss.equals("AVG(") ||
                        ss.equals("MED(") || ss.equals("VAR(") || ss.equals("MOD(") || ss.equals("MAX(") || ss.equals("DEV(") ||
                        ss.equals("MAY(") || ss.equals("MIN(") ) && s.substring(s.length()-1).equals(")")) { //ES REF OP

                    oper=ss;
                    String ops= s.substring(5,s.length()-1);

                    Scanner sc=new Scanner(ops);

                    sc.useDelimiter(",");

                    while (sc.hasNext()){
                        String op =sc.next();
                        if (op.contains(":")){ //es bloque
                            if (op.length()<5) {
                                tipus = "referencia pero #ERROR";
                                break;
                            }
                        }
                        else {
                            String[] part = op.split("(?<=\\D)(?=\\d)");
                            if (part.length==2 && isNum(part[1]) && toNumber(part[0])<y && toNumber(part[0])>0 && Integer.parseInt(part[1])<=x && Integer.parseInt(part[1])>0){
                                l.add(new AbstractMap.SimpleEntry<>(toNumber(part[0]),Integer.parseInt(part[1])));
                                if (oper.equals("MAY(") || oper.equals("MIN")) tipus="REFTEXT";
                                else tipus="REFNUM";
                            }else{
                                tipus="referencia pero #ERROR";
                                break;
                            }
                        }
                    }
                } else tipus="referencia pero #ERROR";
            } else if(s.length()<=5){ //Referencia a una celda

                String aux= s.replaceFirst("=","");
                String[] part = aux.split("(?<=\\D)(?=\\d)");
                if (part.length==2 && isNum(part[1]) && toNumber(part[0])<y && Integer.parseInt(part[1])<=x){
                    l.add(new AbstractMap.SimpleEntry<>(toNumber(part[0]),Integer.parseInt(part[1])));
                    tipus ="ref a otra celda";
                }else tipus="referencia pero #ERROR";

            }else tipus= "referencia pero #ERROR";

        }else if(isNum(s)) tipus ="numeric";
        else if (/*is data*/ 1<0) tipus ="data";
        else tipus="text";
        System.out.println("La cela es de "+tipus);
        if (l.size()>0){
            System.out.print("Los operadores de "+oper+" son: ");
            for(AbstractMap.SimpleEntry<Integer,Integer> q:l){
                System.out.print((q.getKey()-1) +" "+(q.getValue()-1)+", ");
            }
        }
    }

    private static boolean isNum(String s) {
        try {
            Double.parseDouble(s);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }
   // Pasar de numeros a letras para las columnas:

    public static String getExcelColumnName(int number) {
        final StringBuilder sb = new StringBuilder();
        int num = number - 1;
        while (num >= 0) {
            int numChar = (num % 26) + 65;
            sb.append((char)numChar);
            num = (num / 26) - 1;
        }
        return sb.reverse().toString();
    }

//Pasar de letras a numeros:
    public static int toNumber(String name) {
        int number = 0;
        for (int i = 0; i < name.length(); i++) {
            number = number * 26 + (name.charAt(i) - ('A' - 1));
        }
        return number; }


}


