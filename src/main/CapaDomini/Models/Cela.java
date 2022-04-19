package main.CapaDomini.Models;

import java.awt.*;
import java.util.AbstractMap;

public abstract class Cela {
    //VARIABLES
    protected  AbstractMap.SimpleEntry<Integer , Integer> id;
    protected  String resultat_final;
    protected  Color colorFons = new Color(255,255,255);
    protected  Color colorLletra = new Color(0);
    protected  CelaEnum designedType; // En un futur fer enum
    protected  String type;

    //CREADORA
    public Cela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) {
        this.id = id;
        this.resultat_final = resultat;
        type = calculaTipus();
    }


    //GETTERS AND SETTERS
    public CelaEnum getDesignedType() {return designedType;}
    public void setDesignedType(CelaEnum designedType) {this.designedType = designedType;}
    public AbstractMap.SimpleEntry<Integer, Integer> getId() {return id;}
    public void setId(AbstractMap.SimpleEntry<Integer, Integer> id) {this.id = id;}
    public String getResultatFinal() {return resultat_final;}
    public void setResultat(String resultat) {this.resultat_final = resultat;}
    public Color getColorFons() {return colorFons;}
    public void setColorFons(Color colorFons) {this.colorFons = colorFons;}
    public Color getColorLletra() {return colorLletra;}
    public void setColorLletra(Color colorLletra) {this.colorLletra = colorLletra;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    //PUBLIC FUNCTIONS
    public String calculaTipus(){
        String Tipus = "text";
        if(resultat_final == null)return Tipus;
        //else if (resultat.substring(0,1) == "=") evaluar_ref();
        else if(isNumerical(resultat_final))Tipus = "numeric";
        else if(isData(resultat_final))Tipus = "date";
        return Tipus;
    }

    //PRIVATE FUNCTIONS
    /*
    private void evaluar_ref(){
        if (contingut.)
    }
     */

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

            String MM = PublicFuntions.monthToData(monthText);
            if(MM.equals("null"))return false;

            String yyyy = strData.substring(size-4);

            String date = dd+"/"+ MM + "/" + yyyy;
            return validator.isValid(date);
        }
    }

}

