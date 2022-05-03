package main.CapaDomini.Models;

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

    public static String getOper(String text){
            if (Objects.equals(text, "SUM")) return "SUM";
            else if (Objects.equals(text, "RES")) return "RES";
            else if (Objects.equals(text, "PRO")) return "PRO";
            else if (Objects.equals(text, "DIV")) return "DIV";
            else if (Objects.equals(text, "AVG")) return "AVG";
            else if (Objects.equals(text, "MED")) return "MED";
            else if (Objects.equals(text, "VAR")) return "VAR";
            else if (Objects.equals(text, "MOD")) return "MOD";
            else if (Objects.equals(text, "MAX")) return "MAX";
            else if (Objects.equals(text, "DEV")) return "DEV";
            else if (Objects.equals(text, "MAY")) return "MAY";
            else if (Objects.equals(text, "MIN")) return "MIN";
            else return "NULL";
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
    public static Boolean esRefLlarga(String result, Integer fila, Integer col){
        Integer size = result.length();
        if(size < 5)return false;
        if(result.charAt(0) != '=')return false;
        String Oper = getOper(result.substring(1,4));
        if(Oper.equals("NULL"))return false;
        return true;
    }

    public static AbstractMap.SimpleEntry<Integer, Integer> getNumIdRef(String result){
        Integer fil = Integer.parseInt(result.substring(2,result.indexOf('_')));
        Integer col = Integer.parseInt(result.substring(result.indexOf('_')+1));
        return new AbstractMap.SimpleEntry<Integer,Integer>(fil-1,col-1);

    }

    public static boolean refValida(Integer num, Integer fila, Integer col){
        return (num-1 < fila && num-1 >= 0 && num-1 < col);
    }

}


