package main.CapaDomini.Models;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Objects;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PublicFuntions {
    public static String monthToText(String monthNumber){
        switch (monthNumber) {
            case "01":
                return " de gener del ";
            case "02":
                return " de febrer del ";
            case "03":
                return " de març del ";
            case "04":
                return " de abril del ";
            case "05":
                return " de maig del ";
            case "06":
                return " de juny del ";
            case "07":
                return " de juliol del ";
            case "08":
                return " de agost del ";
            case "09":
                return " de setembre del ";
            case "10":
                return " de octubre del ";
            case "11":
                return " de novembre del ";
            case "12":
                return " de desembre del ";
        }
        return "null";
    }

    public static String monthToData(String monthText){
        switch (monthText) {
            case " de gener del ":
                return "01";
            case " de febrer del ":
                return "02";
            case " de març del ":
                return "03";
            case " de abril del ":
                return "04";
            case " de maig del ":
                return "05";
            case " de juny del ":
                return "06";
            case " de juliol del ":
                return "07";
            case " de agost del ":
                return "08";
            case " de setembre del ":
                return "09";
            case " de octubre del ":
                return "10";
            case " de novembre del ":
                return "11";
            case " de desembre del ":
                return "12";
        }
        return "null";
    }

    public static String calculaTipus(String contingut){
        String Tipus = "text";
        if(contingut == null)return Tipus;
        if(isNumerical(contingut))Tipus = "numeric";
        else if(isData(contingut))Tipus = "date";
        return Tipus;
    }

    //PRIVATE FUNCTIONS
    private static Boolean isNumerical(String strNum){
        if (strNum == null) return false;

        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static Boolean isData(String strData){
        DataValidator validator = new DateValidator("dd/MM/yyyy");
        if(validator.isValid(strData))return true;
        else{
            int size = strData.length();
            if(size < 19)return false;

            String dd = strData.substring(0,2);
            String monthText = strData.substring(2,size-4);

            String MM = PublicFuntions.monthToData(monthText);
            if(MM.equals("null"))return false;

            String yyyy = strData.substring(size-4);

            String date = dd+"/"+ MM + "/" + yyyy;
            return validator.isValid(date);
        }
    }



    public static Full readCsv() {
        List<List<String>> files = new ArrayList<>();
        int size = 0;
        try (Scanner scanner = new Scanner(new File("src/main/CapaDomini/Models/Test.csv"));) {
            while (scanner.hasNextLine()) {
                List<String> fil = getRecordFromLine(scanner.nextLine());
                if (fil.size() > size) size = fil.size();
                files.add(fil);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Full f = new Full("Full 2", size, files.size());

        int i = 0;
        for (List <String> lists: files) {
            int j = 0;
            for (String s : lists) {
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(i, j);
                f.Modifica_Cela(id, s);
                j++;
            }
            i++;
        }
        return f;
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }


    public static Boolean esRef(String result, Integer fila, Integer col){
        Integer size = result.length();
        if(size < 5)return false;
        if(result.charAt(0) != '=')return false;
        if(result.charAt(1) != '#')return false;
        if(!result.contains("_") || result.charAt(size-1) == '_')return false;
        if(!isNumerical(result.substring(2, result.indexOf('_'))) || !isNumerical(result.substring(result.indexOf('_')+1)))return false;
        if(!refValida(Integer.parseInt(result.substring(2,result.indexOf('_'))),fila,col) || !refValida(Integer.parseInt(result.substring(result.indexOf('_')+1)),fila,col))return false;
        return true;
    }


    public static boolean refValida(Integer num, Integer fila, Integer col){
        return (num-1 < fila && num-1 >= 0 && num-1 < col);
    }

    public static String analiza(String s, int x, int y) {
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
                            }else {
                                Scanner scc=new Scanner(op);
                                scc.useDelimiter(":");
                                Boolean err=false;
                                while (scc.hasNext()){
                                    String[] part = scc.next().split("(?<=\\D)(?=\\d)");
                                    if (part.length==2 && isNum(part[1]) && toNumber(part[0])<y && toNumber(part[0])>0 && Integer.parseInt(part[1])<=x && Integer.parseInt(part[1])>0
                                            && !part[0].contains("-") && !part[0].contains("+") && !part[0].contains("*") && !part[0].contains("'") && !part[0].contains(".")){
                                        System.out.println("EL BLOQUE ES " +toNumber(part[0])+" " +Integer.parseInt(part[1]));
                                        if (oper.equals("MAY(") || oper.equals("MIN")) tipus="REFTEXT";
                                        else tipus="REFNUM";
                                    }else{
                                        tipus="referencia pero #ERROR";
                                        err=true;
                                        break;
                                    }
                                }
                                if (err) break;
                            }
                        } else {
                            String[] part = op.split("(?<=\\D)(?=\\d)");
                            if (part.length==2 && isNum(part[1]) && toNumber(part[0])<=y && toNumber(part[0])>0 && Integer.parseInt(part[1])<=x && Integer.parseInt(part[1])>0
                                    && !part[0].contains("-") && !part[0].contains("+") && !part[0].contains("*") && !part[0].contains("'") && !part[0].contains(".")){
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
                if (part.length==2 && isNum(part[1]) && toNumber(part[0])<=y && Integer.parseInt(part[1])<=x && toNumber(part[0])>0 && Integer.parseInt(part[1])>0
                        && !part[0].contains("-") && !part[0].contains("+") && !part[0].contains("*") && !part[0].contains("'") && !part[0].contains(".")){
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
        return tipus;
    }

    public static ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> analizaops(String s, int x, int y) {
        String tipus="";
        String oper="";
        ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> l= new ArrayList<>();
        ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> operadores= new ArrayList<>();
        if (s.substring(0,1).equals("=")){//es ref;
            if (s.length()>6) {
                String ss=s.substring(1,5);
                if ((ss.equals("SUM(") || ss.equals("RES(") || ss.equals("PRO(") || ss.equals("DIV(") || ss.equals("AVG(") ||
                        ss.equals("MED(") || ss.equals("VAR(") || ss.equals("MOD(") || ss.equals("MAX(") || ss.equals("DEV(") ||
                        ss.equals("MAY(") || ss.equals("MIN(") ) && s.substring(s.length()-1).equals(")")) {      //ES REF OP

                    oper=ss;
                    String ops= s.substring(5,s.length()-1);

                    Scanner sc=new Scanner(ops);

                    sc.useDelimiter(",");

                    while (sc.hasNext()){
                        String op =sc.next();
                        if (op.contains(":")){                       // es bloque
                            if (op.length()<5) {
                                tipus = "referencia pero #ERROR";
                                break;
                            }else {
                                Scanner scc=new Scanner(op);
                                scc.useDelimiter(":");
                                Boolean err=false;
                                    String[] part = scc.next().split("(?<=\\D)(?=\\d)");
                                    String[] part2 = scc.next().split("(?<=\\D)(?=\\d)");
                                    if (part.length==2 && isNum(part[1]) && toNumber(part[0])<y && toNumber(part[0])>0 && Integer.parseInt(part[1])<=x && Integer.parseInt(part[1])>0){
                                        if (part2.length==2 && isNum(part2[1]) && toNumber(part2[0])<y && toNumber(part2[0])>0 && Integer.parseInt(part2[1])<=x && Integer.parseInt(part2[1])>0){
                                        for (int i=toNumber(part[0]);i<=toNumber(part2[0]);i++){
                                            for (int j=Integer.parseInt(part[1]);j<=Integer.parseInt(part2[1]);j++){
                                                l.add(new AbstractMap.SimpleEntry<>(i,j));
                                            }
                                        }
                                        if (oper.equals("MAY(") || oper.equals("MIN")) tipus="REFTEXT";
                                        else tipus="REFNUM";
                                        }else{
                                            tipus="referencia pero #ERROR";
                                            err=true;
                                    }
                                }else{
                                        tipus="referencia pero #ERROR";
                                        break;
                                    }
                                if (err) break;
                            }
                        }
                        else {            //OPERANDO NORMAL
                            String[] part = op.split("(?<=\\D)(?=\\d)");
                            if (part.length==2 && isNum(part[1]) && toNumber(part[0])<=y && toNumber(part[0])>0 && Integer.parseInt(part[1])<=x && Integer.parseInt(part[1])>0
                            && !part[0].contains("-") && !part[0].contains("+") && !part[0].contains("*") && !part[0].contains("'") && !part[0].contains(".")){
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
                if (part.length==2 && isNum(part[1]) && toNumber(part[0])<=y && Integer.parseInt(part[1])<=x && toNumber(part[0])>0 && Integer.parseInt(part[1])>0
                        && !part[0].contains("-") && !part[0].contains("+") && !part[0].contains("*") && !part[0].contains("'") && !part[0].contains(".")){
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
                operadores.add(new AbstractMap.SimpleEntry<Integer, Integer>(q.getKey()-1,q.getValue()-1));
            }
        }
        return operadores;
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




















