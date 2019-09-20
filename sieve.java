import java.util.ArrayList;
import java.util.Scanner;

public class sieve {

    public static int[] sieveOfEratosthenes(int j){
        int P[] = new int[j+1];
        P[0] = 0;
        int i;
        for (i = 1; i < j+1 ; i++) {
            P[i] = 1;
        }
        int p = 2;
        while(p <= (Math.sqrt(j))){
            i = p+p;
            while(i <= j){
                P[i]=0;
                i = i+p;
            }
            i = p+1;
            while (i <= Math.sqrt(j) && P[i]==0){
                i = i+1;
            }
            p = i;
        }
        return P;
    }

    public static void modifiedSieveOfEratosthenes(int I, int J){

        int L = (int) Math.sqrt(J);
        int[] P = sieveOfEratosthenes(J);
        char A[] = new char[J-I];

        for (int i = 0; i < J-I ; i++) {
            A[i] = '1';
        }

        for(int p=2; p<L; p++){
            if(P[p] == 0) continue;

            double t = (double)I/(double)p;
            t = Math.ceil(t) * p;
            int x = (int) t;
            while(x < J) {
                A[x-I] = '0';
                x=x+p;
            }
        }

        int count = 0;
        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = I; i < J ; i++) {
            if(A[i-I] == '1'){
                count++;
                primes.add(i);
            }
        }

        System.out.println(count);
        System.out.println(primes.get(0)+ " "+ primes.get(1));
        System.out.println(primes.get(primes.size()-2) + " " + primes.get(primes.size()-1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        int num1 = Integer.parseInt(arr[0]);
        int num2 = Integer.parseInt(arr[1]);
        sc.close();
        long start = System.currentTimeMillis();
        modifiedSieveOfEratosthenes(num1,num2);
        long stop = System.currentTimeMillis();
        System.out.println(stop-start);
        //sieveOfEratosthenes(4);
    }
}
