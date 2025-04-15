import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static class Node {
        int row, col, dist;
        boolean isBreak;
        public Node(int row, int col, int dist, boolean isBreak) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.isBreak = isBreak;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }

        ans = Integer.MAX_VALUE;
        Search();
        System.out.println(ans == Integer.MAX_VALUE? -1 : ans);
    }

    static void Search() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,1,false));
        // Need 2 visited arrays for breaking a wall and not breaking a wall
        boolean[][][] visited = new boolean[N][M][2];
        visited[0][0][0] = true; // not breaking a wall

        while(!q.isEmpty()) {
            Node curr = q.poll();
            if(curr.row == N-1 && curr.col == M-1) {
                ans = Math.min(ans, curr.dist);
                continue;
            }

            for(int i=0;i<4;i++) {
                int mr = curr.row+dr[i];
                int mc = curr.col+dc[i];
                if(mr<0 || mc<0 || mr>=N || mc>=M) continue;
                if(map[mr][mc]==0) {
                    if(!curr.isBreak && !visited[mr][mc][0]) { // when wall is not broken
                        visited[mr][mc][0] = true;
                        q.add(new Node(mr,mc,curr.dist+1, curr.isBreak));
                    } else if(curr.isBreak && !visited[mr][mc][1]){ // wall was broken so need to use a second visited array
                        visited[mr][mc][1] = true;
                        q.add(new Node(mr,mc,curr.dist+1, curr.isBreak));
                    }
                } else {
                    if(!curr.isBreak && !visited[mr][mc][1]) { // break the wall
                        visited[mr][mc][1] = true;
                        q.add(new Node(mr,mc,curr.dist+1, true));
                    }
                }

            }
        }
    }
}
