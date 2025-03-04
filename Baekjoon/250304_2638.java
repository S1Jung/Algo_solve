import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static boolean[][] visited;
    static class Node {
        int row, col;
        public Node (int row, int col) {
            this.row = row;
            this.col = col;
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
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;

        loop : while(true) {
            visited = new boolean[N][M];
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    // Check outside area
                    if((i==0||j==0||i==N-1||j==M-1) && map[i][j]==0) Outside(i,j);
                }
            }

            // Cheese melt
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(map[i][j]==1) CheeseMelt(i,j);
                }
            }

            // Renew map
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(map[i][j]==2 || map[i][j]==3) map[i][j]=0;
                }
            }

            count++;

            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(map[i][j]!=0) continue loop;
                }
            }
            break;
        }
        System.out.println(count);
    }

    static void Outside(int r, int c) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r,c));
        visited[r][c] = true;
        while(!q.isEmpty()) {
            Node curr = q.poll();
            map[curr.row][curr.col] = 2;
            for(int i=0;i<4;i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];
                if(mr<0||mc<0||mr>=N||mc>=M||map[mr][mc]!=0||visited[mr][mc]) continue;
                visited[mr][mc] = true;
                q.add(new Node(mr,mc));
            }
        }
    }

    static void CheeseMelt(int r, int c) {
        int outside = 0;
        for(int i=0;i<4;i++) {
            int mr = r + dr[i];
            int mc = c + dc[i];
            if(mr<0||mc<0||mr>=N||mc>=M||map[mr][mc]!=2) continue;
            outside++;
        }
        if(outside >= 2) map[r][c] = 3;
    }
}