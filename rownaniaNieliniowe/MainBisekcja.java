package rownaniaNieliniowe;

public class MainBisekcja {
    public static void main(String[] args) {
        double a = 1;
        double b = 2;
        double epsilon = 0.05;
        System.out.println(bisekcja(a, b, epsilon));
    }

    private static double bisekcja(double a, double b, double epsilon) {
        int i = 0;
        double wynik = 0;
        double x;

        if (warunek(a, b)) {
            while (Math.abs(funkcja(wynik)) >= epsilon) {
                x = x(a, b);
                if (funkcja(a) * funkcja(x) < 0) {
                    b = x;
                } else {
                    a = x;
                }
                wynik = x;
                i++;
            }
            System.out.println("ilosc iteracji: "+i);
        } else {
            System.out.println("Warunek konieczny nie zostal spelniony!");
        }

        return wynik;
    }

    private static double x(double a, double b) {
        return (a + b) / 2;
    }

    private static double funkcja(double x) {
        return Math.pow(x, 2) + x - 5;
    }

    private static boolean warunek(double a, double b) {
        if (funkcja(a) * funkcja(b) < 0) {
            return true;
        } else {
            return false;
        }
    }
}

