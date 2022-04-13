package main.CapaDomini.Models;

import java.util.AbstractMap;
import java.util.Objects;

public class TextCela extends Cela{

    //CONSTRUCTORA
    public TextCela(AbstractMap.SimpleEntry<Integer, Integer> id, String contingut){
        super(id, contingut);
    }

    //FUNCIONS PUBLIQUES
    public Boolean buscarElement(String element){
        return contingut.contains(element);
    }

    public void remplacarElement(String element, String change){
        int index = contingut.indexOf(element);
        if(index != -1)
        {
            contingut = contingut.substring(0,index) + change + contingut.substring(index+element.length());
        }
    }

    public void AllMayus(){
        contingut =  contingut.toUpperCase();
    }

    public void AllMinus(){
        contingut =  contingut.toLowerCase();
    }
}
