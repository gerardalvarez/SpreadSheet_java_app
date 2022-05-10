package main.CapaDades;

import main.CapaDomini.Models.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Documented;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class DataParser {

    public void guarda(Document document) throws Exception {

        File myObj = new File("src/main/CapaDades/"+document.getNom());
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            FileWriter fileWriter = new FileWriter("src/main/CapaDades/DatabaseDocs",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(document.getNom()+"\n");
            printWriter.flush();
            printWriter.close();
        } else {
            System.out.println("File already exists.");
        }
        FileWriter fileWriter = new FileWriter("src/main/CapaDades/"+document.getNom());
        PrintWriter printWriter = new PrintWriter(fileWriter);

        //ESto son hojas creadas para hacer pruebas ya que el doc que uso en el main está vacío

        /*full extra para pruebas*//*
        Full f=new Full(2,3);
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 0), "1");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 0), "2");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 0), "1");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 1), "b");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 1), "c");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 1), "a");
        f.SetNom("Hoja 1");
        document.afegir_full(f);
        *//*full extra para pruebas 2*//*
        f=new Full(3,3);
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 0), "2");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 0), "2");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 0), "1");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(0, 1), "b");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(1, 1), "cc");
        f.Modifica_Cela(new AbstractMap.SimpleEntry<>(2, 1), "2.02");
        Cela aa= f.Consultar_cela(new AbstractMap.SimpleEntry<>(2, 0));
        aa.getObservadors().add(new AbstractMap.SimpleEntry<>(2, 1));
        f.SetNom("Hoja 2");
        document.afegir_full(f);
        */


        DateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
        printWriter.print(document.getNom()+";"+dateFormat.format(document.getData_creacio())+";"+dateFormat.format(new Date())+"\n"+document.getNumfulls()+"\n");
        ArrayList<Full> fulls = document.getFulls();
        for(Full ff:fulls){
            printWriter.print(ff.getNom()+";"+ff.getNum_Files()+";"+ff.getNum_Columnes()+"\n");
            String c="";
            for (Integer g=0; g < ff.getNum_Files(); ++g) { //PRINT
               for (Integer j = 0; j < ff.getNum_Columnes(); ++j) {
                   Cela a=ff.getCeles().get(new AbstractMap.SimpleEntry<Integer, Integer>(g,j));
                   c=g.toString()+";"+j.toString() +";"+a.getColorFons().getRed()+";"+a.getColorFons().getGreen()+";"+a.getColorFons().getBlue()+";"+a.getColorLletra().getRed()+";"+a.getColorLletra().getGreen()+";"+a.getColorLletra().getBlue()+";"+a.getType()+";"+a.getResultatFinal()+";"+a.getClass().getSimpleName();
                  switch (a.getClass().getSimpleName()){
                        case "Numero":
                            c=c+";"+ ((Numero) a).getResultat().toString()+";"+ ((Numero) a).getArrodonit().toString()+";"+ ((Numero) a).getNum_Decimals().toString()+";"+ ((Numero) a).getTipus().toString()+";";

                            break;
                        case "TextCela":
                           c=c+";";
                           break;
                        case "DataCela":
                            c=c+";";
                            break;
                        case "CelaRefNum":
                             c=c+";"+ ((CelaRefNum) a).getResultat().toString()+";"+ ((CelaRefNum) a).getArrodonit().toString()+";"+ ((CelaRefNum) a).getNum_Decimals().toString()+";"+ ((CelaRefNum) a).getTipus().toString()+";"+ ((CelaRefNum) a).getContingut()+";";
                            break;
                        case "CelaRefText":
                            c=c+";"+ ((CelaRefText) a).getContingut()+";";
                            break;
                        case "CelaRefData":
                            c=c+";"+ ((CelaRefData) a).getContingut()+";";
                            break;
                        default: break;
                    }
                    int aux=a.getObservadors().size();
                    c+=aux+";";
                    if (aux!=0){
                        for (int i =0;i<aux;++i){
                            c+=a.getObservadors().get(i).getKey()+";"+a.getObservadors().get(i).getValue()+";";
                        }
                    }
                    c+="|";
                    printWriter.print(c);
                   printWriter.print("\n");
                }

            }
        }
            printWriter.close();
    }

    public Document carrega(String nom) throws Exception {

        List<String> content = Files.readAllLines(Paths.get("src/main/CapaDades/"+nom));
        for (String s:content) System.out.println(s);
        Scanner scan = new Scanner(content.get(0));
        scan.useDelimiter(Pattern.compile(";"));
        Document d=new Document(scan.next());
        SimpleDateFormat formatter1=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
        d.setData_creacio(formatter1.parse(scan.next()));
        d.setData_ultima_mod(formatter1.parse(scan.next()));
        int nfulls= Integer.parseInt(content.get(1));
        d.setNumfulls(nfulls);
        System.out.println(nfulls);
        int l=2;
        for(int i=0;i<nfulls;i++){
            scan = new Scanner(content.get(l));
            scan.useDelimiter(Pattern.compile(";"));
            String nomm= scan.next();
            int fila = scan.nextInt();
            int col = scan.nextInt();
            Full z =new Full(nomm,col,fila);
            HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Cela> celes = z.getCeles();
            ++l;
            for (int ii=0;ii<fila*col;++ii) {
                Cela x = leecela(content.get(l));
                ++l;
                celes.replace(x.getId(), x);
            }
            d.afegir_full(z);
            System.out.println(l);
        }
        System.out.println(d.getFulls().size());

        return d;
    }

    public Cela leecela( String x){
        Cela c=null;
        Scanner scan = new Scanner(x);
        scan.useDelimiter(Pattern.compile(";"));
        int fila = scan.nextInt();
        int col = scan.nextInt();
        int rc = scan.nextInt();
        int gc = scan.nextInt();
        int bc = scan.nextInt();
        int r = scan.nextInt();
        int g = scan.nextInt();
        int b = scan.nextInt();

        String tipus = scan.next();
        String res = scan.next();
        String subclass= scan.next();
        switch (subclass){
            case "Numero":
                c=new Numero(new AbstractMap.SimpleEntry<Integer, Integer>(fila,col),new BigDecimal(scan.next()),Boolean.valueOf(scan.next()), scan.nextInt(), Tipus_Numero.valueOf("numero"));
                scan.next();
                break;

            case "TextCela":
                c=new TextCela(new AbstractMap.SimpleEntry<Integer, Integer>(fila,col),res);
                break;

            case "DataCela":
                c=new DataCela(new AbstractMap.SimpleEntry<Integer, Integer>(fila,col),res);
                break;

            case "CelaRefNum":
                String aa=scan.next();
                Boolean ab =Boolean.valueOf(scan.next());
                Integer ac=  scan.nextInt();
                scan.next();
                String cont= scan.next();
                c =new CelaRefNum(new AbstractMap.SimpleEntry<Integer, Integer>(fila,col),aa,ab, ac, Tipus_Numero.valueOf("numero"),cont);
                break;

            case "CelaRefText":
                c= new CelaRefText(new AbstractMap.SimpleEntry<Integer, Integer>(fila,col),scan.next(),res);
                break;

            case "CelaRefData":
                c= new CelaRefData(new AbstractMap.SimpleEntry<Integer, Integer>(fila,col),scan.next(),res);
                break;

            default: break;
        }
        int aux= scan.nextInt();
        if (aux!=0){
            for (int i=0;i<aux;++i){
                c.getObservadors().add((new AbstractMap.SimpleEntry<Integer, Integer>(scan.nextInt(),scan.nextInt())));
            }
        }
        c.setColorFons(new Color(rc,gc,bc));
        c.setColorLletra(new Color(r,g,b));
        c.setType(tipus);
        return c;
    }


    public List<String> getdocs() throws IOException {


        List<String> content = Files.readAllLines(Paths.get("C:\\Users\\Gerard\\IdeaProjects\\subgrup-prop3-1\\src\\main\\CapaDades\\DatabaseDocs"));
        return content;
    }



}
