package main.CapaDomini.Models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.AbstractMap;

import static java.lang.Math.PI;

public class Numero extends Cela{
    private BigDecimal resultat;
    private Boolean arrodonit;
    private Integer num_Decimals;
    private Tipus_Numero tipus;

    //Constructor


    public Numero(AbstractMap.SimpleEntry<Integer, Integer> id, BigDecimal resultat, Boolean arrodonit, Integer num_Decimals, Tipus_Numero tipus) {
        super(id, resultat.toString());
        this.resultat = resultat;
        this.arrodonit = arrodonit;
        this.num_Decimals = num_Decimals;
        this.tipus = tipus;

    }


    public Numero(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) {
        super(id, String.valueOf(resultat));
        this.resultat = new BigDecimal(resultat);
        resultat_final=resultat.toString();
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
    }

    //Mètodes


    public void incrementar () {
        this.resultat = this.resultat.add(new BigDecimal(1));
    }

    public void reduir () {
        this.resultat = this.resultat.add(new BigDecimal(-1));
    }

    public void potencia(Double exp) {
        double b = this.resultat.doubleValue();
        this.resultat = BigDecimal.valueOf(Math.pow(b, exp));
        this.resultat = this.resultat.stripTrailingZeros();
    }

    public void valor_absolut() {
        this.resultat = this.resultat.abs();
    }

    public void arrel (Double exp) {
        double b = this.resultat.doubleValue();
        this.resultat = BigDecimal.valueOf(Math.pow(b, 1/exp));
        this.resultat = this.resultat.stripTrailingZeros();
    }

    //funció a descartar un cop estigui fet el controller
    public void setDecimals() {
        if(this.arrodonit) {
            this.resultat = this.resultat.setScale(this.num_Decimals, RoundingMode.HALF_UP);
        }
        else {
            this.resultat = this.resultat.setScale(this.num_Decimals, RoundingMode.DOWN);
        }
    }

    public void conversio(Tipus_Numero convertit) {
        switch (this.tipus) {
            case celsius:
                switch(convertit) {
                    case fahrenheit:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1.8));
                        this.resultat = this.resultat.add(BigDecimal.valueOf(32));
                        break;

                    case kelvin:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(273.15));
                        break;
                }
                break;

            case fahrenheit:
                switch(convertit) {
                    case celsius:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(-32));
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf((double) 5/9));

                        break;

                    case kelvin:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(-32));
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf((double)5/9));
                        this.resultat = this.resultat.add(BigDecimal.valueOf(273.15));
                        break;
                }
                break;

            case kelvin:
                switch(convertit) {
                    case celsius:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(-273.15));
                        break;

                    case fahrenheit:
                        this.resultat = this.resultat.add(BigDecimal.valueOf(-273.15));
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1.8));
                        this.resultat = this.resultat.add(BigDecimal.valueOf(32));
                        break;
                }
                break;

            case km:
                switch(convertit) {
                    case m:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1000));
                        break;

                    case cm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(100000));
                        break;

                    case mm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1000000));
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(39370.07874));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(3280.839895));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1093.613298));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case miles:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.62137119));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;
                }
                break;

            case m:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1000), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case cm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(100));
                        break;

                    case mm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1000));
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(39.37007874));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(3.280839895));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1.093613298));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case miles:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.00062137119));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;
                }
                break;

            case cm:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(100000), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(100), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case mm:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(10));
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.3937007874));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.03280839895));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.01093613298));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case miles:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.0000062137119));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;
                }
                break;

            case mm:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1000000), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1000), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(10), 20, RoundingMode.HALF_UP);
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.03937007874));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.003280839895));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.001093613298));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case miles:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(0.00000062137119));
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;
                }
                break;

            case inches:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(39370.07874), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(39.37007874), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.3937007874), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case mm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.03937007874), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case feet:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(12), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case yards:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(36), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case miles:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(63360), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;
                }
                break;

            case feet:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(3280.839895), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(3.280839895), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.03280839895), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case mm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.003280839895), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(12));
                        break;

                    case yards:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(3), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case miles:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(5280), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;
                }
                break;


            case yards:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1093.613298), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1.093613298), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.01093613298), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case mm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.001093613298), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(36));
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(3));
                        break;

                    case miles:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(1760), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;
                }
                break;

            case miles:
                switch(convertit) {
                    case km:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.62137119), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case m:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.00062137119), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case cm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.0000062137119), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case mm:
                        this.resultat = this.resultat.divide(BigDecimal.valueOf(0.00000062137119), 20, RoundingMode.HALF_UP);
                        this.resultat = this.resultat.stripTrailingZeros();
                        break;

                    case inches:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(63360));
                        break;

                    case feet:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(5280));
                        break;

                    case yards:
                        this.resultat = this.resultat.multiply(BigDecimal.valueOf(1760));
                        break;
                }
                break;


            case graus:
                if (convertit == Tipus_Numero.radiants) {
                    this.resultat = this.resultat.multiply(BigDecimal.valueOf(PI/180));
                }
                break;

            case radiants:
                if (convertit == Tipus_Numero.graus) {
                    this.resultat = this.resultat.multiply(BigDecimal.valueOf((double) 180/PI));
                }
                break;
        }
    }


    //Setters

    public void setResultat(BigDecimal resultat) {
        this.resultat = resultat;
    }

    public void setArrodonit(Boolean arrodonit) {
        this.arrodonit = arrodonit;
    }

    public void setNum_Decimals(Integer num_Decimals) {
        this.num_Decimals = num_Decimals;
    }

    public void setTipus(Tipus_Numero tipus) {
        this.tipus = tipus;
    }

    //Getters

    public BigDecimal getResultat() {
        return resultat;
    }

    public Boolean getArrodonit() {
        return arrodonit;
    }

    public Integer getNum_Decimals() {
        return num_Decimals;
    }

    public Tipus_Numero getTipus() {
        return tipus;
    }
}

