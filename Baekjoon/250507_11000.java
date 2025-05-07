import java.io.*;
import java.util.*;

public class Main {
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

        int N = Integer.parseInt(br.readLine());
        ArrayList<Node> al = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            al.add(new Node(s,e));
        }
        // List should be aligned with start of Node because it'll be compared with the end of Node in pq
        al.sort((a,b)->{
            if(a.start != b.start) return a.start-b.start;
            return a.end-b.end;
        });

        // pq should be aligned with the end
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
            if(a.end != b.end) return a.end-b.end;
            return a.start-b.start;
        });

        for(Node curr : al) {
            if(pq.isEmpty()) pq.add(curr);
            else {
                if(pq.peek().end>curr.start) pq.add(curr);
                else {
                    pq.poll();
                    pq.add(curr);
                }
            }
        }
        System.out.println(pq.size());
    }
}

