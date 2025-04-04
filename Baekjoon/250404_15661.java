import java.io.*;
import java.util.*;

public class Main {
    static int N, ans, num;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];
        ans = Integer.MAX_VALUE;
        Search(0);
        System.out.println(ans);
    }

    static void Search(int count) {
        if(count == N) {
            if(num == N || num == 0) return;
            int team1 = 0;
            int team2 = 0;
            for(int i=0;i<N;i++) {
                if(visited[i]) {
                    for(int j=i+1;j<N;j++) {
                        if(visited[j]) { // 첫 팀으로 선정 시
                            team1 += map[i][j]+map[j][i];
                        }
                    }
                }
                else {
                    for(int j=i+1;j<N;j++) {
                        if(!visited[j]) { // 첫 팀으로 선정안된 다른 팀
                            team2 += map[i][j]+map[j][i];
                        }
                    }
                }
            }
            ans = Math.min(ans, Math.abs(team1-team2));
            return;
        }

        for(int i=count;i<N;i++) {
            visited[i] = true;
            num++;
            Search(i+1);
            visited[i] = false;
            num--;
        }
    }
}