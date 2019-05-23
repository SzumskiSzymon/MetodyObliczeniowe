package Projekty.Zad1;

public class Motor extends Pojazd {
    private int maxSpeed;

    // Konstruktory

    Motor(String name,String color,int year,double course,int maxSpeed){
        this.name=name;
        this.color=color;
        this.year=year;
        this.course=course;
        this.maxSpeed=maxSpeed;
        System.out.println("Konstruktor merytoryczny Motor");
    }

    // metody


    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString(){
        String wynik = "";
        wynik +="\nNazwa: "+getName();
        wynik +="\nRok produkcji: "+getYear();
        wynik +="\nPrzebieg: "+getCourse()+" km";
        wynik +="\nModel: "+getMaxSpeed()+ "km/h";
        wynik +="\nKolor: "+getColor();
        return "\nSamochod osobowy\n"+wynik+"\n";
    }


    @Override

    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj instanceof Osobowy){
            Motor motor = (Motor) obj;
            return name.equals(motor.name) && (maxSpeed==motor.maxSpeed) && color.equals(motor.color)&&
                    (year==motor.year) && (course == motor.course);
        }
        return false;
    }

}
