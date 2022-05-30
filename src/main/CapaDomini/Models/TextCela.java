package main.CapaDomini.Models;
import java.util.AbstractMap;

/**
 * Classe de TextCela. Classe que extend de la classe Cela.
 * Aquesta esta feta per guardar totes les caracteristiques propies de Text
 * @author Gerard Castell
 */
public class TextCela extends Cela{

    //CONSTRUCTORA
    /**
     * Creadora crea la TextCela amb id i resultat
     * @param id id cela
     * @param resultat string que es mostra al full de calcul
     */
    public TextCela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat){
        super(id, resultat);
    }

    /**
     * Creadora de TextCela que crea tambe la Cela amb els seus parametres
     * @param dd TextCela
     * @param id id de la Cela
     */
    public TextCela(TextCela dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
        super(id,dd.getResultatFinal());
        this.type=dd.getType();
        this.colorFons=dd.getColorFons();
        this.colorLletra=dd.getColorLletra();
    }

    //FUNCIONS PUBLIQUES

    /**
     * Funcio que retorna si un element pertany al string del resultat.
     * @param element String que es mirara si es conte.
     * @return true si l'element apareix, fals si no.
     */
    public Boolean buscarElement(String element){
        return resultat_final.contains(element);
    }

    /**
     * Funcio que si es troba que el resultat conte un element el canvia per un altre.
     * @param element string a buscar
     * @param change String a reemplaçar
     */
    public void remplacarElement(String element, String change){
       resultat_final= resultat_final.replaceAll(element,change);
    }

    /**
     * Funcio que pasa el resultat en majuscules
     */
    public void AllMayus(){
        resultat_final =  resultat_final.toUpperCase();
    }

    /**
     * Funcio que posa el resultat en minuscules
     */
    public void AllMinus(){
        resultat_final =  resultat_final.toLowerCase();
    }


}
