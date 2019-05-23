package Projekty.Zad1;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

class BazaPojazdow<T extends Pojazd> implements InterPojazd<T>{
    private ArrayList<T> bazaPojazdow = new ArrayList<>();
    private String nazwaPliku = "Plik UTF-8";
    //private T obj;

    @Override
    public void add(T obj) {
        for (T element:bazaPojazdow) {
            if (obj.equals(element)) {
                System.out.println("Element istnieje juz w tablicy!");
            } else {
                bazaPojazdow.add(obj);
                System.out.println(obj.getName() + " " + " zostal dodany!");
            }
        }
    }

    @Override
    public void delete(T obj) {
        if (obj instanceof Osobowy) {
            bazaPojazdow.remove(obj);
            System.out.println("Samochod osobowy " + obj.getName() + " " + " zostal usuniety!");
        }
        if (obj instanceof Motor){
            bazaPojazdow.remove(obj);
            System.out.println("Motor "+obj.getName()+" "+" zostal usuniety!");
        }
    }

    @Override
    public void vievInfo(T obj) {
        System.out.println(obj.toString());
    }



    @Override
    public void save() {
        for (T obj : bazaPojazdow) {
            try {
                Writer out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(nazwaPliku, true), "UTF8"));


                out.write(obj.toString());

                if (out == null) {
                    System.out.println("Problem");
                } else {
                    out.close();
                }
            } catch (IOException ex) {
                System.out.println("Problem z dostepem do pliku");
            }
        }
    }


    @Override
    public void read(){
        System.out.println("\nPLIK UTF8: ");
        String str = "";
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader
                    (new FileInputStream(nazwaPliku),"UTF8"));
            while((str = in.readLine()) != null){
                System.out.println(str);
            }
            in.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        Locale.setDefault(new Locale("pl-PL"));
    }


}
