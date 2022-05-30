package main.CapaDades;

import main.CapaDomini.Models.*;

import javax.print.Doc;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.lang.annotation.Documented;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Implementació de la classe Data Parser que transforma les instancies en el nostre propi format
 * @author Gerard Alvarez
 */
public class DataParser {



    /**
     * Funcio que comprova si existeix algun document en el path amb el mateix nom, si no existeix el crea i guarda el nom al document parsejat.
     * @param document Document a guardar
     * @param path Path on es vol guardar
     * @param fileName Nom del nou document
     * @return si existeix o no
     */
    public Boolean comprovaExisteix(Document document, String path, String fileName) throws IOException {
        File myObj = new File(path + "/" + fileName + ".fdc");
        System.out.println(path + "/" + fileName + ".fdc");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            FileWriter fileWriter = new FileWriter(path + "/" + fileName + ".fdc",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(document.getNom()+"\n");
            printWriter.flush();
            printWriter.close();
            return false;
        } else {
            System.out.println("File already exists.");
            return true;
        }
    }

    /**
     * Funcio que sobreescriu el document del path amb el nom filename per les dades del document del paràmetre
     * @param document Document a guardar
     * @param path Path on es vol guardar
     * @param fileName Nom del nou document
     */
    public void guarda(Document document, String path, String fileName) throws Exception {
        FileWriter fileWriter = new FileWriter(path + "/" + fileName + ".fdc");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        DateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
        printWriter.print(document.getNom()+";"+dateFormat.format(document.getData_creacio())+";"+dateFormat.format(new Date())+"\n"+document.getFulls().size()+"\n");
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


    /**
     * Funcio que llegeix un document en el format propi i el passa a instància
     * @param nom nom del document
     * @param path Path d'on es vol carregar
     * @return Instancia de document
     */
    public Document carrega(String nom, String path) throws Exception {

        List<String> content = Files.readAllLines(Paths.get(path + "/" + nom));
        //for (String s:content) System.out.println(s);
        Scanner scan = new Scanner(content.get(0));
        scan.useDelimiter(Pattern.compile(";"));
        Document d=new Document(scan.next());
        SimpleDateFormat formatter1=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");
        d.setData_creacio(formatter1.parse(scan.next()));
        d.setData_ultima_mod(formatter1.parse(scan.next()));
        int nfulls= Integer.parseInt(content.get(1));
        d.setNumfulls(nfulls);
        //System.out.println(nfulls);
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
           // System.out.println(l);
        }
       // System.out.println(d.getFulls().size());

        return d;
    }
    /**
     * Funcio auxiliar que crida carrega() per llegir una linea y passarla a instancia de cel·la
     * @param x String que conte tota la informació d'una cel·la
     * @return Instancia de cela
     */
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

    /**
     * Funcio que crea un document csv al path amb el nom filename amb les dades del parametre contigut
     * @param contingut Matriu amb tots els texts de les cel·les
     * @param path Path on es vol guardar
     * @param fileName Nom del nou document
     */
    public static void exportaCsv(String fileName, String path, String[][] contingut) throws IOException {
        File myObj = new File(path + "/" + fileName + ".csv");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            FileWriter fileWriter = new FileWriter(path + "/" + fileName + ".csv",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (String[] fila : contingut) {
                String fila_final = Arrays.toString(fila).replace("[", "").replace("]", ",");
                printWriter.print(fila_final + "\n");
            }

            printWriter.flush();
            printWriter.close();
        } else {
            System.out.println("File already exists.");
        }
    }

    /**
     * Funcio que llegeix un document en el format csv i el passa a instància
     * @param fileName nom del document
     * @param path Path d'on es vol carregar
     * @return
     */
    public static List<List<String>> readCsv(String fileName, String path) throws FileNotFoundException {
        List<List<String>> files = new ArrayList<>();
        int size = 0;
        Scanner scanner = new Scanner(new File(path + "/" + fileName));
        while (scanner.hasNextLine()) {
            List<String> fil = PublicFuntions.getRecordFromLine(scanner.nextLine());
            if (fil.size() > size) size = fil.size();
            files.add(fil);
        }

        return files;
    }




}
