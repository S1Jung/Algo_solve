import java.io.*;
import java.util.*;

public class Main {
    static int N,K,S,X,Y,ans;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static class Node implements Comparable<Node> {
        int row, col;
        public Node (int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int compareTo (Node o) {
            return map[this.row][this.col] - map[o.row][o.col];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        ans = 0;
        BFS();
        System.out.println(ans);
    }

    static void BFS() {
        PriorityQueue<Node>[] pq = new PriorityQueue[2];
        pq[0] = new PriorityQueue<>();
        pq[1] = new PriorityQueue<>();
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j]!=0) pq[0].add(new Node(i,j));
            }
        }

        int count = 0;
        while(count<S) {
            while(!pq[count%2].isEmpty()) {
                Node curr = pq[count%2].poll();
                for(int i=0;i<4;i++) {
                    int mr = curr.row + dr[i];
                    int mc = curr.col + dc[i];
                    if(mr<0 || mc<0 || mr>=N || mc>=N || map[mr][mc]!=0) continue;
                    map[mr][mc] = map[curr.row][curr.col];
                    pq[(count%2+1)%2].add(new Node(mr,mc));
                }
            }
            count++;
        }
        ans = map[X-1][Y-1];
    }
}

