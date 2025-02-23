import java.io.*;
import java.util.*;

public class Main {
    static int R,C;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static int[] airCleaner;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        airCleaner = new int[2];
        map = new int[R][C];
        for(int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==-1) {
                    if(airCleaner[0]==0) airCleaner[0] = i;
                    else airCleaner[1]= i;
                }
            }
        }

        for(int i=0;i<T;i++) {
            Spread();
            Move();
        }

        int count = 0;
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(map[i][j] > 0) count += map[i][j];
            }
        }
        System.out.println(count);
    }

    static void Spread() {
        int[][] copied = new int[R][C];
        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                copied[i][j] = map[i][j];
            }
        }

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                if(map[i][j]==0 || map[i][j]==-1) continue;

                for(int k=0;k<4;k++) {
                    int mr = i + dr[k];
                    int mc = j + dc[k];
                    if(mr<0 || mc<0 || mr>=R || mc>=C || map[mr][mc]==-1) continue;
                    copied[mr][mc] += map[i][j]/5;
                    copied[i][j] -= map[i][j]/5;
                }
            }
        }

        for(int i=0;i<R;i++) {
            for(int j=0;j<C;j++) {
                map[i][j] = copied[i][j];
            }
        }
    }

    static void Move() {
        // top to down
        for(int i=airCleaner[0]-1;i>0;i--) {
            map[i][0] = map[i-1][0];
        }

        // right to left
        for(int i=0;i<C-1;i++) {
            map[0][i] = map[0][i+1];
        }

        // down to top
        for(int i=0;i<airCleaner[0];i++) {
            map[i][C-1] = map[i+1][C-1];
        }

        // left to right
        for(int i=C-1;i>1;i--) {
            map[airCleaner[0]][i] = map[airCleaner[0]][i-1];
        }
        map[airCleaner[0]][1] = 0;

        // down to top
        for(int i=airCleaner[1]+1;i<R-1;i++) {
            map[i][0] = map[i+1][0];
        }

        // right to left
        for(int i=0;i<C-1;i++) {
            map[R-1][i] = map[R-1][i+1];
        }

        // top to down
        for(int i=R-1;i>airCleaner[1];i--) {
            map[i][C-1] = map[i-1][C-1];
        }

        // left to right
        for(int i=C-1;i>1;i--) {
            map[airCleaner[1]][i] = map[airCleaner[1]][i-1];
        }
        map[airCleaner[1]][1] = 0;
    }

}