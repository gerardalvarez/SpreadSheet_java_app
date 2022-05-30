package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;

import static java.lang.Math.PI;

/**
 * Aquesta classe representa una cel·la de tipus numero i conte totes les operacions propies que es puguin fer en ella
 * @author Marc Castells
 */

public class Numero extends Cela implements Cloneable{
    /**
     * Resultat del Numero
     */
    protected BigDecimal resultat;
    /**
     * Boolea que indica si arrodonim o trunquem
     */
    protected Boolean arrodonit;
    /**
     * Numero de decimals a mostrar
     */
    protected Integer num_Decimals;
    /**
     * Tipus de cel·la numero
     */
    protected Tipus_Numero tipus;


    /**
     * Creadora de la classe numero
     * @param id Identificador de la cel·la
     * @param resultat El valor de la cel·la
     * @param arrodonit Ens indica si esta arrodonit o no el valor
     * @param num_Decimals El numero de decimals a mostrar per pantalla
     * @param tipus Tipus de cela de numero que es
     */
    public Numero(AbstractMap.SimpleEntry<Integer, Integer> id, BigDecimal resultat, Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus) {
        super(id, resultat.toString());
        this.resultat = resultat;
        this.arrodonit = arrodonit;
        this.num_Decimals = num_Decimals;
        this.tipus = tipus;
        ActualitzarResultatFinal();
    }


    public Numero(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) {
        super(id, String.valueOf(resultat));
        this.resultat = new BigDecimal(resultat);
        ActualitzarResultatFinal();
    }

    public Numero(Numero dd,AbstractMap.SimpleEntry<Integer,Integer> id) {
        super(id,dd.getResultatFinal());
        this.resultat = dd.getResultat();
        this.arrodonit = dd.getArrodonit();
        this.num_Decimals = dd.getNum_Decimals();
        this.tipus = dd.getTipus();
        this.colorFons=dd.getColorFons();
        this.colorLletra=dd.getColorLletra();
        this.type=dd.getType();
        ActualitzarResultatFinal();
    }

    //Mètodes

    /**
     * Funcio que incrementa en 1 el numero
     */
    public void incrementar () {
        this.resultat = this.resultat.add(new BigDecimal(1));
        ActualitzarResultatFinal();
    }

    /**
    Funcio que redueix en 1 el numero
     */
    public void reduir () {
        this.resultat = this.resultat.add(new BigDecimal(-1));
        ActualitzarResultatFinal();
    }

    /**
     * Funcio que aplica una potencia amb un exponent donat al numero
     * @param exp exponent de l'operacio
     */
    public void potencia(Double exp) {
        double b = this.resultat.doubleValue();
        this.resultat = BigDecimal.valueOf(Math.pow(b, exp));
        this.resultat = this.resultat.stripTrailingZeros();
        ActualitzarResultatFinal();
    }

    /**
     * Transforma al numero al seu valor absolut
     */
    public void valor_absolut() {
        this.resultat = this.resultat.abs();
        ActualitzarResultatFinal();
    }

    /**
     * Funcio que aplica una arrel amb un exponent donat al numero
     * @param exp exponent de l'operacio
     */
    public void arrel (Double exp) {
        double b = this.resultat.doubleValue();
        this.resultat = BigDecimal.valueOf(Math.pow(b, 1/exp));
        this.resultat = this.resultat.stripTrailingZeros();
        ActualitzarResultatFinal();
    }

