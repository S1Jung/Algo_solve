import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> al;
    static int N,ans,count;
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

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0;i<N;i++) {
            String[] str = br.readLine().split("");
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(str[j]);
            }
        }
        ans = 0;
        visited = new boolean[N][N];
        al = new ArrayList<>();
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j]==0 || visited[i][j]) continue;
                ans++;
                count = 1;
                Search(i,j);
            }
        }

        System.out.println(ans);
        if(ans != 0) {
            Collections.sort(al);
            for(int num : al) {
                System.out.println(num);
            }
        }
    }

    static void Search(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        visited[x][y] = true;
        al.add(count);
        q.add(new Node(x,y));

        while(!q.isEmpty()) {
             Node curr = q.poll();
             for(int i=0;i<4;i++) {
                 int mr = curr.row + dr[i];
                 int mc = curr.col + dc[i];
                 if(mr<0||mc<0||mr>=N||mc>=N||visited[mr][mc]||map[mr][mc]==0) continue;
                 visited[mr][mc] = true;
                 q.add(new Node(mr, mc));
                 al.remove(al.size()-1);
                 al.add(++count);
             }
        }
    }

}