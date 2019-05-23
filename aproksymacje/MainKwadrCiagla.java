package aproksymacje;

public class MainKwadrCiagla {

    public static void main(String[] args) {

        int n = 8;
        double x = 0.25;
        double a = -1;
        double b = 1;
        for(int i = 4;i<=n;i++) {
            System.out.println("n : " + i + " wynik = " + aproksymacja(x, a, b, i));
        }
    }

    private static double aproksymacja(double x,double a,double b, int n) {
        double[][] macierz = new double[n][n];
        double[] wyniki = new double[n];
        double[] rozwiazania = new double[n];

        double sum;
        double solution = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                macierz[i][j] = simpsonA(a,b,i,j);
            }
            wyniki[i] = simpsonB(a,b,i);
        }

        double[][] macWyn = new double[n][n];
        double[] wCopy = new double[n];

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                macWyn[i][j] = macierz[i][j];
            }
        }

        for(int i = 0; i < n; i++) {
            wCopy[i] = wyniki[i];
        }

        for (int i = 0; i < n - 1; i++){
            for (int j = i + 1; j <= n - 1; j++){
                for (int k = 0; k < n; k++){
                    macWyn[j][k] = macierz[j][k] - (macierz[i][k] * (macierz[j][i] / macierz[i][i]));
                }

                wCopy[j] = wyniki[j] - (wyniki[i] * (macierz[j][i] / macierz[i][i]));

                for (int ii = 0; ii<n; ii++){
                    for (int jj = 0; jj<n; jj++){
                        macierz[ii][jj]=macWyn[ii][jj];
                    }
                    wyniki[ii]=wCopy[ii];
                }
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            sum = 0.0;
            for(int j = i + 1; j < n; j++) {
                sum += macierz[i][j] * rozwiazania[j];
            }
            rozwiazania[i] = (wyniki[i] - sum) / macierz[i][i];
        }

        for(int i = 0; i < 3; i++) {
            solution += rozwiazania[i] * Math.pow(x, i);
        }
        return solution;
    }

    private static double wielomian(double x, int n){
            return Math.pow(x,n);
    }



    private static double simpsonA(double a, double b,int ii,int jj){

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

            fx[i] = wielomian(x[i],ii)*wielomian(x[i],jj)*p;

        }

        for(int i = 0;i<n;i++){
            t[i] = (x[i+1] + x[i])/2;

            ft[i] = wielomian(t[i],ii)*wielomian(t[i],jj)*p;
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

    private static double simpsonB(double a, double b,int j){

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

            fx[i] = wielomian(x[i],j)*funkcja(x[i])*p;

        }

        for(int i = 0;i<n;i++){
            t[i] = (x[i+1] + x[i])/2;

            ft[i] = wielomian(t[i],j)*funkcja(t[i])*p;
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


    private static double funkcja(double x) {
        return Math.sqrt(3*Math.pow(x,2)+1);
    }
}
