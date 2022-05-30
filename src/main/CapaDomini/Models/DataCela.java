package main.CapaDomini.Models;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Objects;
import static main.CapaDomini.Models.PublicFuntions.monthToData;

/**
 * Classe de DataCela. Classe que extend de la classe Cela.
 * Aquesta esta feta per guardar totes les caracteristiques propies de les dates
 * @author Gerard Castell
 */
public class DataCela extends Cela {

    //VARIABLES
    /**
     * Guarda el format de la data en format normal: dd/mm/yyyy
     */
    private String dateFormat = "null";
    /**
     *  Guarda el format de la data en format text: dd del MES(mm) del yyyy
     */
    private String TextFormat = "null";
    /**
     * On tenim la data guardada
     */
    private LocalDate date;

    //GETTERS AND SETTERS
    public LocalDate getDate() {return date;}
    public void setDate(LocalDate date) {this.date = date;}

    //CREADORA

    /**
     * Creadora del Classe DataCela
     * @param id id de la cela
     * @param contingut contingut que trobem a la cela del que s'extreu la data
     */
    public DataCela(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut){
        super(id, contingut);
        DataValidator validator = new DateValidator("dd/MM/yyyy");
        if(validator.isValid(contingut)){
            dateFormat = contingut;
            date = LocalDate.of(Integer.parseInt(dateFormat.substring(6)),Integer.parseInt(dateFormat.substring(3,5))
                    ,Integer.parseInt(dateFormat.substring(0,2)));
        }
        else{
            TextFormat = contingut;
            int size = TextFormat.length();
            int dia = Integer.parseInt(TextFormat.substring(0,2));
            int mes = Integer.parseInt(monthToData(TextFormat.substring(2,size-4)));
            int any = Integer.parseInt(TextFormat.substring(size-4));
            date = LocalDate.of(any,mes,dia);
        }
    }

    /**
     * Creador de la Classe Cela que tambe omple els camps de la Cela
     * @param dd DataCela
     * @param id id de la Cela
     */
    public DataCela(DataCela dd, AbstractMap.SimpleEntry<Integer, Integer> id) {
        super(id,dd.getResultatFinal());
        String contingut =dd.getResultatFinal();
        this.type=dd.getType();
        this.colorFons=dd.getColorFons();
        this.colorLletra=dd.getColorLletra();
        DataValidator validator = new DateValidator("dd/MM/yyyy");
        if(validator.isValid(contingut)){
            dateFormat = contingut;
            date = LocalDate.of(Integer.parseInt(dateFormat.substring(6)),Integer.parseInt(dateFormat.substring(3,5))
                    ,Integer.parseInt(dateFormat.substring(0,2)));
        }
        else{
            TextFormat = contingut;
            int size = TextFormat.length();
            int dia = Integer.parseInt(TextFormat.substring(0,2));
            int mes = Integer.parseInt(monthToData(TextFormat.substring(2,size-4)));
            int any = Integer.parseInt(TextFormat.substring(size-4));
            date = LocalDate.of(any,mes,dia);
        }
    }

    //PUBLIC FUNCTIONS

    /**
     * La Funcio Change to Text canvia el contingut que es veu al Full de calcul pel format de Text
     */
    public void changeToText(){
        if(!Objects.equals(TextFormat, "null")){
           resultat_final = TextFormat;
        }
        else{
            resultat_final = dateFormat.substring(0,2) + PublicFuntions.monthToText(dateFormat.substring(3,5))+ dateFormat.substring(6);
        }
    }

    /**
     * La Funcio Change to Text canvia el contingut que es veu al Full de calcul pel format de Data
     */
    public void changeToDate(){
        if(!Objects.equals(dateFormat, "null")){
            resultat_final = dateFormat;
        }
        else{
            int size = TextFormat.length();
            resultat_final = TextFormat.substring(0,2) + "/" + monthToData(TextFormat.substring(2,size-4)) +
                    "/" + TextFormat.substring(size-4);
        }
    }

    /**
     * Funcio que retorna el dia de la Data
     * @return String amb el dia
     */
    public String getDia(){
        return String.valueOf(date.getDayOfMonth());
    }

    /**
     * Funcio que retorna el mes de la Data
     * @return String amb el mes
     */
    public String getMes(){
        return String.valueOf(date.getMonth());
    }

    /**
     * Funcio que retorna el any de la Data
     * @return String amb el any
     */
    public String getAny(){
        return String.valueOf(date.getYear());
    }

    /**
     * Funcio que retorna el dia de la setmana de la Data
     * @return String amb el dia de la setmana
     */
    public String getWeekDay(){
        return String.valueOf(date.getDayOfWeek());
    }
}
