package main.CapaDomini.Models;


import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Full {
    private String nom;
    private Integer Num_Columnes;
    private Integer Num_Files;
    private HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela> Celes;

    //Constructor
    public Full(String n, Integer nc, Integer nf) {
        Celes = new HashMap<>();
        this.nom = n;
        this.Num_Columnes = nc;
        this.Num_Files = nf;
        HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela> cel= new HashMap<>();
        for (Integer i=0; i < Num_Files; ++i) {
            for (Integer j = 0; j < Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(i, j);
                Celes.put(idc, new TextCela(idc, ""));
            }
        }
    };

    public Full(Integer nc, Integer nf) {
        this.nom = "Full sense nom";
        this.Num_Columnes = nc;
        this.Num_Files = nf;
        HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela> cel= new HashMap<>();
        for (Integer i=0; i < Num_Files; ++i) {
            for (Integer j = 0; j < Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(i, j);
                cel.put(idc, new TextCela(idc, ""));
            }
        }
        this.Celes= cel;
    };

    //Setters
    public void SetNom(String n){
        this.nom= n;
    };
    public String getNom(){return this.nom;}

    //Mètodes Públics
    public void Afegir_Fila(Integer nf) {
        if (nf <= this.Num_Files-1) IncrementarIndexFila(nf);
        ++this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(nf,i);
            Celes.put(idc, new TextCela(idc, ""));
            ++i;
        }
    };

    public void Afegir_Columna(Integer nc) {
        if (nc <= this.Num_Columnes-1) IncrementarIndexCol(nc);
        ++this.Num_Columnes;
        Integer i= 0;
        while (i < this.Num_Files) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,nc);
            Celes.put(idc, new TextCela(idc, ""));
            ++i;
        }
    };

    public void Eliminar_Fila(Integer nf) {
        if (nf <= this.Num_Files-1) DecrementarIndexFila(nf);
        --this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(nf,i);
            this.Celes.remove(idc);
            ++i;
        }
    };

    public void Eliminar_Columna(Integer nc) {
        if (nc <= this.Num_Columnes-1) DecrementarIndexCol(nc);
        --this.Num_Columnes;
        Integer i= 0;
        while (i < this.Num_Files) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,nc);
            this.Celes.remove(idc);
            ++i;
        }
    };

    public void Esborrar_Celes(ArrayList<Cela> celes) {
        int i= 0;
        while (i < celes.size()){
            AbstractMap.SimpleEntry<Integer, Integer> idc= celes.get(i).getId();
            this.Celes.get(idc).setResultatFinal("");
            ++i;
        }
    };

    public void Modifica_Cela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) {
        int size = resultat.length();
        String a = PublicFuntions.calculaTipus(resultat);
        if(Objects.equals(a, "numeric")){
            if(Objects.equals(this.Celes.get(id).getType(), "numeric")){
                this.Celes.get(id).setResultatFinal(resultat);
            }
            else{
                Cela cel = this.Celes.get(id);
                Color colorFons = cel.getColorFons();
                Color colorLletra = cel.getColorLletra();
                String type = cel.getType();
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new Numero(id, new BigDecimal(resultat), true, 2, Tipus_Numero.numero));
                this.Celes.get(id).setType(type);
                this.Celes.get(id).setColorFons(colorFons);
                this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
        }
        else if(Objects.equals(a, "date")){
            Cela cel = this.Celes.get(id);
            Color colorFons = cel.getColorFons();
            Color colorLletra = cel.getColorLletra();
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
            this.Celes.replace(id, new DataCela(id, resultat));
            this.Celes.get(id).setType("date");
            this.Celes.get(id).setColorFons(colorFons);
            this.Celes.get(id).setColorLletra(colorLletra);
            this.Celes.get(id).setObservadors(observadors);
        }

        /*else if(size == 4 && resultat.startsWith("=#")  && isNumerical(resultat.substring(2))){
            Integer fil = Integer.parseInt(resultat.substring(2,3));
            Integer col = Integer.parseInt(resultat.substring(3));
            AbstractMap.SimpleEntry<Integer, Integer> he = new AbstractMap.SimpleEntry<Integer,Integer>(fil,col);
            if(Objects.equals(this.Celes.get(he).getType(), "date")){
                //CREAR CELA DATA REF
            }
            else if(Objects.equals(this.Celes.get(he).getType(), "text")){
                //CREAR  CELA TEXT REF
            }
            else{
                //CREAR NUM CELA REF
            }
            this.Celes.get(he).newObserver(id);
        }
        else if(size > 4 && resultat.charAt(0) == '='){
            String func = PublicFuntions.getOper(resultat.substring(1,4));
            if(func.equals("MAY") || func.equals("MIN")){
                //CREAR FUNCION TEXT;
            }
            else if(!func.equals("NULL")){
                //CREAR FUNCION NUM
            }
            //MIRAR OBSERVERS I PONERLOS EN LA CELDA QUE TOCA
            else{
                if(Objects.equals(this.Celes.get(id).getType(), "text")){
                    this.Celes.get(id).setResultatFinal(resultat);
                }
                else{
                    Cela cel = this.Celes.get(id);
                    Color colorFons = cel.getColorFons();Color colorLletra = cel.getColorLletra();
                    String type = cel.getType();ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                    this.Celes.replace(id, new TextCela(id, resultat));
                    this.Celes.get(id).setType(type);
                    this.Celes.get(id).setColorFons(colorFons);this.Celes.get(id).setColorLletra(colorLletra);
                    this.Celes.get(id).setObservadors(observadors);
                }
            }
        }*/
        else {
            if(Objects.equals(this.Celes.get(id).getType(), "text")){
                this.Celes.get(id).setResultatFinal(resultat);
            }
            else{
                Cela cel = this.Celes.get(id);
                Color colorFons = cel.getColorFons();Color colorLletra = cel.getColorLletra();
                String type = cel.getType();ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new TextCela(id, resultat));
                this.Celes.get(id).setType(type);
                this.Celes.get(id).setColorFons(colorFons);this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
        }
        //AQUI HAY QUE ACTUALIZAR TODOS LOS OBSERVERS DE LA NUEVA CELDA SI ESTA CANVIA DE TIPO TAMBIEN HABRIA QUE TENERLO
        //EN CUENTA
    }

    public void ModificaCelaRef(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat, String pareType,  AbstractMap.SimpleEntry<Integer, Integer> pare){
        if(Objects.equals(pareType, "date")){
            DataCela c =  (DataCela) this.Celes.get(pare);
            Cela cel = this.Celes.get(id);
            Color colorFons = cel.getColorFons();
            Color colorLletra = cel.getColorLletra();
            String dateFormat = c.getDataFormat();
            String TextFormat = c.getTextFormat();
            LocalDate date = c.getDate();
            String Rf = this.Celes.get(pare).getResultatFinal();
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
            this.Celes.replace(id, new CelaRefData(id,resultat,Rf));
            this.Celes.get(id).setColorFons(colorFons);
            this.Celes.get(id).setColorLletra(colorLletra);
            this.Celes.get(id).setObservadors(observadors);
            this.Celes.get(id).setResultatFinal(Rf);
            ((DataCela) this.Celes.get(id)).setDataFormat(dateFormat);
            ((DataCela) this.Celes.get(id)).setTextFormat(TextFormat);
            ((DataCela) this.Celes.get(id)).setDate(date);
            String a = ((DataCela) this.Celes.get(id)).getDataFormat();
            System.out.println(a);
        }
        else if(Objects.equals(pareType, "text")){
            System.out.println("HEY");
            Cela cel = this.Celes.get(id);
            Color colorFons = cel.getColorFons();
            Color colorLletra = cel.getColorLletra();
            String Rf = this.Celes.get(pare).getResultatFinal();
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
            this.Celes.replace(id, new CelaRefText(id,resultat,Rf));
            this.Celes.get(id).setColorFons(colorFons);
            this.Celes.get(id).setColorLletra(colorLletra);
            this.Celes.get(id).setObservadors(observadors);
            this.Celes.get(id).setResultatFinal(Rf);
        }
        else{
            System.out.println("HEY");
            Numero c = (Numero) this.Celes.get(pare);
            Cela cel = this.Celes.get(id);
            Color colorFons = cel.getColorFons();
            Color colorLletra = cel.getColorLletra();
            Boolean arrodonit = c.getArrodonit();
            Integer num_Decimals = c.getNum_Decimals();
            Tipus_Numero tipus = c.getTipus();
            String Rf = this.Celes.get(pare).getResultatFinal();
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
            this.Celes.replace(id, new CelaRefNum(id,Rf,arrodonit, num_Decimals,  tipus, resultat));
            this.Celes.get(id).setColorFons(colorFons);
            this.Celes.get(id).setColorLletra(colorLletra);
            this.Celes.get(id).setObservadors(observadors);
            ((Numero) this.Celes.get(id)).setResultat(new BigDecimal(Rf));
            Numero a = (Numero) this.Celes.get(id);

        }
        if(!this.Celes.get(pare).getObservadors().contains(id))this.Celes.get(pare).newObserver(id);
    }

    public void ModificaCelaTextRefLong(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat,  AbstractMap.SimpleEntry<Integer, Integer> pare){
        Cela cel = this.Celes.get(id);
        Color colorFons = cel.getColorFons();
        Color colorLletra = cel.getColorLletra();
        String Rf = this.Celes.get(pare).getResultatFinal();
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
        this.Celes.replace(id, new CelaRefText(id,resultat,Rf));
        this.Celes.get(id).setColorFons(colorFons);
        this.Celes.get(id).setColorLletra(colorLletra);
        this.Celes.get(id).setObservadors(observadors);
        this.Celes.get(id).setResultatFinal(Rf);
        TextCela cd = (TextCela)this.Celes.get(id);
        if(resultat.substring(1, 4).equals("MAY"))cd.AllMayus();
        else cd.AllMinus();
        if(!this.Celes.get(pare).getObservadors().contains(id))this.Celes.get(pare).newObserver(id);
    }
    public void ModificaCelaNumRefLong(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat,  AbstractMap.SimpleEntry<Integer, Integer> pare){
        Numero c = (Numero) this.Celes.get(pare);
        Cela cel = this.Celes.get(id);
        Color colorFons = cel.getColorFons();
        Color colorLletra = cel.getColorLletra();
        Boolean arrodonit = c.getArrodonit();
        Integer num_Decimals = c.getNum_Decimals();
        Tipus_Numero tipus = c.getTipus();
        String Rf = this.Celes.get(pare).getResultatFinal();
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
        this.Celes.replace(id, new CelaRefNum(id,Rf,arrodonit, num_Decimals,  tipus, resultat));
        this.Celes.get(id).setColorFons(colorFons);
        this.Celes.get(id).setColorLletra(colorLletra);
        this.Celes.get(id).setObservadors(observadors);
        ((Numero) this.Celes.get(id)).setResultat(new BigDecimal(Rf));
        Numero a = (Numero) this.Celes.get(id);
        //AFEGIR AVALUAR
        ArrayList<Numero> sender = new ArrayList<>();
        AbstractMap.SimpleEntry<Integer, Integer> first = PublicFuntions.getIdRefText(resultat);
        AbstractMap.SimpleEntry<Integer, Integer> second = PublicFuntions.getIdRefNum(resultat);
        if(resultat.charAt(11)=='-' || resultat.substring(1, 4).equals("DIV")){
            sender.add((Numero) this.Celes.get(first));
            sender.add((Numero) this.Celes.get(second));
        }
        else{
            sender = getAllCeles(first,second);
        }
        ((Numero) this.Celes.get(id)).setResultat(avaluar(sender, resultat.substring(1,4)));
        //FUNCIO OBSERVER ES MOLT MES AMPLIA
        if(resultat.charAt(11)=='-' || resultat.substring(1, 4).equals("DIV")){
            if(!this.Celes.get(first).getObservadors().contains(id))this.Celes.get(first).newObserver(id);
            if(!this.Celes.get(second).getObservadors().contains(id))this.Celes.get(second).newObserver(id);
        }
        else{
            getAllObservers(id,first,second);
        }
    }
    private BigDecimal avaluar(ArrayList<Numero> sender, String oper){
        if(Objects.equals(oper, "DIV")){
            return sender.get(0).getResultat().divide(sender.get(1).getResultat());
        }
        else if(Objects.equals(oper, "AVG")){
            Bloc_celes bc= new Bloc_celes();
            return BigDecimal.valueOf(bc.calculaMitjana(sender));
        }
        else if(Objects.equals(oper, "DEV")){
            Bloc_celes bc= new Bloc_celes();
            return BigDecimal.valueOf(bc.calculaDesviació(sender));
        }
        else if(Objects.equals(oper, "VAR")) {
            Bloc_celes bc= new Bloc_celes();
            return BigDecimal.valueOf(bc.calculaVariança(sender));
        }
        else if(Objects.equals(oper, "MED")){
            Bloc_celes bc= new Bloc_celes();
            return BigDecimal.valueOf(bc.calculaMediana(sender));
        }

        else if(Objects.equals(oper, "MOD")){
            Bloc_celes bc= new Bloc_celes();
            return BigDecimal.valueOf(bc.calculaModa(sender));
        }
        else if(Objects.equals(oper, "MAX")){
            Bloc_celes bc= new Bloc_celes();
            return  BigDecimal.valueOf(bc.maxim(sender));
        }
        else if(Objects.equals(oper, "SUM")){
            Bloc_celes bc= new Bloc_celes();
            return  BigDecimal.valueOf(bc.suma(sender));
        }
        else if(Objects.equals(oper, "RES")){
            Bloc_celes bc= new Bloc_celes();
            return  BigDecimal.valueOf(bc.resta(sender));
        }
        else if(Objects.equals(oper, "PRO")){
            Bloc_celes bc= new Bloc_celes();
            return  BigDecimal.valueOf(bc.mult(sender));
        }
        return new BigDecimal(0);
    }

    private ArrayList<Numero> getAllCeles(AbstractMap.SimpleEntry<Integer, Integer> first , AbstractMap.SimpleEntry<Integer, Integer> second){
        Integer firstF,secondF,firstC, secondC;ArrayList<Numero> sender = new ArrayList<>();
        if (first.getKey() <=  second.getKey()){
            firstF = first.getKey();
            secondF = second.getKey();
        }
        else{
            firstF = second.getKey();
            secondF = first.getKey();
        }
        if (first.getValue() <=  second.getValue()){
            firstC = first.getValue();
            secondC = second.getValue();
        }
        else{
            firstC = second.getValue();
            secondC = first.getValue();
        }

        for(int i = firstF; i <= secondF; i++){
            for(int j = firstC; j <= secondC ;j++){
                sender.add((Numero) this.Celes.get(new AbstractMap.SimpleEntry<>(i,j)));
            }
        }
        return sender;
    }

    private void getAllObservers( AbstractMap.SimpleEntry<Integer, Integer> fill,AbstractMap.SimpleEntry<Integer, Integer> first , AbstractMap.SimpleEntry<Integer, Integer> second){
        Integer firstF,secondF,firstC, secondC;ArrayList<Numero> sender = new ArrayList<>();
        if (first.getKey() <=  second.getKey()){
            firstF = first.getKey();
            secondF = second.getKey();
        }
        else{
            firstF = second.getKey();
            secondF = first.getKey();
        }
        if (first.getValue() <=  second.getValue()){
            firstC = first.getValue();
            secondC = second.getValue();
        }
        else{
            firstC = second.getValue();
            secondC = first.getValue();
        }

        for(int i = firstF; i <= secondF; i++){
            for(int j = firstC; j <= secondC ;j++){
                AbstractMap.SimpleEntry<Integer,Integer> id = new AbstractMap.SimpleEntry<>(i,j);
                if(!this.Celes.get(id).getObservadors().contains(fill))this.Celes.get(id).newObserver(fill);
            }
        }
    }

    public void ModificaCelaNum(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat){
        if(Objects.equals(this.Celes.get(id).getType(), "numeric")){
            Numero n = (Numero) this.Celes.get(id);
            n.setResultat(new BigDecimal(resultat));
        }
        else{
            Cela cel = this.Celes.get(id);
            Color colorFons = cel.getColorFons();
            Color colorLletra = cel.getColorLletra();
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
            this.Celes.replace(id, new Numero(id, new BigDecimal(resultat), true, 2, Tipus_Numero.numero));
            this.Celes.get(id).setColorFons(colorFons);
            this.Celes.get(id).setColorLletra(colorLletra);
            this.Celes.get(id).setObservadors(observadors);
        }
    }
    public void ModificaCelaDate(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat){
        Cela cel = this.Celes.get(id);
        Color colorFons = cel.getColorFons();
        Color colorLletra = cel.getColorLletra();
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
        this.Celes.replace(id, new DataCela(id, resultat));
        this.Celes.get(id).setType("date");
        this.Celes.get(id).setColorFons(colorFons);
        this.Celes.get(id).setColorLletra(colorLletra);
        this.Celes.get(id).setObservadors(observadors);
    }
    public void ModificaCelaText(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat){
        if(Objects.equals(this.Celes.get(id).getType(), "text")){
            this.Celes.get(id).setResultatFinal(resultat);
        }
        else{
            Cela cel = this.Celes.get(id);
            Color colorFons = cel.getColorFons();Color colorLletra = cel.getColorLletra();
            String type = cel.getType();ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
            this.Celes.replace(id, new TextCela(id, resultat));
            this.Celes.get(id).setType(type);
            this.Celes.get(id).setColorFons(colorFons);this.Celes.get(id).setColorLletra(colorLletra);
            this.Celes.get(id).setObservadors(observadors);
        }
    }


    public void Modifica_Tipus_Numeric(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String resultat = this.Celes.get(id).getResultatFinal();
        this.Celes.replace(id, new Numero(id, new BigDecimal(resultat), true, 2, Tipus_Numero.numero));
        this.Celes.get(id).setType("numeric");
    }

    public void Modifica_Tipus_Text(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String contingut = this.Celes.get(id).getResultatFinal();
        this.Celes.replace(id, new TextCela(id, contingut));
        this.Celes.get(id).setType("text");
    }

    public void Modifica_Tipus_Data(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String contingut = this.Celes.get(id).getResultatFinal();
        this.Celes.replace(id, new DataCela(id, contingut));
        this.Celes.get(id).setType("data");
    }

    public void Modifica_Numero_Tipus(AbstractMap.SimpleEntry<Integer, Integer> id, String Type) {
        Cela c = this.Celes.get(id);
        Numero n = (Numero) c;
        n.setTipus(Tipus_Numero.valueOf(Type));
    }

    public Cela Consultar_cela(AbstractMap.SimpleEntry<Integer, Integer> id) {
        return this.Celes.get(id);
    };

    public HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Cela> getCeles() throws Exception {
        return Celes;
    }

    //Métodes Privats
    private void IncrementarIndexFila(Integer nf){
        int i= nf;
        while (i < this.Num_Files) {
            for (int j= 0; j < this.Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,j);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(i+1,j);
                this.Celes.get(idc).setId(idc2);
            }
            ++i;
        }
    };

    private void IncrementarIndexCol(Integer nc){
        int i= nc;
        while (i < this.Num_Columnes) {
            for (int j= 0; j < this.Num_Files; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(j,i);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(j,i+1);
                this.Celes.get(idc).setId(idc2);
            }
            ++i;
        }
    };

    private void DecrementarIndexFila(Integer nf){
        int i= nf;
        while (i < this.Num_Files) {
            for (int j= 0; j < this.Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,j);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(i-1,j);
                this.Celes.get(idc).setId(idc2);
            }
            ++i;
        }
    };

    private void DecrementarIndexCol(Integer nc){
        int i= nc;
        while (i < this.Num_Columnes) {
            for (int j= 0; j < this.Num_Files; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(j,i);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(j,i-1);
                this.Celes.get(idc).setId(idc2);
            }
            ++i;
        }
    };


    public Integer getNum_Columnes() {

        return Num_Columnes;
    };

    public Integer getNum_Files() {

        return Num_Files;
    };

    public ArrayList<Numero> getBlocNumero(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = GetIdCeles(id1, id2);
        ArrayList<Numero> num = new ArrayList<>();

        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            Cela c = this.Celes.get(id);
            Numero n = (Numero) c;
            num.add(n);
        }

        return num;
    }

    public ArrayList<DataCela> getBlocData(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = GetIdCeles(id1, id2);
        ArrayList<DataCela> data = new ArrayList<>();

        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            Cela c = this.Celes.get(id);
            DataCela d = (DataCela) c;
            data.add(d);
        }

        return data;
    }

    public ArrayList<TextCela> getBlocText(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = GetIdCeles(id1, id2);
        ArrayList<TextCela> text = new ArrayList<>();

        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            Cela c = this.Celes.get(id);
            TextCela t = (TextCela) c;
            text.add(t);
        }

        return text;
    }

    public ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> GetIdCeles(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = new ArrayList<>();
        boolean found = false;
        Integer i = id1.getKey();
        Integer j = id1.getValue();
        while(!found) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<>(i,j);
            ids.add(idc);
            if(idc.equals(id2)) found = true;
            if(j <= id2.getValue()) {
                j++;
            }
            else {
                j = id1.getValue();
                i++;
            }
        }
        return ids;
    }

    private Boolean isNumerical(String strNum){
        if (strNum == null) return false;

        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean ExisteixId(AbstractMap.SimpleEntry<Integer, Integer> id) {
        return this.Celes.containsKey(id);
    }
};



