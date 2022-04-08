package main.CapaDomini.Models;


import java.awt.*;
import java.util.AbstractMap;

public class Cela {
    //VARIABLES
    private AbstractMap.SimpleEntry<Integer, Integer> id;
    private String contingut;
    private Color colorFons;
    private Color colorLletra;
    private CelaEnum designedType; // En un futur fer enum

    //CREADORA


    public Cela(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut) {
        this.id = id;
        this.contingut = contingut;
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
            String dd = strData.substring(0,1);
            String MM = strData.substring(4,5);
            String yyyy = strData.substring(9);
            String date = dd+"/"+ MM + "/" + yyyy;
            return validator.isValid(strData);
        }
    }
}

