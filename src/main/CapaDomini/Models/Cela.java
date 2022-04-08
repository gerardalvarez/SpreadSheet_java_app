package main.CapaDomini.Models;

import java.awt.*;
import java.util.AbstractMap;

abstract class Cela {
    //VARIABLES
    private AbstractMap<Integer, Integer> id;
    private String contingut;
    private Color colorFons;
    private Color colorLletra;
    private CelaEnum designedType; // En un futur fer enum

    //GETTERS AND SETTERS
    public CelaEnum getDesignedType() {return designedType;}
    public void setDesignedType(CelaEnum designedType) {this.designedType = designedType;}
    public AbstractMap<Integer, Integer> getId() {return id;}
    public void setId(AbstractMap<Integer, Integer> id) {this.id = id;}
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
        //else if(isData(contingut))Tipus = "data";
        return Tipus;
    }

    //PRIVATE
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
        if(contingut.length() == 10){
            if(contingut.charAt(3) == ('/') && contingut.charAt(5) == '/'){
                return true;
            }
        }
        return false;
    }
}

