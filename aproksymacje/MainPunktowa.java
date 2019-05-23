package aproksymacje;

public class MainPunktowa {
    public static void main(String[] args){

        int n = 3;
        double xx = 0.25;
        System.out.println(aproksymacja(n,xx));
    }



    private static double aproksymacja(int n,double xx){
        double wynik = 0;
        //double[] x = {1,2,3,4};
        //double[] y = {6,19,40,69};
        double[][] macierz = new double[n+1][2*n];
        double[][] macierzW = new double[n+1][n+1];
        double[] wyniki = new double[n+1];


        double[] x = {-1,-0.5,0,0.5,1};
        double[] y = new double[x.length];

        for(int i = 0;i<x.length;i++){
            y[i] = funkcja(x[i]);
        }


        for(int i = 0;i<n+1;i++){
            for(int j = 0;j<2*n;j++) {
                if (j < n + 1) {
                    macierz[i][j] = suma(x, y, n, i + j);
                }else {
                    macierz[i][j] = suma(x,y,n,2*j+i-1);
                }
            }
        }

        for(int i = 0;i<n+1;i++){
            for(int j = 0;j<n+1;j++){
                macierzW[i][j] = macierz[i][j];
            }
        }

        for(int i = 0;i<n+1;i++){
            wyniki[i] = macierz[i][3];
        }

        for (int i = 0;i<wyniki.length;i++){
            wynik+= Math.pow(xx,i)*gauss(macierzW,wyniki,n+1,i);
        }



        return wynik;
}

    private static double gauss(double[][] macierz,double[] wyniki, int n,int kk){

        double[][] macWyn = new double[macierz.length][macierz.length];
        double[] wCopy = new double[wyniki.length];
        double[] rozwiazania = new double[macierz.length];

        for(int i = 0; i < macierz.length; i++) {
            for (int j = 0; j < macierz.length; j++) {
                macWyn[i][j] = macierz[i][j];
            }
        }

        for(int i = 0; i < wyniki.length; i++) {
            wCopy[i] = wyniki[i];
        }

        for (int i = 0; i < n - 1; i++){
            for (int j = i + 1; j <= n - 1; j++){
                for (int k = 0; k < n; k++){
                    macWyn[j][k] = macierz[j][k] - (macierz[i][k] * (macierz[j][i] / macierz[i][i]));
                }

                wCopy[j] = wyniki[j] - (wyniki[i] * (macierz[j][i] / macierz[i][i]));

                for (int ii = 0; ii<macWyn.length; ii++){
                    for (int jj = 0; jj<macWyn.length; jj++){
                        macierz[ii][jj]=macWyn[ii][jj];
                    }
                    wyniki[ii]=wCopy[ii];
                }
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for(int j = i + 1; j < n; j++) {
                sum += macierz[i][j] * rozwiazania[j];
            }
            rozwiazania[i] = (wyniki[i] - sum) / macierz[i][i];
        }

        return rozwiazania[kk];
    }

    private static double suma(double[] x,double[] y,int n,int j){
        int length = 3*n+2;
        double[] suma = new double[length];

        for(int i=0;i<length;i++){

            if(i<(2*n+1)){
                suma[i] = sumaX(x,n,i);
            }else{
                suma[i] = sumaXY(x,y,n,i);
            }
            //System.out.println(suma[i]);
        }

        return suma[j];
    }

    private static double sumaX(double[] x,int n,int i){
        double value = 0;
        for(int j=0;j<(2*n);j++){
            value += Math.pow(x[j],i);
        }
        return value;
    }

    private static double sumaXY(double[] x,double[] y,int n,int i){
        double value = 0;
        for(int j=0;j<(2*n);j++){
            value += Math.pow(x[j],i-(2*n+1))*y[j];
        }
        return value;
    }

    private static double funkcja(double x) {
        return Math.sqrt(3*Math.pow(x,2)+1);
    }



}
