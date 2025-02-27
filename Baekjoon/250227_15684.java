import java.io.*;
import java.util.*;

public class Main {
    static int N,H,ans;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N];
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a-1][b-1] = 1;
            map[a-1][b] = 2;
        }


        ans = 4;
        Search(0,0,0);

        System.out.println(ans > 3 ? -1:ans);
    }

    static void Search(int count,int row, int col) {
        if(count >= ans) return;
        boolean flag = true;
        for(int i=0;i<N;i++) {
            int x = 0;
            int y = i;
            while (x<H) {
                if(map[x][y]==1) y++;
                else if(map[x][y]==2) y--;
                x++;
            }
            if(y!=i) flag = false;
        }
        if(flag) {
            ans = Math.min(count, ans);
            return;
        }
        if(count == 3) return;

        for(int i=row;i<H;i++) {
            for(int j=0;j<N-1;j++) {
                if(i==row && j<col) continue;
                if(map[i][j]==0 && map[i][j+1]==0) {
                    if (j > 0 && map[i][j-1]==1) continue;
                    if (j < N-2 && map[i][j+2]==2) continue;
                    map[i][j]=1;
                    map[i][j+1]=2;
                    Search(count+1,i,j+1);
                    map[i][j]=0;
                    map[i][j+1]=0;
                }
            }
        }

    }
}