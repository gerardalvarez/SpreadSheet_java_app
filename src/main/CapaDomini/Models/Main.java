package main.CapaDomini.Models;

import main.CapaDades.DataParser;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {


    public static void main(String[] args) throws Exception {

        Random rand = new Random();

        Full full=new Full(5,5);
        for (int i=0;i< full.getNum_Files();++i) {
            for (int j = 0; j < full.getNum_Columnes(); ++j) {
                full.Modifica_Cela(new AbstractMap.SimpleEntry<>(i,j), Integer.toString(rand.nextInt(10)));
            }
        }

        for (int i=0;i< full.getNum_Files();++i) {
            for (int j = 0; j < full.getNum_Columnes(); ++j) {
                Cela c= full.Consultar_cela(new AbstractMap.SimpleEntry<>(i,j));
                System.out.print("("+ c.getId()+") "+c.getResultatFinal()+ "|");
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

        DataParser d =new DataParser();
        d.guarda(new Document("Prueba"));
    }
}
