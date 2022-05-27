package main.CapaDomini.Models;


import jdk.javadoc.internal.doclets.toolkit.util.Utils;
import main.CapaDomini.Controllers.CtrlDomini;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.math.RoundingMode;
import java.util.*;

public class Full {
    private String nom;
    private Integer Num_Columnes;
    private Integer Num_Files;
    private HashMap<AbstractMap.SimpleEntry<Integer,Integer>, Cela> Celes;
    private ArrayList<Accio> Estatsprevis;

    //Constructor
    /**
     * Constructora de Full que crea el full a partird del nom, el número de columnes i el número files.
     * @param n
     * @param nc
     * @param nf
     */
    public Full(String n, Integer nc, Integer nf) {
        Celes = new HashMap<>();
        Estatsprevis= new ArrayList<>();
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

    /**
     * Constructora de Full que crea el full a partird del número de columnes i el número files.
     * @param nc
     * @param nf
     */
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

    /**
     * Funció que afegeix una fila al full a l'índex on s'indica per paràmetre.
     * @param nf
     */
    public void Afegir_Fila(Integer nf) throws Exception {
        int k= 0;
        //AÑADE AL FINAL
        while (k < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(Num_Files,k);
            this.Celes.put(idc, new TextCela(idc, ""));
            ++k;
        }
        if (nf <= this.Num_Files-1) IncrementarIndexFila(nf);
        ++this.Num_Files;
        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(nf,i);
            modificarContingutCela(idc,"");
            ++i;
        }

    };

    /**
     * Funció que afegeix una columna al full a l'índex on s'indica per paràmetre.
     * @param nc
     */
    public void Afegir_Columna(Integer nc) throws Exception {
        int k= 0;
        //AÑADE AL FINAL
        while (k < this.Num_Files) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(k,Num_Columnes);
            this.Celes.put(idc, new TextCela(idc, ""));
            ++k;
        }

