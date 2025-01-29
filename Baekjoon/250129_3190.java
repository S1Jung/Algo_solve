import java.io.*;
import java.util.*;

public class Main {
    static int N, ans;
    static int[][] map;
    static int[] dr={0,1,0,-1};
    static int[] dc={1,0,-1,0};
    static LinkedList<Node> al, snake;
    static class Node {
        int num,x,y;
        char dir;
        public Node(int num, char dir) {
            this.num = num;
            this.dir = dir;
        }
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x-1][y-1] = 2;
        }

        al = new LinkedList<>();
        snake = new LinkedList<>();
        int L = Integer.parseInt(br.readLine());
        for(int i=0;i<L;i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            al.add(new Node(num, dir));
        }
        ans = 0;
        Play(0,0,0);
        System.out.println(ans);
    }

    static void Play(int cr, int cc, int cdir) {
        snake.add(new Node(cr,cc));
        map[cr][cc] = 1;

        while(true) {
            ans++;
            cr += dr[cdir];
            cc += dc[cdir];
            if(cr<0 || cc <0 || cr>=N || cc>=N || map[cr][cc]==1) break;
            // 뱀 머리는 항상 배열의 앞에 추가해야한다.
            snake.addFirst(new Node(cr,cc));
            if(map[cr][cc] != 2) {
                Node last = snake.removeLast();
                map[last.x][last.y] = 0;
            }
            map[cr][cc] = 1;

            // 배열 없을 경우 실행될 수 있기에 isEmpty()
            if(!al.isEmpty() && ans==al.get(0).num){
                if(al.get(0).dir=='D') cdir = (cdir+1)%4;
                else cdir = (cdir+3)%4;
                al.removeFirst();
            }
        }
    }
}