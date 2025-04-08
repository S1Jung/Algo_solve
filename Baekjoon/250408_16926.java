import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<R;i++) {
            Move();
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    static void Move () {
        int num = Math.min(N/2, M/2);
        for(int i=0;i<num;i++) {
            int temp = map[i][i];
            for(int j=1+i;j<M-i;j++) {
                map[i][j-1] = map[i][j];
            }

            for(int j=1+i;j<N-i;j++) {
                map[j-1][M-1-i] = map[j][M-1-i];
            }

            for(int j=M-1-i;j>0+i;j--) {
                map[N-1-i][j] = map[N-1-i][j-1];
            }

            for(int j=N-1-i;j>0+i;j--) {
                map[j][i] = map[j-1][i];
            }

            map[i+1][i] = temp;
        }
    }
}