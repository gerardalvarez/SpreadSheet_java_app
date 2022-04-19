package main.CapaDomini.Models;

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

}


