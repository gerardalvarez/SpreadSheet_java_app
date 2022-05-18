package main.CapaDomini.Models;

import main.CapaDades.DataParser;
import main.CapaDomini.Controllers.CtrlDomini;

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

        Full f = new Full(5, 5);
        for (int i = 0; i < f.getNum_Files(); ++i) {
            for (int j = 0; j < f.getNum_Columnes(); ++j) {
                f.Modifica_Cela(new AbstractMap.SimpleEntry<>(i, j), Integer.toString(rand.nextInt(10)));
            }
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

        CtrlDomini ct =new CtrlDomini();
        DataParser d= new DataParser();
        Document a=d.carrega("a");
        ct.gg().put("a",a);

        for (Full ff: a.getFulls()) {
            for (int i = 0; i < ff.getNum_Files(); ++i) {
                for (int j = 0; j < ff.getNum_Columnes(); ++j) {
                    Cela c = ff.Consultar_cela(new AbstractMap.SimpleEntry<>(i, j));
                    System.out.print("(" + c.getId() + ") " + c.getResultatFinal() + "|");
                }
                System.out.println();
            }
        }
        ct.modificarContingutCela("a","Full sense nom",new AbstractMap.SimpleEntry<>(0,0),"a");
        ct.modificarContingutCela("a","Full sense nom",new AbstractMap.SimpleEntry<>(0,1),"aaa");
        ct.modificarContingutCela("a","Full sense nom",new AbstractMap.SimpleEntry<>(4,0),"=SUM(A1:D2)");
        ct.copiar("a","Full sense nom",new AbstractMap.SimpleEntry<>(0,0),new AbstractMap.SimpleEntry<>(2,1),new AbstractMap.SimpleEntry<>(2,2),new AbstractMap.SimpleEntry<>(4,3));
        ct.Operar_bloc("a","Full sense nom",new AbstractMap.SimpleEntry<>(0,0),new AbstractMap.SimpleEntry<>(0,1),new AbstractMap.SimpleEntry<>(0,0),new AbstractMap.SimpleEntry<>(0,1),"may", 5.0);
            System.out.println(f.getNom());
            for (Full ff: a.getFulls()) {
                for (int i = 0; i < ff.getNum_Files(); ++i) {
                    for (int j = 0; j < ff.getNum_Columnes(); ++j) {
                        Cela c = ff.Consultar_cela(new AbstractMap.SimpleEntry<>(i, j));
                        System.out.print("(" + c.getId() + ") " + c.getResultatFinal() + "|");
                    }
                    System.out.println();
                }
            }

        }
    }


