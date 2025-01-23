import java.io.*;
import java.util.*;

public class Main {
    public static class Node {
        int dur;
        boolean exist;
        public Node(int dur, boolean exist) {
            this.dur = dur;
            this.exist = exist;
        }
    }

    static int N,K,ans;
    static ArrayList<Node> al;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        al = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<2*N;i++) {
            al.add(new Node(Integer.parseInt(st.nextToken()), false));
        }
        ans = 0;

        while(K>0) {
            Move();
        }

        System.out.println(ans);
    }

    static void Move() {
        ans++;
        // step 1
        al.add(0,al.remove(al.size()-1));
        if(al.get(N-1).exist) al.get(N-1).exist = false;

        // step 2
        for(int i=N-2;i>=0;i--) {
            if(!al.get(i).exist || al.get(i+1).dur<=0 || al.get(i+1).exist) continue;
            al.get(i).exist = false;
            al.get(i+1).exist = true;
            al.get(i+1).dur --;
            if(al.get(i+1).dur == 0) K--;
            if(i+1 == N-1) al.get(i+1).exist = false;
        }

        // step 3
        if(al.get(0).dur > 0) {
            al.get(0).exist = true;
            al.get(0).dur--;
            if(al.get(0).dur == 0) K--;
        }

        // step 4는 while 조건문 안에 K에 관한 식을 넣었고 시간을 단축하기 위해 함수 안에 K를 줄이는 방식으로 했기에 이미 적용됐다.
    }
}