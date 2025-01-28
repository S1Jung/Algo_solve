import java.io.*;
import java.util.*;

public class Main {
    static int N,M,ans;
    static int[][] map, copied;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static class Node {
        int row;
        int col;
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
        copied = new int[N][M];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        Block(0,0,0);

        System.out.println(ans);
    }

    static void Block(int count, int row, int col) {
        if(count == 3) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    copied[i][j] = map[i][j];
                }
            }

            SafeCount();
            return;
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                // 아래 조건은 그 전 순서의 map을 바꿨던 순서 스킵용
                if(i<row || (i==row && j<col)) continue;
                if(map[i][j]==0) {
                    map[i][j] = 1;
                    Block(count+1, i, j);
                    map[i][j] = 0;
                }
            }
        }
    }

    static void SafeCount() {
        Queue<Node> q = new ArrayDeque<>();

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if (copied[i][j]==2) q.add(new Node(i,j));
            }
        }

        while(!q.isEmpty()) {
            Node curr = q.poll();
            for(int i=0;i<4;i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];
                if(mr<0 || mc<0 || mr>=N || mc >=M) continue;
                if(copied[mr][mc]==0) {
                    copied[mr][mc] = 2;
                    q.add(new Node(mr,mc));
                }
            }
        }

        int count = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if (copied[i][j]==0) count++;
            }
        }
        ans = Math.max(ans, count);
    }
}