import java.io.*;
import java.util.*;

public class Main {
    static int N, ans;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static class Node implements Comparable<Node> {
        int row, col, value;
        public Node (int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int count = 0;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N==0) break;

            map = new int[N][N];
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            count++;
            Search();
            System.out.println("Problem "+count+": "+ans);
        }
    }

    static void Search() {
        PriorityQueue<Node> pq  = new PriorityQueue<>();
        pq.add(new Node(0,0,map[0][0]));
        int[][] arr = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(arr[i], Integer.MAX_VALUE);
        }

        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            if(curr.row==N-1 && curr.col==N-1) break;

            for(int i=0;i<4;i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];
                if(mr<0 || mc<0 || mr>=N || mc>=N) continue;
                if(curr.value+map[mr][mc]<arr[mr][mc]) {
                    arr[mr][mc] = curr.value+map[mr][mc];
                    pq.add(new Node(mr,mc,arr[mr][mc]));
                }

            }

        }
        ans = arr[N-1][N-1];
    }
}
