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
        public Node(int row, int col) {
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
        int cheese;
        loop : while(true) {
            count++;
            cheese = 0;
            visited = new boolean[N][M];

            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                   if((i==0 || i==N-1 || j==0 || j==M-1) && map[i][j]==0) Spread(i,j);
                }
            }

            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(map[i][j]==1) {
                        IsOutside(i,j);
                        cheese++;
                    }
                }
            }

            for(int i=0;i<N;i++) {
                for (int j=0;j<M;j++) {
                    if(map[i][j]==2 || map[i][j]==3) map[i][j]=0;
                }
            }

            for(int i=0;i<N;i++) {
                for (int j=0;j<M;j++) {
                    if(map[i][j]!=0) continue loop;
                }
            }
            break;
        }

        System.out.println(count);
        System.out.println(cheese);
    }

    static void Spread(int r, int c) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(r,c));
        visited[r][c] = true;

        while(!q.isEmpty()) {
            Node curr = q.poll();
            map[curr.row][curr.col] = 2;

            for(int i=0;i<4;i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];
                if(mr<0||mc<0||mr>=N||mc>=M||visited[mr][mc]||map[mr][mc]!=0) continue;

                q.add(new Node(mr,mc));
                visited[mr][mc] = true;
            }
        }
    }

    static void IsOutside(int r, int c) {
        for(int i=0;i<4;i++) {
            int mr = r + dr[i];
            int mc = c + dc[i];
            if(mr<0||mc<0||mr>=N||mc>=M||map[mr][mc]!=2) continue;

            map[r][c]=3;
            break;
        }
    }
}