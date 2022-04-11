package main.CapaDomini.Models;

import java.awt.*;
import java.util.AbstractMap;

public class Cela {
    //VARIABLES
    private AbstractMap.SimpleEntry<Integer, Integer> id;
    private String contingut;
    private Color colorFons = new Color(255,255,255);
    private Color colorLletra = new Color(0);
    private CelaEnum designedType; // En un futur fer enum
    private String type;

    //CREADORA
    public Cela(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) {
        this.id = id;
        this.contingut = contingut;
        this.type = calculaTipus();
    }


    //GETTERS AND SETTERS
    public CelaEnum getDesignedType() {return designedType;}
    public void setDesignedType(CelaEnum designedType) {this.designedType = designedType;}
    public AbstractMap.SimpleEntry<Integer, Integer> getId() {return id;}
    public void setId(AbstractMap.SimpleEntry<Integer, Integer> id) {this.id = id;}
    public String getContingut() {return contingut;}
    public void setContingut(String contingut) {this.contingut = contingut;}
    public Color getColorFons() {return colorFons;}
    public void setColorFons(Color colorFons) {this.colorFons = colorFons;}
    public Color getColorLletra() {return colorLletra;}
    public void setColorLletra(Color colorLletra) {this.colorLletra = colorLletra;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    //PUBLIC FUNCTIONS
    public String calculaTipus(){
        String Tipus = "text";
        if(isNumerical(contingut))Tipus = "numeric";
        else if(isData(contingut))Tipus = "date";
        return Tipus;
    }

    //PRIVATE FUNCTIONS
    private Boolean isNumerical(String strNum){
        if (strNum == null) return false;

        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private Boolean isData(String strData){
        DataValidator validator = new DateValidator("dd/MM/yyyy");
        if(validator.isValid(strData))return true;
        else{
            int size = strData.length();
            if(size < 19)return false;

            String dd = strData.substring(0,2);
            String monthText = strData.substring(2,size-4);

            String MM = getMonth(monthText);
            if(MM.equals("null"))return false;

            String yyyy = strData.substring(size-4);

            String date = dd+"/"+ MM + "/" + yyyy;
            return validator.isValid(date);
        }
    }

    private String getMonth(String monthText){
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
}

