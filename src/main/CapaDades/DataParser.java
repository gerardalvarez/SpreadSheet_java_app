package main.CapaDades;

import main.CapaDomini.Models.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Documented;
import java.util.AbstractMap;
import java.util.ArrayList;

public class DataParser {

    public void guarda(Document document) throws Exception {

        File myObj = new File(document.getNom()+".txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
        FileWriter fileWriter = new FileWriter("C:\\Users\\Gerard\\IdeaProjects\\subgrup-prop3-1\\src\\main\\CapaDades\\"+document.getNom()+".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        //full extra
        Full f=new Full(2,3);
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 0), "1");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 0), "2");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 0), "1");

        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 1), "b");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 1), "c");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 1), "a");
        document.afegir_full(f);

        printWriter.print(document.getNom()+";"+document.getData_creacio()+";"+document.getData_ultima_mod()+"\nFULLES "+document.getNumfulls()+"\n\n");

        ArrayList<Full> fulls = document.getFulls();
        for(Full ff:fulls){
            printWriter.print(ff.getNom()+";"+ff.getNum_Files()+"-"+ff.getNum_Columnes()+"\n");
            String c="";
            for (Integer g=0; g < ff.getNum_Files(); ++g) { //PRINT
               for (Integer j = 0; j < ff.getNum_Columnes(); ++j) {
                   Cela a=ff.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j));
                   c=g.toString()+"-"+j.toString() +";"+a.getColorFons()+";"+a.getColorLletra()+";"+a.getType()+";"+a.getResultatFinal()+";"+a.getClass().getSimpleName();
                  switch (a.getClass().getSimpleName()){
                        case "Numero":
                            c=c+";"+ ((Numero) a).getResultat().toString()+";"+ ((Numero) a).getArrodonit().toString()+";"+ ((Numero) a).getNum_Decimals().toString()+";"+ ((Numero) a).getTipus().toString();
                            break;
                        case "TextCela":
                           c=c+";" ;
                           break;
                        case "DataCela":
                            c=c+";"+ ((DataCela) a).getDataFormat()+";"+ ((DataCela) a).getTextFormat();
                            break;
                        case "CelaRefNum":
                             c=c+";";
                            break;
                        case "CelaRefText":
                            c=c+";";
                            break;
                        case "CelaRefData":
                            c=c+";";
                            break;
                        default: break;
                    }
                    c+="|";
                    printWriter.print(c);
                }
                printWriter.print("\n");

            }
            printWriter.print("\n");
        }
            printWriter.close();
    }
}
