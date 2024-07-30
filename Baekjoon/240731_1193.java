import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int x = 0;
        int y = 0;
        for(int i=1;;i++) {
            x += i;
            if(x>=N) {
                y = i;
                break;
            }
        }
        if(y % 2 == 0) {
            int top = N-(x-y);
            int bot = x-N+1;
            System.out.println(top + "/" + bot);
        } else {
            int top = N-(x-y);
            int bot = x-N+1;
            System.out.println(bot + "/" + top);
        }
    }
}