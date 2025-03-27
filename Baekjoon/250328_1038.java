import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Long> al;
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        if (N <= 10) {
            System.out.println(N);
        } else if(N > 1022) { // At first, have to know maximum N is 1023 by not limiting this condition
            System.out.println(-1);
        } else {
            al = new ArrayList<>(); // Since the Max num will be 9876543210, so have to use 'long'
            str = "";
            for(int i=0;i<10;i++) {
                str += i;
                al.add((long) i);
                Search(i);
                str = "";
            }
            Collections.sort(al);
            System.out.println(al.get(N));
        }
    }

    static void Search(long index) {
        if(str.length()>9) {
            return;
        }

        for(int i=0;i<index;i++) {
            String curr = str;
            str += i;
            al.add(Long.parseLong(str));
            Search(i);
            str = curr;
        }

    }
}