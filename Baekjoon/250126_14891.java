import java.io.*;
import java.util.*;

public class Main {
    static LinkedList<LinkedList<Integer>> ll;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ll = new LinkedList<>();
        for(int i=0;i<4;i++) {
            ll.add(new LinkedList<>());
            String str = br.readLine();
            for(int j=0;j<8;j++) {
                ll.get(i).add(Integer.parseInt(String.valueOf(str.charAt(j))));
            }
        }

        int K = Integer.parseInt(br.readLine());
        for(int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int rotate = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            visited = new boolean[4];
            Move(rotate-1, dir);
        }

        System.out.println(Ans());
    }

    static void Move(int rotate, int dir) {
        visited[rotate] = true;
        // 현재 배열 회전 전에 양 옆 회전 조건 확인
        boolean right = false;
        if(rotate<3 && ll.get(rotate).get(2) != ll.get(rotate+1).get(6) && !visited[rotate+1]) right = true;

        boolean left = false;
        if(rotate>0 && ll.get(rotate).get(6) != ll.get(rotate-1).get(2) && !visited[rotate-1]) left = true;

        // 현재 배열 회전
        if(dir == 1) ll.get(rotate).add(0,ll.get(rotate).removeLast());
        else ll.get(rotate).add(7,ll.get(rotate).removeFirst());

        // 양 옆 회전 조건 바탕으로 회전 시작
        if(right) Move(rotate+1, -dir);
        if(left) Move(rotate-1, -dir);
    }

    static int Ans() {
        int total = 0;
        for(int i=0;i<4;i++) {
            if(ll.get(i).get(0) == 1) total += (int) Math.pow(2,i);
        }
        return total;
    }
}