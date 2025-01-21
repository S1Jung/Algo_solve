import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] map;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Search(r,c,d);
        int ans = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j]==2) ans++;
            }
        }

        System.out.println(ans);
    }

    public static void Search(int r, int c, int d) {
        if(map[r][c] == 0) map[r][c] = 2;
        boolean flag = false;
        for(int i=0;i<4;i++) {
            // 현재 바라보는 방향에서 90도를 회전 후 실행하고 아닐 시, for문 4번 반복은 360이므로 또 90도 회전시킨다. 따라서 현재의 d의 값을 저장하며 지속적으로 변화시킨다.
            d = (d+3)%4;
            int dx = r + dr[d];
            int dy = c + dc[d];
            if(map[dx][dy] == 0) {
                Search(dx, dy, d);
                flag = true;
                break; // 이거 안 하면 4칸 중 여러 칸이 가능할 때 다음 칸도 실행되어 답이 달라진다. 항상 처음 청소되지 않은 칸을 찾아 전진후 끝내야한다.
            }
        }
        if(flag) return;

        int dx = r+dr[(d+2)%4];
        int dy = c+dc[(d+2)%4];
        if(map[dx][dy]==1) return;
        Search(dx,dy,d);
    }
}