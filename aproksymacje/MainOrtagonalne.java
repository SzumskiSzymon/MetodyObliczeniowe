package aproksymacje;

public class MainOrtagonalne {
    public static void main(String[] args){
        double x = 0.25;
        double a = -1;
        double b = 1;
        int n = 7;
        for(int i = 3;i<=n;i++) {
            aproksymacja(x, a, b, i);
        }
    }

    private static double aproksymacja(double x,double a,double b,int n){
        double wynik = 0;

        for(int i = 0;i<n;i++){
            wynik+=c(a,b,i)*wielomian(x,i);
        }

        System.out.println("n : "+n+" wynik = "+wynik);
        return wynik;
    }

    private static double wielomian(double x,int n) {
        if(n==0){
            return 1d;
        }else if(n==1){
            return x;
        }else if(n==2){
            return (1d/2d)*(3d*Math.pow(x,2)-1d);
        }else{
            return (1d/n) * ((2d * (n - 1d) + 1d) * x * wielomian(x, n - 1) - ((n - 1) * wielomian(x, n - 2)));
        }
    }

    private static double funkcja(double x){
        return Math.sqrt(3*Math.pow(x,2)+1);
    }

    private static double lambda(double a, double b,int j){

        int n = 100;
        double p = 1d;
        double lambda = 0;
        double sumaT = 0;
        double sumaX = 0;
        double fa = 0;
        double fb = 0;
        double h = (b-a)/n;
        double[] x = new double[n+1];
        double[] t = new double[n];
        double[] fx = new double[n+1];
        double[] ft = new double[n];

        for(int i = 0;i<=n;i++){
            x[i] = a + (i*(b-a))/n;

            fx[i] = p*Math.pow(wielomian(x[i],j),2);

        }

        for(int i = 0;i<n;i++){
            t[i] = (x[i+1] + x[i])/2;

            ft[i] = p*Math.pow(wielomian(t[i],j),2);
        }

        for(int i=0;i<n;i++){
            sumaT+= ft[i];
            if(i>0){
                sumaX += fx[i];
            }
        }

        lambda = h/6 * (fx[0] + 4*sumaT + 2*sumaX + fx[n]);

        return lambda;
    }

    private static double c(double a,double b,int j){

        int n = 100;
        double c = 0;

        double p = 1d;
        double sumaT = 0;
        double sumaX = 0;
        double fa = 0;
        double fb = 0;
        double h = (b-a)/n;
        double[] x = new double[n+1];
        double[] t = new double[n];
        double[] fx = new double[n+1];
        double[] ft = new double[n];

        for(int i = 0;i<=n;i++){
            x[i] = a + (i*(b-a))/n;

            fx[i] = p*wielomian(x[i],j)*funkcja(x[i]);

        }

        for(int i = 0;i<n;i++){
            t[i] = (x[i+1] + x[i])/2;

            ft[i] = p*wielomian(t[i],j)*funkcja(t[i]);
        }

        for(int i=0;i<n;i++){
            sumaT+= ft[i];
            if(i>0){
                sumaX += fx[i];
            }
        }

        c = h/6 * (fx[0] + 4*sumaT + 2*sumaX + fx[n]);

        return (1d / lambda(a,b,j)) * c;
    }
}
