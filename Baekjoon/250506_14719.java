import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int max = 0;
        int[] map = new int[W];
        int[] maxMap = new int[W];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<W;i++) {
            map[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, map[i]);
            maxMap[i] = max;
        }

        max = 0;
        for(int i=W-1;i>=0;i--) {
            max = Math.max(max, map[i]);
            maxMap[i] = Math.min(maxMap[i], max);
        }

        int ans = 0;
        for(int i=0;i<W;i++) ans += maxMap[i] - map[i];
        System.out.println(ans);
    }

}