        if (nc <= this.Num_Columnes-1) IncrementarIndexCol(nc);
        this.Num_Columnes= this.Num_Columnes+1;
        int i =0;
        while (i < this.Num_Files) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,nc);
            modificarContingutCela(idc,"");
            ++i;
        }

    };

    /**
     * Funció que elimina una fila al full a l'índex on s'indica per paràmetre.
     * @param nf
     */
    public void Eliminar_Fila(Integer nf) throws Exception {
        if (nf < this.Num_Files) DecrementarIndexFila(nf);
        --this.Num_Files;

        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(Num_Files,i);
            this.Celes.remove(idc);
            ++i;
        }
    };

    /**
     * Funció que elimina columna al full a l'índex on s'indica per paràmetre.
     * @param nc
     */
    public void Eliminar_Columna(Integer nc) throws Exception{
        if (nc < this.Num_Columnes) DecrementarIndexCol(nc);
        --this.Num_Columnes;

        Integer i= 0;
        while (i < this.Num_Columnes) {
            AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i,Num_Columnes);
            this.Celes.remove(idc);
            ++i;
        }
    };

    /**
     * Funció que esborra les celes que rep per paràmetre.
     * @param celes
     */
    public void Esborrar_Celes(ArrayList<Cela> celes) {
        int i= 0;
        while (i < celes.size()){
            AbstractMap.SimpleEntry<Integer, Integer> idc= celes.get(i).getId();
            Cela c= this.Celes.get(idc);
            c= new TextCela(idc,"");
            ++i;
        }
    };

    /**
     * Funció que modifica la cel·la "id" amb el resultat que es rep per paràmetre, comprovant el tipus de cel·la
     * i comprovant si té obtervadors.
     * @param id
     * @param resultat
     */
    public void Modifica_Cela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) {
        int size = resultat.length();
        String a = PublicFuntions.calculaTipus(resultat);
        if(a.equals("numeric")){
            if(this.Celes.get(id) instanceof Numero){
                Numero cel = (Numero)this.Celes.get(id);
                Color colorFons = cel.getColorFons();
                Color colorLletra = cel.getColorLletra();
                String type = cel.getType();
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new Numero(id, new BigDecimal(resultat), cel.getArrodonit(), cel.getNum_Decimals(), Tipus_Numero.numero));
                this.Celes.get(id).setType("numeric");
                this.Celes.get(id).setColorFons(colorFons);
                this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
            else{
                Cela cel = this.Celes.get(id);
                Color colorFons = cel.getColorFons();
                Color colorLletra = cel.getColorLletra();
                String type = cel.getType();
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new Numero(id, new BigDecimal(resultat), true, 2, Tipus_Numero.numero));
                this.Celes.get(id).setType("numeric");
                this.Celes.get(id).setColorFons(colorFons);
                this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
        } else if(Objects.equals(a, "date")){
            if(this.Celes.get(id) instanceof DataCela){
                DataCela cel = (DataCela) this.Celes.get(id);
                Color colorFons = cel.getColorFons();
                Color colorLletra = cel.getColorLletra();
                String type = cel.getType();
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new DataCela(id,resultat));
                this.Celes.get(id).setType("date");
                this.Celes.get(id).setColorFons(colorFons);
                this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
            else{
                Cela cel = this.Celes.get(id);
                Color colorFons = cel.getColorFons();
                Color colorLletra = cel.getColorLletra();
                String type = cel.getType();
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new DataCela(id,resultat));
                this.Celes.get(id).setType("date");
                this.Celes.get(id).setColorFons(colorFons);
                this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
        }else {
            if(this.Celes.get(id) instanceof TextCela){
                TextCela cel = (TextCela) this.Celes.get(id);
                Color colorFons = cel.getColorFons();
                Color colorLletra = cel.getColorLletra();
                String type = cel.getType();
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new TextCela(id,resultat));
                this.Celes.get(id).setType("text");
                this.Celes.get(id).setColorFons(colorFons);
                this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
            else{
                Cela cel = this.Celes.get(id);
                Color colorFons = cel.getColorFons();
                Color colorLletra = cel.getColorLletra();
                String type = cel.getType();
                ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> observadors = cel.getObservadors();
                this.Celes.replace(id, new TextCela(id,resultat));
                this.Celes.get(id).setType("text");
                this.Celes.get(id).setColorFons(colorFons);
                this.Celes.get(id).setColorLletra(colorLletra);
                this.Celes.get(id).setObservadors(observadors);
            }
        }
        //AQUI HAY QUE ACTUALIZAR TODOS LOS OBSERVERS DE LA NUEVA CELDA SI ESTA CANVIA DE TIPO TAMBIEN HABRIA QUE TENERLO
        //EN CUENTA
    }

    /**
     *Funció que avalua l'operació a realitzar amb els operadors "sender" i operació "oper",
     * i retorna el resultat.
     * @param sender
     * @param oper
     * @return
     */
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
            System.out.println(bc.calculaVariança(sender));
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

    /**
     * Modifica el tipus de la cel·la amb id "id" pel tipus numeric.
     * @param id
     */
    public void Modifica_Tipus_Numeric(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String resultat = this.Celes.get(id).getResultatFinal();
        this.Celes.replace(id, new Numero(id, new BigDecimal(resultat), true, 2, Tipus_Numero.numero));
        this.Celes.get(id).setType("numeric");
    }
    /**
     * Modifica el tipus de la cel·la amb id "id" pel tipus text.
     * @param id
     */
    public void Modifica_Tipus_Text(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String contingut = this.Celes.get(id).getResultatFinal();
        this.Celes.replace(id, new TextCela(id, contingut));
        this.Celes.get(id).setType("text");
    }
    /**
     * Modifica el tipus de la cel·la amb id "id" pel tipus data.
     * @param id
     */
    public void Modifica_Tipus_Data(AbstractMap.SimpleEntry<Integer, Integer> id) {
        String contingut = this.Celes.get(id).getResultatFinal();
        this.Celes.replace(id, new DataCela(id, contingut));
        this.Celes.get(id).setType("data");
    }

    /**
     * Modifica el tipus de número de la cel·la amb id "id" pel tipus "type".
     * @param id
     * @param Type
     */
    public void Modifica_Numero_Tipus(AbstractMap.SimpleEntry<Integer, Integer> id, String Type) {
        Cela c = this.Celes.get(id);
        Numero n = (Numero) c;
        n.setTipus(Tipus_Numero.valueOf(Type));
    }

    public Cela Consultar_cela(AbstractMap.SimpleEntry<Integer, Integer> id) {
        return this.Celes.get(id);
    };

    public HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Cela> getCeles() {
        return Celes;
    }

    //Métodes Privats

    /**
     * Incrementa el índex de les files que tenen un índex superior a "nf".
     * @param nf
     */
    private void IncrementarIndexFila(Integer nf) throws Exception{
        int i= Num_Files;
        while (i > nf) {
            for (int j= 0; j < this.Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i-1,j);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(i,j);
                if (Celes.get(idc) instanceof CelaRefData) {
                    CelaRefData c = (CelaRefData) Celes.get(idc);
                    modificarContingutCela(idc2, c.getContingut());
                } else if (Celes.get(idc) instanceof CelaRefText) {
                    CelaRefText c = (CelaRefText) Celes.get(idc);
                    modificarContingutCela(idc2, c.getContingut());
                } else if(Celes.get(idc) instanceof CelaRefNum){
                    CelaRefNum c = (CelaRefNum) Celes.get(idc);
                    modificarContingutCela( idc2, c.getContingut());
                }else{
                    modificarContingutCela(idc2, Celes.get(idc).getResultatFinal());
                }
            }
            --i;
        }
    };
    /**
     * Incrementa el índex de les columnes que tenen un índex superior a "nc".
     * @param nc
     */
    private void IncrementarIndexCol(Integer nc) throws Exception {
        int i= Num_Columnes;
        while (i > nc) {
            for (int j= 0; j < this.Num_Files; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(j,i-1);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(j,i);
                if (Celes.get(idc) instanceof CelaRefData) {
                    CelaRefData c = (CelaRefData) Celes.get(idc);
                    modificarContingutCela(idc2, c.getContingut());
                } else if (Celes.get(idc) instanceof CelaRefText) {
                    CelaRefText c = (CelaRefText) Celes.get(idc);
                    modificarContingutCela(idc2, c.getContingut());
                } else if(Celes.get(idc) instanceof CelaRefNum){
                    CelaRefNum c = (CelaRefNum) Celes.get(idc);
                    modificarContingutCela( idc2, c.getContingut());
                }else{
                    modificarContingutCela(idc2, Celes.get(idc).getResultatFinal());
                }
            }
            --i;
        }
    };

    /**
     * Decrementa el índex de les files que tenen un índex superior a "nf".
     * @param nf
     */
    private void DecrementarIndexFila(Integer nf) throws Exception {
        int i= nf+1;
        while (i < this.Num_Files) {
            for (int j= 0; j < this.Num_Columnes; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(i-1,j);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(i,j);
                if (Celes.get(idc2) instanceof CelaRefData) {
                    CelaRefData c = (CelaRefData) Celes.get(idc2);
                    modificarContingutCela(idc, c.getContingut());
                } else if (Celes.get(idc2) instanceof CelaRefText) {
                    CelaRefText c = (CelaRefText) Celes.get(idc2);
                    modificarContingutCela(idc, c.getContingut());
                } else if(Celes.get(idc2) instanceof CelaRefNum){
                    CelaRefNum c = (CelaRefNum) Celes.get(idc2);
                    modificarContingutCela( idc, c.getContingut());
                }else{
                    modificarContingutCela(idc, Celes.get(idc2).getResultatFinal());
                }
            }
            ++i;
        }
    };
    /**
     * Decrementa el índex de les columnes que tenen un índex superior a "nc".
     * @param nc
     */
    private void DecrementarIndexCol(Integer nc) throws Exception{
        int i= nc+1;
        while (i < this.Num_Columnes) {
            for (int j= 0; j < this.Num_Files; ++j) {
                AbstractMap.SimpleEntry<Integer, Integer> idc = new AbstractMap.SimpleEntry<Integer, Integer>(j,i-1);
                AbstractMap.SimpleEntry<Integer, Integer> idc2 = new AbstractMap.SimpleEntry<Integer, Integer>(j,i);
                if (Celes.get(idc2) instanceof CelaRefData) {
                    CelaRefData c = (CelaRefData) Celes.get(idc2);
                    modificarContingutCela(idc, c.getContingut());
                } else if (Celes.get(idc2) instanceof CelaRefText) {
                    CelaRefText c = (CelaRefText) Celes.get(idc2);
                    modificarContingutCela(idc, c.getContingut());
                } else if(Celes.get(idc2) instanceof CelaRefNum){
                    CelaRefNum c = (CelaRefNum) Celes.get(idc2);
                    modificarContingutCela( idc, c.getContingut());
                }else{
                    modificarContingutCela(idc, Celes.get(idc2).getResultatFinal());
                }
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

    /**
     * Retorna una llista de cel·les tipus Numero a partir de "id1" i "id2" que delimiten el bloc
     * de cel·les a tractar.
     * @param id1
     * @param id2
     * @return
     */
    public ArrayList<Numero> getBlocNumero(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> ids = GetIdCeles(id1, id2);
        ArrayList<Numero> num = new ArrayList<>();

        for (AbstractMap.SimpleEntry<Integer, Integer> id : ids) {
            System.out.println(id);
            Cela c = this.Celes.get(id);
            Numero n = (Numero) c;
            num.add(n);
        }

        return num;
    }

    public double[] getColNumero(Integer col, Integer fIni, Integer fFi){
        double[] num = new double[fFi-fIni+1];
        for(int i = fIni; i <= fFi; i++){
            System.out.println(i + "="+ col);
            Cela c = this.Celes.get(new AbstractMap.SimpleEntry<>(i,col));
            System.out.println(c.getResultatFinal());
            Numero n = (Numero) c;
            num[i-fIni] = (n.getResultat().doubleValue());
        }
        return num;
    }
    public ArrayList<String> getColText(Integer col, Integer fIni, Integer fFi){
        ArrayList<String> num = new ArrayList<>();
        for(int i = fIni; i <= fFi; i++){
            System.out.println(i + "="+ col);
            Cela c = this.Celes.get(new AbstractMap.SimpleEntry<>(i,col));
            num.add(c.getResultatFinal());
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

    public ArrayList<Cela> CelesArray(){
        ArrayList<Cela> a =  new ArrayList<>();
        for (HashMap.Entry<AbstractMap.SimpleEntry<Integer,Integer>, Cela> entry : Celes.entrySet()) {
            a.add(entry.getValue());
        }
        return a;
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

    public ArrayList<Cela> getBlocCeles(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2) {
        ArrayList<Cela> c = new ArrayList<>();
        boolean found = false;
        Integer ip = id1.getKey();
        Integer jp= id1.getValue();
        Integer iif = id2.getKey();
        Integer jjf= id2.getValue();
        for (int i =ip;i<=iif;i++){
            for (int j =jp;j<=jjf;j++){
                c.add(Celes.get(new AbstractMap.SimpleEntry<>(i,j)));
            }
        }
        return c;
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

    public Cela [][] getBlocEnMatriu(ArrayList<Cela> c, Integer nf, Integer nc){
        Cela [][] mat = new Cela[nf+1][nc+1];
        int count=0;
        for (int i=0;i<= nf;++i) {
            for (int j = 0; j <= nc; ++j) {
                mat[i][j] = c.get(count);
                count++;
            }
        }
        return mat;
    }

    /**
     * Ordena un bloc de cel·les acotat per les ids "id1" i "id2" segons indiqui el "criteri".
     * @param id1
     * @param id2
     * @param cols
     * @param criteri
     * @throws Exception
     */
    public void ordena_bloc(AbstractMap.SimpleEntry<Integer, Integer> id1, AbstractMap.SimpleEntry<Integer, Integer> id2, ArrayList<Integer> cols, String criteri) throws Exception {
        ArrayList<Cela> C= getBlocCeles(id1,id2);
        Bloc_celes b=new Bloc_celes();
        Integer nf = id2.getKey() - id1.getKey();
        Integer nc = id2.getValue() - id1.getValue();
        Cela [][] matt = getBlocEnMatriu(C,nf,nc);
        for (Cela d:C) System.out.println(d.getId()+"-");
        System.out.println("2222222");
        for (int i=0;i< matt.length;++i) {
            for (int j = 0; j < matt[0].length; ++j) {
                System.out.print("(" + matt[i][j].getId() + ") " + matt[i][j].getResultatFinal() + "|");
            }
            System.out.println();
        }
        switch (criteri){
            case "Major-menor":
                b.ordena_Z_A(matt,cols);
                break;
            case "Menor-major":
                b.ordena_A_Z(matt,cols);
                break;
            default:
                System.out.println("error");
        }
        for (int i=0;i< matt.length;++i) {
            for (int j = 0; j < matt[0].length; ++j) {
                Cela c= matt[i][j];
                if(c instanceof CelaRefNum){
                    modificarContingutCela(c.getId(),((CelaRefNum) c).getContingut());
                } else if (c instanceof CelaRefText) {
                    modificarContingutCela(c.getId(),((CelaRefText) c).getContingut());
                } else if (c instanceof CelaRefData) {
                    modificarContingutCela(c.getId(),((CelaRefData) c).getContingut());
                }else modificarContingutCela(c.getId(),c.getResultatFinal());
            }
        }

    }

    /**
     * Retorna cert si "strNum" és un número, fals si no ho és.
     * @param strNum
     * @return
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
     * Retorna cert si "id" existeix, fals si no.
     * @param id
     * @return
     */
    public boolean ExisteixId(AbstractMap.SimpleEntry<Integer, Integer> id) {
        return this.Celes.containsKey(id);
    }

    /**
     * Retorna la informació del full.
     * @return
     */
    @Override
    public String toString() {
        return "Full{" +
                "nom='" + nom + '\'' +
                ", Num_Columnes=" + Num_Columnes +
                ", Num_Files=" + Num_Files +
                ", Celes=" + Celes +
                '}';
    }

    /**
     * Mostra el full en forma de matriu de strings, formant-la i retornant-la.
     * @return
     */
    public String[][] Mostrar() { //El full hauria de retornar una ArrayList i així no haver de col·locar tot aixó al controller
        String[][] temp = new String[this.Num_Files][this.Num_Columnes];
        for(int i = 0; i < this.Num_Files; i++) {
            for(int j = 0; j < this.Num_Columnes; j++) {
                AbstractMap.SimpleEntry<Integer, Integer> id = new AbstractMap.SimpleEntry<>(i, j);
                Cela c = this.Celes.get(id);
                if (c instanceof Numero) {
                    Numero n = (Numero) c;
                    BigDecimal d = n.getResultat();
                    Integer Num_Dec = n.getNum_Decimals();
                    if(n.getArrodonit()) {
                        d = d.setScale(Num_Dec, RoundingMode.HALF_UP);
                    }
                    else {
                        d = d.setScale(Num_Dec, RoundingMode.DOWN);
                    }
                    temp[i][j] = (d.toString());
                }
                else if (c instanceof TextCela) {
                    if(c.getResultatFinal().isEmpty()) temp[i][j] = (" ");
                    else temp[i][j] = (c.getResultatFinal());
                }
                else if (c instanceof DataCela){
                    temp[i][j] = (c.getResultatFinal());
                }
            }
        }
        return temp;
    }

    /**
     * Retorna el resultat final, en forma de string, de la cel·la indicada.
     * @param id
     * @return
     */
    public String ValorTotal(AbstractMap.SimpleEntry<Integer, Integer> id) {
        Cela c = this.Celes.get(id);
        if (c instanceof Numero) {
            if (c instanceof CelaRefNum) {
                CelaRefNum rn = (CelaRefNum) c;
                return rn.getContingut();
            }
            else {
                Numero n = (Numero) c;
                BigDecimal d = n.getResultat();
                return d.toString();
            }
        }
        else if (c instanceof DataCela) {
            if (c instanceof CelaRefData) {
                CelaRefData rd = (CelaRefData) c;
                return rd.getContingut();
            }
            else return c.getResultatFinal();
        }
        else {
            if (c instanceof CelaRefText) {
                CelaRefText rt = (CelaRefText) c;
                return rt.getContingut();
            }
            else return c.getResultatFinal();
        }
    }

    /**
     * És la subfunció de modificacel·la que controla les referències de les cel·les.
     * @param id
     * @param l
     * @param resultat
     */
    public void opera(AbstractMap.SimpleEntry<Integer, Integer> id, ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> l, String resultat) {

        if(resultat.length()<=5){
            if (Celes.get(l.get(0)) instanceof Numero || Celes.get(l.get(0)) instanceof CelaRefNum) {
                Cela aaux=new CelaRefNum(id,Celes.get(l.get(0)).getResultatFinal(),true,2,Tipus_Numero.numero,resultat);
                aaux.setObservadors(Celes.get(id).observadors);
                Celes.replace(id,aaux);
            }else if (Celes.get(l.get(0)) instanceof TextCela || Celes.get(l.get(0)) instanceof CelaRefText ) {
                Cela aaux=new CelaRefText(id,resultat,Celes.get(l.get(0)).getResultatFinal());
                aaux.setObservadors(Celes.get(id).observadors);
                Celes.replace(id,aaux);
            }else if (Celes.get(l.get(0)) instanceof DataCela || Celes.get(l.get(0)) instanceof CelaRefData) {

                Cela aaux=new CelaRefData(id,resultat,Celes.get(l.get(0)).getResultatFinal());
                aaux.setObservadors(Celes.get(id).observadors);
                Celes.replace(id,aaux);
            }
        }else if(resultat.substring(1, 4).equals("MAY") ){
            Celes.replace(id,new CelaRefText(id,resultat,Celes.get(l.get(0)).getResultatFinal().toUpperCase()));
        }else if(resultat.substring(1, 4).equals("MIN") ){
            Celes.replace(id,new CelaRefText(id,resultat,Celes.get(l.get(0)).getResultatFinal().toLowerCase()));
        }else if (resultat.substring(1, 4).equals("COV") || resultat.substring(1, 4).equals("PER")){
        l.remove(l.size()-1);
        ArrayList<Numero> a1=new ArrayList<>();
        ArrayList<Numero> a2= new ArrayList<>();
        Boolean primera_part=true;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getKey()==-2) {
                primera_part=false;
                continue;
            }
            if (primera_part) {
                if (Celes.get(l.get(i)) instanceof Numero) {
                    a1.add(((Numero) Celes.get(l.get(i))));
                }
            } else {
                if (Celes.get(l.get(i)) instanceof Numero) {
                    a2.add(((Numero) Celes.get(l.get(i))));
                }
            }
        }
        Bloc_celes bc=new Bloc_celes();
        if (resultat.substring(1, 4).equals("PER")){
            Cela aaux;
            try {
                BigDecimal b = BigDecimal.valueOf(bc.coeficient_Pearson(a1,a2));
                aaux= new CelaRefNum(id,b.toString(),true,2,Tipus_Numero.numero,resultat);
                System.out.println(b+" ññ");
            } catch (NumberFormatException e) {
                aaux= new TextCela(id,"#Invalid calc");
                Celes.replace(id,aaux);
                return;
            }
            aaux.setObservadors(Celes.get(id).observadors);
            Celes.replace(id,aaux);

        }else {
            Cela aaux;
            if (a1.size()!= a2.size()){
                aaux= new TextCela(id,"#ERROR");
            }
            else {
                try {
                    BigDecimal b = BigDecimal.valueOf(bc.covariança(a1,a2));
                    aaux= new CelaRefNum(id,b.toString(),true,2,Tipus_Numero.numero,resultat);
                } catch (NumberFormatException e) {
                    aaux= new TextCela(id,"#ERROR");
                    Celes.replace(id,aaux);
                    return;
                }
            }
            aaux.setObservadors(Celes.get(id).observadors);
            Celes.replace(id,aaux);
        }

        if (l.remove(new AbstractMap.SimpleEntry<>(-2,-2)));
    }
else {
            ArrayList<Numero> aux = new ArrayList<>();
            for (int i = 0; i < l.size(); i++) {
                if (Celes.get(l.get(i)) instanceof Numero) {
                    aux.add(((Numero) Celes.get(l.get(i))));
                }
            }
            BigDecimal b = avaluar(aux, resultat.substring(1, 4));
            Cela aaux= new CelaRefNum(id,b.toString(),true,2,Tipus_Numero.numero,resultat);
            aaux.setObservadors(Celes.get(id).observadors);
            Celes.replace(id,aaux);
        }
        for (int i = 0; i < l.size(); i++) {
            if (!Celes.get(l.get(i)).getObservadors().contains(id)) Celes.get(l.get(i)).newObserver(id);
        }
        System.out.println("vamooooooos"+Celes.get(id).getObservadors().size());
    }

    /**
     * Esborra les referències de la cel·la amb id "id".
     * @param id
     * @param l
     */
    public void borraref(AbstractMap.SimpleEntry<Integer, Integer> id, ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> l) {

        for (AbstractMap.SimpleEntry<Integer, Integer> i:l){
            Celes.get(i).getObservadors().remove(id);
        }
        System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
    }

    /**
     * Funció que comprova si existeix algun bucle dins del bloc de cel·les comprés entre "id" i "id1".
     * @param id
     * @param id1
     * @return
     */
    public boolean comprova_bucle(AbstractMap.SimpleEntry<Integer, Integer> id, AbstractMap.SimpleEntry<Integer, Integer> id1) {
       Boolean b=false;
       String res="";
        if( Celes.get(id1) instanceof CelaRefNum ) {
            res=((CelaRefNum) Celes.get(id1)).getContingut();
        }else if (Celes.get(id1) instanceof CelaRefText) {
            res=((CelaRefText) Celes.get(id1)).getContingut();
        }else if (Celes.get(id1) instanceof CelaRefData) {
            res=((CelaRefData) Celes.get(id1)).getContingut();
        }else return false;
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> l = PublicFuntions.analizaops(res,this.Num_Files,this.Num_Columnes);
       if (l.contains(id)) return true;
       for (AbstractMap.SimpleEntry<Integer,Integer> id2 : l ){
           b= comprova_bucle(id,id2);
           if (b) break;
       }
        return b;

    }

    /**
     * Funció que modifica el contingut de la cel·la amb id "id", comprovant si no és referència de cap altra cel·la.
     * @param id
     * @param resultat
     * @throws Exception
     */
    public void modificarContingutCela(AbstractMap.SimpleEntry<Integer, Integer> id, String resultat) throws Exception {

            String a = "";
            ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> l = new ArrayList<>();
            if (Consultar_cela(id) instanceof CelaRefNum && !((CelaRefNum) Consultar_cela(id)).getContingut().equals(resultat)) {
                l = PublicFuntions.analizaops(((CelaRefNum) Consultar_cela(id)).getContingut(), this.Num_Files, this.Num_Columnes);
                borraref(id, l);
            } else if (Consultar_cela(id) instanceof CelaRefText && !((CelaRefText) Consultar_cela(id)).getContingut().equals(resultat)) {
                l = PublicFuntions.analizaops(((CelaRefText) Consultar_cela(id)).getContingut(), this.Num_Files, this.Num_Columnes);
                borraref(id, l);
            } else if (Consultar_cela(id) instanceof CelaRefData && !((CelaRefData) Consultar_cela(id)).getContingut().equals(resultat)) {
                l = PublicFuntions.analizaops(((CelaRefData) Consultar_cela(id)).getContingut(), this.Num_Files, this.Num_Columnes);
                borraref(id, l);
            }if (resultat.length()>6 && (resultat.substring(0,5).equals("=COV(") || resultat.substring(0,5).equals("=PER(")) ){
                String ops= resultat.substring(5,resultat.length()-1);
                Boolean b= analitzablocs(ops, this.Num_Files, this.Num_Columnes);
                System.out.println(b);
                if (!b){
                    Modifica_Cela(id, "#ERROR");
                    CheckObs(id);
                    return;
                }
                l= analitzablocs_ops(ops, this.Num_Files, this.Num_Columnes);
                for (AbstractMap.SimpleEntry<Integer, Integer> id2 : l) {
                    b = comprova_bucle(id, id2);
                    if (b) break;
                }
                if (b){
                     Modifica_Cela(id, "#ERROR");
                    CheckObs(id);
                     return;
                }
                if (!l.contains(id)) opera(id, l, resultat);
                else {
                    Modifica_Cela(id, "#ERROR");
                    CheckObs(id);
                    return;
                }
            }else {
            if (!resultat.equals("")) a = PublicFuntions.analiza(resultat, this.Num_Files, this.Num_Columnes);
            if (!resultat.equals("")) {
                if (a.equals("text") || a.equals("data") || a.equals("numeric")) {
                    Modifica_Cela(id, resultat);
                } else if (a.equals("REFNUM") || a.equals("REFTEXT") || a.equals("ref a otra celda")) {
                    Boolean b = false;
                    l = PublicFuntions.analizaops(resultat, this.Num_Files, this.Num_Columnes);
                    for (AbstractMap.SimpleEntry<Integer, Integer> id2 : l) {
                        b = comprova_bucle(id, id2);
                        if (b) break;
                    }
                    if (b) a = "referencia pero #ERROR";
                    else {
                        if (!l.contains(id)) opera(id, l, resultat);
                        else a = "referencia pero #ERROR";
                    }
                }
                if (a.equals("referencia pero #ERROR")) {
                    Modifica_Cela(id, "#ERROR");
                } else if (resultat.equals("")) Modifica_Cela(id, "");
            } else if (resultat.equals("")) Modifica_Cela(id, "");

            CheckObs(id);
        }
    }

    /**
     * Funció que comprova els observadors de la cel·la amb id "id".
     * @param id
     * @throws Exception
     */
    public void CheckObs(AbstractMap.SimpleEntry<Integer, Integer> id) throws Exception {
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> obs = getCeles().get(id).getObservadors();
        System.out.println("check obs V"+obs.size());
        if(obs.size()!=0){
            System.out.println("check obs "+obs.size());
            for(int i = 0; i < obs.size(); i++){
                if (getCeles().get(obs.get(i)) instanceof CelaRefData) {
                    CelaRefData c = (CelaRefData) getCeles().get(obs.get(i));
                    modificarContingutCela( obs.get(i), c.getContingut());
                } else if (getCeles().get(obs.get(i)) instanceof CelaRefText) {
                    CelaRefText c = (CelaRefText) getCeles().get(obs.get(i));
                    modificarContingutCela( obs.get(i), c.getContingut());
                } else if (getCeles().get(obs.get(i)) instanceof CelaRefNum){
                    System.out.println(obs.get(i).getKey() + " " + obs.get(i).getValue());
                    CelaRefNum c = (CelaRefNum) getCeles().get(obs.get(i));
                    modificarContingutCela( obs.get(i), c.getContingut());
                }

            }

        }
    }


    public Boolean analitzablocs(String ops, Integer y, Integer x) {
        String tipus="";
        Scanner sc=new Scanner(ops);
        sc.useDelimiter(",");
        Boolean err=false;
        int aux=0;
        while (sc.hasNext()){
            ++aux;
            String op =sc.next();
            System.out.println(op);
            if (op.contains(":")){                       // es bloque
                if (op.length()<5) {
                    tipus = "referencia pero #ERROR";
                    break;
                }else {
                    System.out.println("entra aqui");
                    Scanner scc=new Scanner(op);
                    scc.useDelimiter(":");

                    String[] part = scc.next().split("(?<=\\D)(?=\\d)");
                    String[] part2 = scc.next().split("(?<=\\D)(?=\\d)");
                    System.out.println(part[0]+part[1]);
                    if (part.length==2 && PublicFuntions.isNum(part[1]) && PublicFuntions.toNumber(part[0])<y && PublicFuntions.toNumber(part[0])>0 && Integer.parseInt(part[1])<=x && Integer.parseInt(part[1])>0
                    && part2.length==2 && PublicFuntions.isNum(part2[1]) && PublicFuntions.toNumber(part2[0])<y && PublicFuntions.toNumber(part2[0])>0 && Integer.parseInt(part2[1])<=x && Integer.parseInt(part2[1])>0){
                        if (Integer.parseInt(part[1]) <= Integer.parseInt(part2[1]) && PublicFuntions.toNumber(part[0])<=PublicFuntions.toNumber(part2[0])){
                            System.out.println("suuu");
                            for (int ii=PublicFuntions.toNumber(part[0]);ii<=PublicFuntions.toNumber(part2[0]);ii++){
                                for (int jj=Integer.parseInt(part[1]);jj<=Integer.parseInt(part2[1]);jj++){
                                    if (!(Celes.get(new AbstractMap.SimpleEntry<>(ii-1,jj-1)) instanceof Numero)) {
                                        System.out.println(Celes.get(new AbstractMap.SimpleEntry<>(ii-1,jj-1)).getClass()+" "+ii+" "+jj);
                                        tipus="referencia pero #ERROR";
                                        err=true;
                                        break;
                                    }
                                }
                                if (err) break;
                            }
                        }else{
                            tipus="referencia pero #ERROR";
                            err=true;
                        }
                    }else{
                        tipus="referencia pero #ERROR";
                        err=true;
                        break;
                    }
                    if (err) break;
                }
            }
            else {
                tipus="referencia pero #ERROR";
                err=true;
                break;
            }
            if (err) break;
            if (aux>2){
                tipus="referencia pero #ERROR";
                break;
            }
        }
        if (tipus.equals("referencia pero #ERROR")) return false;
        else return true;
    }


    private ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> analitzablocs_ops(String ops, Integer y, Integer x) {
        String tipus="";
        ArrayList<AbstractMap.SimpleEntry<Integer, Integer>> l =new ArrayList<>();
        Scanner sc=new Scanner(ops);
        sc.useDelimiter(",");
        Boolean err=false;
        int aux=0;
        while (sc.hasNext()){
            ++aux;
            String op =sc.next();
            if (op.contains(":")){                       // es bloque
                if (op.length()<5) {
                    tipus = "referencia pero #ERROR";
                    break;
                }else {
                    Scanner scc=new Scanner(op);
                    scc.useDelimiter(":");

                    String[] part = scc.next().split("(?<=\\D)(?=\\d)");
                    String[] part2 = scc.next().split("(?<=\\D)(?=\\d)");
                    if (part.length==2 && PublicFuntions.isNum(part[1]) && PublicFuntions.toNumber(part[0])<y && PublicFuntions.toNumber(part[0])>0 && Integer.parseInt(part[1])<=x && Integer.parseInt(part[1])>0
                            && part2.length==2 && PublicFuntions.isNum(part2[1]) && PublicFuntions.toNumber(part2[0])<y && PublicFuntions.toNumber(part2[0])>0 && Integer.parseInt(part2[1])<=x && Integer.parseInt(part2[1])>0){
                        if (Integer.parseInt(part[1]) <= Integer.parseInt(part2[1]) && PublicFuntions.toNumber(part[0])<=PublicFuntions.toNumber(part2[0])){
                            for (int i=PublicFuntions.toNumber(part[0]);i<=PublicFuntions.toNumber(part2[0]);i++){
                                for (int j=Integer.parseInt(part[1]);j<=Integer.parseInt(part2[1]);j++){
                                    l.add(new AbstractMap.SimpleEntry<>(i-1,j-1));
                                }
                                if (err) break;
                            }
                        }else{
                            tipus="referencia pero #ERROR";
                            err=true;
                        }
                    }else{
                        tipus="referencia pero #ERROR";
                        break;
                    }
                    if (err) break;
                }
            }
            else {
                tipus="referencia pero #ERROR";
                break;
            }
            if (err) break;
            l.add(new AbstractMap.SimpleEntry<>(-2,-2));
            if (aux>2){
                tipus="referencia pero #ERROR";
                break;
            }
        }
        return l;
    }



};



