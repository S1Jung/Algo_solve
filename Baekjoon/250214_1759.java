import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        array = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<C;i++) {
            array[i]=st.nextToken().charAt(0);
        }
        Arrays.sort(array);

        sb = new StringBuilder();
        Password(0, 0);
    }
    static int L,C;
    static char[] array;
    static StringBuilder sb;
    static void Password(int count, int depth) {
        if(count == L) {
            int vowels = 0;
            int consonants = 0;
            for(int i=0;i<sb.length();i++) {
                // Read the conditions of passages well!!!
                // 1. Vowels condition 2. Consonants condition 3. No repeated letters are given
                if(sb.charAt(i)=='a' || sb.charAt(i)=='e' || sb.charAt(i)=='i'|| sb.charAt(i)=='o'|| sb.charAt(i)=='u') vowels++;
                else consonants++;
            }
            if(vowels >= 1 && consonants >= 2) System.out.println(sb);

            return;
        }

        for(int i=depth;i<C;i++) {
            sb.append(array[i]);
            Password(count+1,i+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }


}

