/**
 * Implementacio de la classe DateValidator
 * @file DateValidator.java
 * @author Gerard Castell
 * @date 2022
 */
package main.CapaDomini.Models;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Classe que permet validar si una data compleix el format correcte o no
 */
public class DateValidator implements DataValidator {
    /**
     * Atribut on es pot indicar el format que ens interessa. Ex: dd/mm/yyyy o mm/dd/yyyy o ...
     */
    private final String dateFormat;

    /**
     * Creadora de la classe
     * @param dateFormat format en el que volem validar dates
     */
    public DateValidator(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Funció que fara la validació de dates
     * @param dateStr String amb la nostra data a validar
     * @return True si compleix format data, si no fals
     */
    @Override
    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}