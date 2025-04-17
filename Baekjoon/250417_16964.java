import java.io.*;
import java.util.*;

public class Main {
    static int N,ans, index;
    static int[] map, sequence;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> al;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        al = new ArrayList<>();
        for(int i=0;i<=N;i++) {
            al.add(new ArrayList<>());
        }
        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            al.get(start).add(end);
            al.get(end).add(start);
        }

        map = new int[N];
        sequence = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            map[i] = Integer.parseInt(st.nextToken());
            sequence[map[i]] = i;
        }

        for(int i=1;i<=N;i++) { // aligning arraylists that are included in arraylist by the sequence of given map[i] inputs
            ArrayList<Integer> list = al.get(i);
            list.sort((a,b)-> {
               int x = sequence[a];
               int y = sequence[b];
               return x-y;
            });
        }

        visited = new boolean[N+1];
        ans = 1;
        index = 0; // should be separated cause it is accumulated and have to be used after 'for statement'
        search(1);
        System.out.println(ans);
    }

    static void search(int curr) {
        visited[curr] = true;
        for(int next : al.get(curr)) {
            if(!visited[next]) {
                index++;
                if(map[index] != next) {
                    ans = 0;
                    return;
                }
                search(next);
            }
        }
    }
}
