import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int row, col;
        public Node (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static LinkedList<Node> ll;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int homex = Integer.parseInt(st.nextToken());
            int homey = Integer.parseInt(st.nextToken());

            ll = new LinkedList<>();
            for(int j=0;j<n;j++) {
                st = new StringTokenizer(br.readLine());
                int storex = Integer.parseInt(st.nextToken());
                int storey = Integer.parseInt(st.nextToken());
                ll.add(new Node(storex,storey));
            }
            st = new StringTokenizer(br.readLine());
            int rockx = Integer.parseInt(st.nextToken());
            int rocky = Integer.parseInt(st.nextToken());

            boolean flag = Search(homex,homey,rockx,rocky);
            if(flag) System.out.println("happy");
            else System.out.println("sad");
        }
    }
    static boolean Search (int homex, int homey, int rockx, int rocky) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(homex, homey));

        while(!q.isEmpty()) {
            Node curr = q.poll();
            if(Math.abs(curr.row-rockx)+Math.abs(curr.col-rocky)<=1000) return true;

            if(!ll.isEmpty()) {
                for(int j=0;j<ll.size();j++) {
                    if(Math.abs(curr.row-ll.get(j).row)+Math.abs(curr.col-ll.get(j).col)<=1000) {
                        q.add(new Node(ll.get(j).row, ll.get(j).col));
                        ll.remove(j);
                    }
                }
            }

        }

        return false;
    }
}