package Projekty.Zad1;

public interface InterPojazd<T>{
    public void add(T obj);
    public void delete(T obj);
    public void vievInfo(T obj);
    public void save();
    public void read();
}
