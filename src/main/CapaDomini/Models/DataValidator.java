/**
 * Implementacio de interficie DataValidator
 * @file DataValidator.java
 * @author Gerard Castell
 * @date 2022
 */
package main.CapaDomini.Models;

/**
 * Interficie DataValidator amb funcio isValid.
 * Validar Dates
 */
public interface DataValidator {
    boolean isValid(String dateStr);
}