    /**
     * Funcio que realitza una conversió de tipus
     * @param convertit nom del tipus al que s'ha de convertir
     */
    public void conversio(Tipus_Numero convertit) {
        switch (this.tipus) {
            case celsius:
                switch(convertit) {
                    case fahrenheit:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1.8));
                        this.resultat = this.resultat.add(BigDecimal.valueOf(32));
                        ActualitzarResultatFinal();
                        break;

                    case kelvin:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(273.15));
                        ActualitzarResultatFinal();
                        break;
                }
                break;

            case fahrenheit:
                switch(convertit) {
                    case celsius:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(-32));
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf((double) 5/9));
                        ActualitzarResultatFinal();
                        break;

                    case kelvin:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(-32));
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf((double)5/9));
                        this.resultat = this.resultat.add(BigDecimal.valueOf(273.15));
                        ActualitzarResultatFinal();
                        break;
                }
                break;

            case kelvin:
                switch(convertit) {
                    case celsius:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(-273.15));
                        ActualitzarResultatFinal();
                        break;

                    case fahrenheit:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(-273.15));
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1.8));
                        this.resultat = this.resultat.add(BigDecimal.valueOf(32));
                        ActualitzarResultatFinal();
                        break;
                }
                break;

            case km:
                switch(convertit) {
                    case m:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1000));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case cm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(100000));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case mm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1000000));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(39370.07874));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(3280.839895));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1093.613298));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case miles:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.62137119));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;
                }
                break;

            case m:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1000), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case cm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(100));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case mm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1000));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(39.37007874));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(3.280839895));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1.093613298));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case miles:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.00062137119));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;
                }
                break;

            case cm:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(100000), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(100), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case mm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(10));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.3937007874));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.03280839895));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.01093613298));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case miles:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.0000062137119));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;
                }
                break;

            case mm:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1000000), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1000), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(10), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.03937007874));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.003280839895));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.001093613298));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case miles:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.00000062137119));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;
                }
                break;

            case inches:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(39370.07874), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(39.37007874), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.3937007874), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case mm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.03937007874), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case feet:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(12), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case yards:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(36), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case miles:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(63360), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;
                }
                break;

            case feet:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(3280.839895), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(3.280839895), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.03280839895), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case mm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.003280839895), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(12));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case yards:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(3), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case miles:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(5280), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;
                }
                break;


            case yards:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1093.613298), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1.093613298), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.01093613298), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case mm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.001093613298), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(36));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(3));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case miles:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1760), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;
                }
                break;

            case miles:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.62137119), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.00062137119), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.0000062137119), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case mm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.00000062137119), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(63360));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(5280));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1760));
                        this.resultat = this.resultat.stripTrailingZeros();
                        ActualitzarResultatFinal();
                        break;
                }
                break;


            case graus:
                if (convertit == Tipus_Numero.radiants) {
                    this.resultat = this.resultat.multiply(BigDecimal.valueOf(PI/180));
                    this.resultat = this.resultat.stripTrailingZeros();
                    ActualitzarResultatFinal();
                }
                break;

            case radiants:
                if (convertit == Tipus_Numero.graus) {
                    this.resultat = this.resultat.multiply(BigDecimal.valueOf((double) 180/PI));
                    this.resultat = this.resultat.stripTrailingZeros();
                    ActualitzarResultatFinal();
                }
                break;
        }
    }


    //Setters

    /**
     * Setter que actualitza el resultat
     * @param resultat valor del numero el qual s'ha d'actualitzar
     */
    public void setResultat(BigDecimal resultat) {
        this.resultat = resultat;
        ActualitzarResultatFinal();
    }

    /**
     * Setter que actulitza el arrodonit
     * @param arrodonit boolea que indica si arrodonim o no
     */
    public void setArrodonit(Boolean arrodonit) {
        this.arrodonit = arrodonit;
        ActualitzarResultatFinal();
    }

    /**
     * Setter que actulitza el numero de decimals
     * @param num_Decimals numero de decimals
     */
    public void setNum_Decimals(Integer num_Decimals) {
        this.num_Decimals = num_Decimals;
        ActualitzarResultatFinal();
    }

    /**
     * Setter que canvia el tipus del numero
     * @param tipus nom del tipus a canviar
     */
    public void setTipus(Tipus_Numero tipus) {
        this.tipus = tipus;
    }

    //Getters

    /**
     * Getter per obetenir el valor del numero
     * @return Un BigDecimal del numero que tenim
     */
    public BigDecimal getResultat() {
        return this.resultat;
    }

    /**
     * Getter per saber si hem d'arrodnir o no
     * @return Un boolea que indica si arrodonim o no
     */
    public Boolean getArrodonit() {
        return arrodonit;
    }

    /**
     * Getter per obtenir el numero de decimals
     * @return Un Integer amb el numero de decimals
     */
    public Integer getNum_Decimals() {
        return num_Decimals;
    }

    /**
     * Getter per obtenir el tipus
     * @return Retona un Tipus_Numero
     */
    public Tipus_Numero getTipus() {
        return tipus;
    }

    /**
     * Funcio que actulitzar el resultatFinal de la cel·la pare
     */
    private void ActualitzarResultatFinal() {
        BigDecimal numero = resultat;
        if (arrodonit) {
            numero = numero.setScale(num_Decimals, RoundingMode.HALF_UP);
        }
        else {
            numero = numero.setScale(num_Decimals, RoundingMode.DOWN);
        }
        super.setResultatFinal(numero.toString());
    }
}

