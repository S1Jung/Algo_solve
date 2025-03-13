import java.io.*;
import java.util.*;

public class Main {
    static int N,M,x,y;
    static int[] dr = {0,0,-1,1}; // east, west, north, south
    static int[] dc = {1,-1,0,0};
    static int[] dice;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[6]; // up, down, left, right, front, back
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++) {
            int dir = Integer.parseInt(st.nextToken());
            Cast(dir);
        }
    }

    static void Cast(int dir) {
        x += dr[dir-1];
        y += dc[dir-1];
        if(x<0||y<0||x>=N||y>=M) {
            x -= dr[dir-1];
            y -= dc[dir-1];
            return;
        }

        int[] clone = new int[6];
        for(int i=0;i<6;i++) clone[i] = dice[i];

        switch(dir) {
            case 1:
                dice[0] = clone[2];
                dice[3] = clone[0];
                dice[1] = clone[3];
                dice[2] = clone[1];
                break;
            case 2:
                dice[2] = clone[0];
                dice[1] = clone[2];
                dice[3] = clone[1];
                dice[0] = clone[3];
                break;
            case 3:
                dice[0] = clone[4];
                dice[4] = clone[1];
                dice[1] = clone[5];
                dice[5] = clone[0];
                break;
            case 4:
                dice[4] = clone[0];
                dice[1] = clone[4];
                dice[5] = clone[1];
                dice[0] = clone[5];
                break;
        }

        if(map[x][y]==0) map[x][y] = dice[1];
        else {
            dice[1] = map[x][y];
            map[x][y] = 0;
        }

        System.out.println(dice[0]);
    }
}