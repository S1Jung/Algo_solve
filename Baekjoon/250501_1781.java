import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int num, count;
        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Node> al = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            al.add(new Node(a,b));
        }

        Collections.sort(al, (a,b)-> {
           if(a.num!=b.num) return a.num-b.num;
           return a.count-b.count;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(Node curr : al) {
            if(pq.size() < curr.num) {
                pq.add(curr.count);
            } else if (!pq.isEmpty() && pq.peek()<curr.count) {
                // minimum num of curr.num will be the same as pq.size() cause pq.size() is adding regularly
                // so whatever the count of node comes, num of node won't be a matter in priority queue
                // so comparing the least one -> deleting the minimum one and -> adding new one
                pq.poll();
                pq.add(curr.count);
            }
        }

        int sum = 0;
        while(!pq.isEmpty()) sum += pq.poll();
        System.out.println(sum);
    }

}

