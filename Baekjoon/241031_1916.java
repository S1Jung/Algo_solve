import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int end;
        int value;
        public Node(int end, int value) {
            this.end = end;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }

    }
    static ArrayList<ArrayList<Node>> Graph;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        Graph = new ArrayList<>();
        visited = new boolean[N+1];
        dist = new int[N+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        for(int i=0;i<=N;i++) {
            Graph.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            Graph.get(start).add(new Node(end, value));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        System.out.println(Search(start, end));
    }

    static int Search (int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] =0;

        while(!pq.isEmpty()) {
            Node curr = pq.poll();
            if(!visited[curr.end]) {
                visited[curr.end] = true;
                for(Node node : Graph.get(curr.end)) {
                    if(!visited[node.end] && dist[node.end] > dist[curr.end] + node.value) {
                        dist[node.end] = dist[curr.end] + node.value;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        return dist[end];
    }
}