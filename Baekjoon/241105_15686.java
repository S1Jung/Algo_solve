import java.io.*;
import java.util.*;

public class Main {
    static int N,M,ans;
    static boolean[] visited;
    static int[][] map;
    static List<Location> home, store;
    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        home = new ArrayList<>();
        store = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 1) {
                    home.add(new Location(i,j));
                } else if (num == 2) {
                    store.add(new Location(i,j));
                }
            }
        }
        visited = new boolean[store.size()];
        ans = Integer.MAX_VALUE;

        Choose(0,0);

        System.out.println(ans);
    }
    static void Choose(int selected, int index) {
        if(selected == M) {
            Calculate();
            return;
        }
        for(int i=index;i<store.size();i++) {
            if(!visited[i]) {
                visited[i]=true;
                Choose(selected+1, i+1);
                visited[i]=false;
            }
        }
    }

    static void Calculate() {
        int totalDist = 0;
        for(int i=0;i<home.size();i++) {
            int minDist = Integer.MAX_VALUE;
            for(int j=0;j<store.size();j++) {
                if(visited[j]) {
                    int dist = Math.abs(store.get(j).x-home.get(i).x)+Math.abs(store.get(j).y-home.get(i).y);
                    minDist = Math.min(dist, minDist);
                }
            }
            totalDist += minDist;
        }

        ans = Math.min(totalDist, ans);
    }
}