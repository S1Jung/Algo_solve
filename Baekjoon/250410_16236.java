import java.io.*;
import java.util.*;

public class Main {
    static int N, ans, size, count;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static class Fish implements Comparable<Fish> {
        int row, col, dist;
        public Fish (int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        public int compareTo (Fish o) {
            if(this.dist != o.dist) {
                return this.dist - o.dist;
            } else if(this.row != o.row){
                return this.row - o.row;
            } else return this.col - o.col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int startX = 0;
        int startY = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==9) {
                    startX = i;
                    startY = j;
                    map[i][j] = 0;
                }
            }
        }
        size = 2;
        count = 0;
        ans = 0;

        while(true) {
            Fish curr = Search(startX, startY);
            if(curr == null) break;

            ans += curr.dist;
            startX = curr.row;
            startY = curr.col;
            map[startX][startY] = 0;
            count++;
            if(count == size) {
                size++;
                count = 0;
            }
        }

        System.out.println(ans);
    }

    static Fish Search(int startX, int startY) {
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        pq.add(new Fish(startX, startY, 0));
        boolean[][] visited = new boolean[N][N];
        visited[startX][startY] = true;

        while(!pq.isEmpty()) {
            Fish curr = pq.poll();
            if (map[curr.row][curr.col] != 0 && map[curr.row][curr.col] < size) {
                return curr;
            }

            for(int i=0;i<4;i++) {
                int mr = curr.row + dr[i];
                int mc = curr.col + dc[i];
                if(mr<0 || mc<0 || mr >=N || mc >=N || visited[mr][mc] || map[mr][mc] > size) continue;
                pq.add(new Fish(mr, mc, curr.dist+1));
                visited[mr][mc] = true;
            }
        }
        return null;
    }
}