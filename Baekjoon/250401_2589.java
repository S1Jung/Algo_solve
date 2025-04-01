import java.io.*;
import java.util.*;

public class Main {
    static int N,M,ans;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static char[][] map;
    static boolean[][] visited;
    static class Node {
        int row, col, dist;
        public Node (int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0;i<N;i++) {
            String str = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = str.charAt(j);
            }
        }

        ans = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j]=='L') {
                    visited = new boolean[N][M];
                    Search(i,j,0);
                }
            }
        }

        System.out.println(ans);
    }

    static void Search(int r, int c, int count) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r,c,count));
        visited[r][c] = true;

        while(!q.isEmpty()) {
            Node curr = q.poll();
            ans = Math.max(ans, curr.dist);
            for(int i=0;i<4;i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];
                if(mr<0 || mc<0 || mr>=N || mc>=M || visited[mr][mc]||map[mr][mc]=='W') continue;
                visited[mr][mc] = true;
                q.add(new Node(mr,mc,curr.dist+1));
            }
        }
    }
}