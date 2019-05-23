package Projekty.Zad1;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Locale;

public class Osobowy extends Pojazd{
    private String model;

    // Konstruktory

    Osobowy(String name,String color, int year,double course,String model){
        this.name=name;
        this.color=color;
        this.year=year;
        this.course=course;
        this.model=model;
        System.out.println("Konsturktor merytoryczny Osobowy");
    }
    // Metody


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    @Override
    public String toString(){
        String wynik = "";
        wynik +="\nNazwa: "+getName();
        wynik +="\nModel: "+getModel();
        wynik +="\nKolor:"+getColor();
        wynik +="\nRok produkcji: "+getYear();
        wynik +="\nPrzebieg: "+getCourse()+" km";
        return "\nSamochod osobowy\n"+wynik+"\n";
    }

    @Override

    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj instanceof Osobowy){
            Osobowy osobowy = (Osobowy)obj;
            return name.equals(osobowy.name) && model.equals(osobowy.model) && color.equals(osobowy.color)&&
                    (year==osobowy.year) && (course == osobowy.course);
        }
        return false;
    }

}
