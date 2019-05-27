package rownaniaNieliniowe;

public class MainStyczne {
    public static void main(String[] args){
        double a = 1;
        double b = 6;
        double epsilon = 0.05;

        System.out.println(styczna(a,b,epsilon,2));
    }

    private static double styczna(double a,double b,double epsilon,int i){
        double wynik = 0;

        if(mainWarunek(a,b)) {
            if ((Math.abs(funkcja(x(a, b, i))) <= epsilon) || (Math.abs(x(a, b, i+1)-x(a, b, i))) <= epsilon) {
                wynik = x(a, b, i);
            }
        }

        return wynik;
    }


    // funkcje do wyliczania x

    private static double x0(double a,double b){
        double x = 0;

        if((funkcja(a)>0 && pochodnaII()>0) || (funkcja(a)<0 && pochodnaII()<0)){
            x = a;
        }else if((funkcja(b)>0 && pochodnaII()>0) || (funkcja(b)<0 && pochodnaII()<0)){
            x = b;
        }

        return x;
    }
    private static double x(double a,double b,int i){
        if(i==0){
            return x0(a,b) - (funkcja(x0(a,b)) / pochodna(x0(a,b)));
        }else {
            return x(a,b,i-1) - (funkcja(x(a,b,i-1)) / pochodna(x(a,b,i-1))) ;
        }
    }

    // metody do obliczania funkcji

    private static double funkcja(double x){
        return (-24)*Math.pow(x,2) -10*x + 154;
    }
    private static double pochodna(double x){
        return (-48)*x - 10;
    }
    private static double pochodnaII(){
        return -48;
    }

    // metody na warynki

    private static boolean mainWarunek(double a,double b){
        return funkcja(a)*funkcja(b)<0;
    }

}
