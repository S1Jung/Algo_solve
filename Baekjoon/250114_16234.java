import java.io.*;
import java.util.*;

public class Main {
    static int N,L,R,ans;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,1,-1};
    static int[][] map;
    static boolean flag;
    static boolean[][] visited;
    static ArrayList<Node> al;
    static class Node {
        int row;
        int col;
        public Node (int row, int col) {
            this.row=row;
            this.col=col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        ans = 0;
        map = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            flag = false;
            visited = new boolean[N][N]; // 여기서 선언하는 이유: 한 맵에서 다른 여러 부분에서 인구 이동 가능
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    // 중복 탐색 방지 + 인구 이동한 부분에 대해 검색 안된 지역에서 겹쳐서 인구이동할 가능성 배제
                    if(visited[i][j]) continue;
                    Move(new Node(i,j));
                }
            }
            if(flag) ans++; // 한 사이클 끝나고 날짜 증가
            else break;
        }

        System.out.println(ans);
    }

    static void Move(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        al = new ArrayList<>();
        al.add(new Node(node.row, node.col));

        visited[node.row][node.col]=true;

        while(!q.isEmpty()) {
            Node curr = q.poll();
            for(int i=0;i<4;i++) {
                int dx = curr.row+dr[i];
                int dy = curr.col+dc[i];
                if(dx<0 || dy<0 || dx>=N || dy>=N || visited[dx][dy]) continue;
                if(Math.abs(map[dx][dy]-map[curr.row][curr.col])>=L && Math.abs(map[dx][dy]-map[curr.row][curr.col]) <= R) {
                    visited[dx][dy] = true;
                    q.add(new Node(dx,dy));
                    al.add(new Node(dx,dy));
                }
            }
        }

        if(al.size() > 1) {
            flag = true;
            int total= 0;
            for(Node curr : al) {
                total += map[curr.row][curr.col];
            }
            for(Node curr: al) {
                map[curr.row][curr.col] = total/al.size();
            }
        }

    }
}