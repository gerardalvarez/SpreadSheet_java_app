package main.CapaDomini.Models;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.Objects;

import static main.CapaDomini.Models.PublicFuntions.monthToData;

public class DataCela extends Cela {
    private String dateFormat = "null";
    private String TextFormat = "null";
    private LocalDate date;

    //CREADORA
    public DataCela(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut){
        super(id, contingut);
        DataValidator validator = new DateValidator("dd/MM/yyyy");
        if(validator.isValid(contingut)){
            dateFormat = contingut;
            date = LocalDate.of(Integer.parseInt(dateFormat.substring(0,2)),Integer.parseInt(dateFormat.substring(3,5))
                    ,Integer.parseInt(dateFormat.substring(6)));
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

    //GETTERS AND SETTERS
    public String getDataFormat() {return dateFormat;}
    public void setDataFormat(String dataFormat) {this.dateFormat = dataFormat;}
    public String getTextFormat() {return TextFormat;}
    public void setTextFormat(String textFormat) {TextFormat = textFormat;}


    //FUNCTIONS
    public void changeToText(){
        if(!Objects.equals(TextFormat, "null")){
           contingut = TextFormat;
        }
        else{
            contingut = dateFormat.substring(0,2) + PublicFuntions.monthToText(dateFormat.substring(3,5))+ dateFormat.substring(6);
        }
    }

    public void changeToDate(){
        if(!Objects.equals(dateFormat, "null")){
            contingut = dateFormat;
        }
        else{
            int size = TextFormat.length();
            contingut = TextFormat.substring(0,2) + "/" + monthToData(TextFormat.substring(2,size-4)) +
                    "/" + TextFormat.substring(size-4);
        }
    }

    public String getDia(){
        return String.valueOf(date.getDayOfMonth());
    }
    public String getMes(){
        return String.valueOf(date.getMonth());
    }
    public String getAny(){
        return String.valueOf(date.getYear());
    }

    public String getWeekDay(){
        return String.valueOf(date.getDayOfWeek());
    }
}