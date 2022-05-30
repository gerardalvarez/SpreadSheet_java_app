package main.CapaDomini.Models;

import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * Classe abstracta Cela. Es abstracta ja que no es pot crear una instancia d'aquesta.
 * Serveix per englobar els atributs de les classes TextCela, DataCela i Numero.
 * @author Gerard Castell
 */
public abstract class Cela{

    //VARIABLES
    /**
     * La id serveix per poder referenciar una Cela en una posicio d'un Full.
     */
    protected  AbstractMap.SimpleEntry<Integer , Integer> id;
    /**
     * El resultat final es l'String on es guarda el resultat que apareixerà mostrat a la Cela
     */
    protected  String resultat_final;
    /**
     * color de fons d'una Cela
     */
    protected  Color colorFons = new Color(255,255,255);
    /**
     * color de la lletra d'una Cela
     */
    protected  Color colorLletra = new Color(0);
    protected  CelaEnum designedType;
    /**
     * Serveix per guardar de quin tipus es una Cela: Data, Text o Numerica.
     */
    protected  String type;
    /**
     * Array on es guarda quines celes estan observant (Referencies) a la Cela.
     */
    protected ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors; //Para avisarles si yo cambio

    //CREADORA

    /**
     * Creadora Simple de Cela
     * @param id valor que identifica la posicio de una cela al Full
     * @param resultat resultat que es col·loca a resultat final.
     */
    public Cela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) {
        this.id = id;
        this.resultat_final = resultat;
        type = calculaTipus();
        observadors= new ArrayList<>();
    }

    /**
     * Creadora Complexe de Cela
     * @param id valor que identifica la posicio de una cela al Full
     * @param resultat resultat que es col·loca a resultat final.
     * @param cf Color de Fons Cela
     * @param cl Color del Text de una Cela
     * @param dt designed type
     * @param t Tipus d'una Cela
     * @param obs Observadors que té una Cela
     */
    public Cela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat, Color cf, Color cl, CelaEnum dt, String t, ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> obs) {
        this.id = id;
        this.resultat_final = resultat;
        this.colorFons= cf;
        this.colorLletra= cl;
        this.designedType= dt;
        this.type= t;
        this.observadors= obs;
    }

    //GETTERS AND SETTERS
    public AbstractMap.SimpleEntry<Integer, Integer> getId() {return id;}
    public void setId(AbstractMap.SimpleEntry<Integer, Integer> id) {this.id = id;}
    public String getResultatFinal() {return resultat_final;}
    public void setResultatFinal(String resultat) {this.resultat_final = resultat;}
    public Color getColorFons() {return colorFons;}
    public void setColorFons(Color colorFons) {this.colorFons = colorFons;}
    public Color getColorLletra() {return colorLletra;}
    public void setColorLletra(Color colorLletra) {this.colorLletra = colorLletra;}
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    public void setObservadors(ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors) {this.observadors = observadors;}
    public void newObserver(AbstractMap.SimpleEntry<Integer, Integer> newO){observadors.add(newO);}
    public ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> getObservadors(){ return observadors;}

    //PUBLIC FUNCTIONS

    /**
     * Classe que calcula de quin tipus es una Cela
     * @return Retorna si la Cela es Numero, Text o Data
     */
    public String calculaTipus(){
        String Tipus = "text";
        if(resultat_final == null)return Tipus;
        //else if (resultat.substring(0,1) == "=") evaluar_ref();
        else if(isNumerical(resultat_final))Tipus = "numeric";
        else if(isData(resultat_final))Tipus = "date";
        return Tipus;
    }

    /**
     * Busca si el resultat Final conte un string en concret
     * @param element String que es mirara si es conte
     * @return Retorna True si el resultat final conte l'String, si no fals
     */
    public Boolean buscarElement(String element){
        return resultat_final.contains(element);
    }

    /**
     * Funcio que conta quantes paraules te el resultat final de la Cela
     * @return Un string amb el nombre de paraules precedir de "Nombre de paraules: "
     */
    public String countWords(){
        StringTokenizer st = new StringTokenizer(resultat_final);
        return ("Nombre de paraules: " + st.countTokens());
    }

    /**
     * Funcio que conta quants caracters te el resultat final de la Cela
     * @return Un string amb el nombre de caracters precedir de "Nombre de caracters: "
     */
    public String countChars(){
        return "Nombre de caràcters " + resultat_final.replace(" ", "").length();
    }

    /**
     * Funcio que conta quantes vocals te el resultat final de la Cela
     * @return Un string amb el nombre de vocals precedir de "Nombre de vocals: "
     */
    public String countVowels(){
        int count = 0;
        for (char ch : resultat_final.toCharArray()){
            if(ch == 'a'|| ch == 'e'|| ch == 'i' ||ch == 'o' ||ch == 'u' ||ch == 'A'|| ch == 'E'|| ch == 'I' ||ch == 'O' ||ch == 'U')count ++;
        }
        return ("Nombre vocals: "+ count);
    }

    //PRIVATE FUNCTIONS

    /**
     * La Funcio privada isNumerical retorna un Boolean dient si un string es numeric o no
     * @param strNum String que s'evaluara si es numeric
     * @return Retorna true si strNum es numeric, si no fals.
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

    /**
     * La Funcio privada isData retorna un Boolean dient si un string es de tipus Data o no
     * @param strData String que s'evaluara si es data
     * @return Retorna true si strData es Data, si no fals.
     */
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

