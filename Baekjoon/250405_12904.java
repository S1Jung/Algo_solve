import java.io.*;
import java.util.*;

public class Main {
    static String S, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

       S = br.readLine();
       T = br.readLine();

       Search();
    }

    static void Search() {
        while(S.length() < T.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(T);

            if(T.charAt(T.length()-1)=='A') {
                sb.deleteCharAt(T.length()-1);
            } else {
                sb.deleteCharAt(T.length()-1);
                sb.reverse();
            }
            T = sb.toString();
        }

        System.out.println(S.equals(T) ? 1 : 0);
    }
}