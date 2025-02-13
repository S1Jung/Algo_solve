import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static LinkedList<Character> list;
    static boolean[] visited;
    static int ans,R,C;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i=0;i<R;i++) {
            String str = br.readLine();
            for(int j=0;j<C;j++) {
                map[i][j] = str.charAt(j);
            }
        }

        ans = 0;

        visited = new boolean[26];
        list = new LinkedList<>();
        list.add(map[0][0]);
        visited[map[0][0]-'A'] = true;
        Search(0,0);

        System.out.println(ans);
    }

    static void Search(int x, int y) {
        boolean flag = true;
        for(int i=0;i<4;i++) {
            int mr = x + dr[i];
            int mc = y + dc[i];
            // 방문처리 시 list.contains를 사용하려면 Hashset을 사용해 시간 복잡도를 O(1)로 해야한다.
            // linkedlist나 arraylist에서는 list.contains의 시간 복잡도가 O(n)이라 시간 초과 발생
            // 방문 여부가 해당 문제 시간 초과의 핵심이다.
            if(mr<0 || mc<0 || mr>=R || mc>=C || visited[map[mr][mc]-'A']) continue;
            if(flag) flag = false;
            list.add(map[mr][mc]);
            visited[map[mr][mc]-'A'] = true;
            Search(mr,mc);
            list.removeLast();
            visited[map[mr][mc]-'A'] = false;
        }
        if(flag) {
            ans = Math.max(ans, list.size());
            return;
        }
    }
}

