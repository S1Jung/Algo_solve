import java.io.*;
import java.util.*;

public class Main {
    static int N,M,ans;
    static ArrayList<ArrayList<Integer>> al;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        al = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            al.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            al.get(start).add(end);
            al.get(end).add(start);
        }
        ans = 0;
        for(int i=0;i<N;i++) {
            visited = new boolean[N];
            visited[i] = true;
            Search(i, 1);
            // skipping below condition will cause timeout
            if(ans==1) break;
        }

        System.out.println(ans);
    }

    static void Search(int num, int depth) {
        // just have to find 5 depth
        if(depth == 5) {
            ans = 1;
            return;
        }

        for(int next : al.get(num)) {
            if(!visited[next]) {
                visited[next] = true;
                Search(next, depth+1);
                visited[next] = false;
            }
        }
    }
}