import java.io.*;
import java.util.*;

public class Main {
    static int ans;
    static ArrayList<Node> ladder, snake;
    static boolean[] visited;
    static class Node {
        int start, end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ladder = new ArrayList<>();
        snake = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            ladder.add(new Node(start, end));
        }
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            snake.add(new Node(start, end));
        }
        visited = new boolean[101];
        ans = Integer.MAX_VALUE;
        Cast(1);
        System.out.println(ans);
    }

    static void Cast(int start) {
        // 최종 종착지에 대한 '최소'를 구하는 것이므로 BFS를 사용한다.
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(start, 0));
        visited[start] = true;

        while(!q.isEmpty()) {
            Node curr = q.poll();
            if(curr.start==100) ans = Math.min(ans,curr.end);
            a:for(int i=1;i<=6;i++) {
                int move = curr.start+i;
                if(move > 100 || visited[move]) continue;
                visited[move] = true;
                for(Node lad : ladder) {
                    if(lad.start == move) {
                        q.add(new Node(lad.end, curr.end+1));
                        continue a;
                    }
                }
                for(Node sn : snake) {
                    if(sn.start == move) {
                        q.add(new Node(sn.end, curr.end+1));
                        continue a;
                    }
                }

                q.add(new Node(move, curr.end+1));
            }
        }
    }
}