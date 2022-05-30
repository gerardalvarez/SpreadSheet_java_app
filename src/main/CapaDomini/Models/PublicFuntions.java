package main.CapaDomini.Models;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Public Funtions es la classe on trobem les diferents operacions que no engloben un model i que poden ser usades per qualsevol altre classe
 * @author Gerard Castell i Gerard Alvarez
 */
public class PublicFuntions {

    /**
     * Funcio que passat un string amb un mes del any retorna el mes en el format de text que ens interessa retornarlo
     * @param monthNumber String amb el numero del mes corresponent
     * @return un string amb el mes en el format text que ens interessa
     */
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

    /**
     * Funcio que passat un string en el format text definit del Full calcul, es retorna el mes en format numeric
     * @param monthText String del mes en format Text
     * @return retorna el mes en format numeric
     */
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

    /**
     * Funcio que retorna el tipus de una Cela
     * @param contingut els contingut que té la cela
     * @return retorna si es tipus text, data o numeric
     */
    public static String calculaTipus(String contingut){
        String Tipus = "text";
        if(contingut == null)return Tipus;
        if(isNumerical(contingut))Tipus = "numeric";
        else if(isData(contingut))Tipus = "date";
        return Tipus;
    }

    /**
     * Funcio que agafa les paraules separades entre "," i les guarda en una llista
     * @param line Linia a analitzar
     * @return retorna una llista de les paraules
     */
    public static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    /**
     * Funcio que analitza si la sintaxis de l'operacio que es vol realitzar es correcta
     * @param s contingut a analitzar
     * @param y numero de files del full
     * @param x numero de columnes del full
     * @return String que indica si es una cel·la normal, referencia o error
     */
    public static String analiza(String s, int y, int x) {
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
                                    String[] part = scc.next().split("(?<=\\D)(?=\\d)");
                                    String[] part2 = scc.next().split("(?<=\\D)(?=\\d)");
                                    if (part.length==2 && isNum(part[1]) && toNumber(part[0])<y && toNumber(part[0])>0 && Integer.parseInt(part[1])<=x && Integer.parseInt(part[1])>0
                                            && part2.length==2 && isNum(part2[1]) && toNumber(part2[0])<y && toNumber(part2[0])>0 && Integer.parseInt(part2[1])<=x && Integer.parseInt(part2[1])>0
                                            && Integer.parseInt(part[1]) <= Integer.parseInt(part2[1]) && toNumber(part[0])<=toNumber(part2[0])
                                            && !part[0].contains("-") && !part[0].contains("+") && !part[0].contains("*") && !part[0].contains("'") && !part[0].contains(".") ){
                                        System.out.println("EL BLOQUE ES " +toNumber(part[0])+" " +Integer.parseInt(part[1]));
                                        if (oper.equals("MAY(") || oper.equals("MIN")) tipus="REFTEXT";
                                        else tipus="REFNUM";
                                    }else{
                                        tipus="referencia pero #ERROR";
                                        err=true;
                                        break;
                                    }
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


    /**
     * Funcio que analitza si la sintaxis de l'operacio que es vol realitzar es correcta
     * @param s contingut a analitzar
     * @param y numero de files del full
     * @param x numero de columnes del full
     * @return @return retorna la llista de cel·les que operen
     */
    public static ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> analizaops(String s, int y, int x) {
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
                                    if (part.length==2 && isNum(part[1]) && toNumber(part[0])<y && toNumber(part[0])>0 && Integer.parseInt(part[1])<=x && Integer.parseInt(part[1])>0
                                    && part2.length==2 && isNum(part2[1]) && toNumber(part2[0])<y && toNumber(part2[0])>0 && Integer.parseInt(part2[1])<=x && Integer.parseInt(part2[1])>0){
                                        if (Integer.parseInt(part[1]) <= Integer.parseInt(part2[1]) && toNumber(part[0])<=toNumber(part2[0]) ){
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

    /**
     * Functio que retorna si un string es de tipus numeric
     * @param s String amb a comprovar
     * @return true si es numeric, fals si no
     */
    public static boolean isNum(String s) {
        try {
            Double.parseDouble(s);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Funcio que passa una lletra en el seu equivalent numeric
     * @param name Conjunt de lletres a canviar
     * @return el seu numero equivalent
     */
    public static int toNumber(String name) {
        int number = 0;
        for (int i = 0; i < name.length(); i++) {
            number = number * 26 + (name.charAt(i) - ('A' - 1));
        }
        return number; }

    /**
     * Funcio que indica si un string es un numero
     * @param strNum String a analitzar
     * @return Bolea si es numero o no
     */
    private static Boolean isNumerical(String strNum){
        if (strNum == null) return false;

        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Funcio que indica si un string es una data
     * @param strData String a analitzar
     * @return Bolea si es data o no
     */
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
}




















