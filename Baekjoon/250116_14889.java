import java.io.*;
import java.util.*;

public class Main {
    static int N, ans;
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
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        ans = Integer.MAX_VALUE;
        visited = new boolean[N]; // 해당 코드는 1~N까지의 숫자 중 팀 고르는 용도

        Search(0,0);

        System.out.println(ans);
    }

    static void Search(int count, int depth) {
        if(count == N/2) {
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

        for(int i=depth;i<N;i++) {
            visited[i]=true;
            Search(count+1, i+1);
            visited[i]=false;
            // Search(count, i+1); 해당 코드는 불필요하다. 이미 visited[i] = false;로 설정되어 있기 때문에 중복된 탐색을 하기 때문.
        }
    }
}