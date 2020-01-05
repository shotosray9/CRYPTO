import java.util.ArrayList;
import java.util.Scanner;

public class lfsr {

    public static String LinearFeedbackShiftRegister(int n, String initCont,
                                            ArrayList<Integer> tapIndexes, int len){

        String keyStream="";
        for (int i = 0; i < len; i++) {
            keyStream += initCont.charAt(n-1);
            char c = newLeft(tapIndexes, initCont);
            initCont = c + initCont.substring(0, n-1);
        }
        return keyStream;
    }

    public static char newLeft (ArrayList<Integer> taps, String register){
        int sum = 0;

        for (int i = 0; i < taps.size(); i++) {
            char s = register.charAt(taps.get(i));
            sum += Character.getNumericValue(s);
        }

        char ret = sum%2==0 ? '0' : '1';
        return ret;
    }

    public static ArrayList<Integer> tapIndexes(String tap){
        ArrayList<Integer> tapOn = new ArrayList<>();

        for (int i = 0; i < tap.length(); i++) {
            if(tap.charAt(i)=='1')
                tapOn.add(i);
        }
        return tapOn;
    }

    public static String xor(String keyStream, String plaintext, int n){
        String x="";
        for (int i = 0; i < n ; i++) {
            if(keyStream.charAt(i) != plaintext.charAt(i)){
                x+="1";
            }
            else
                x+="0";
        }
        return x;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String initContent = sc.nextLine();
        String tapVector = sc.nextLine();
        String plainOrCipher = sc.nextLine();

        ArrayList<Integer> taps = tapIndexes(tapVector);

        String key = LinearFeedbackShiftRegister(n, initContent, taps, plainOrCipher.length());

        System.out.println(key);
        System.out.println(xor(key, plainOrCipher, plainOrCipher.length()));
    }
}

// 1010 1111 0010 0111 1011