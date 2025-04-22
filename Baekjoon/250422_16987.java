import java.io.*;
import java.util.*;

public class Main {
    static int N, ans;
    static ArrayList<Egg> al;
    static class Egg {
        int dur, weight;
        public Egg(int dur, int weight) {
            this.dur = dur;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        al = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int dur = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            al.add(new Egg(dur, weight));
        }

        ans = 0;
        Throw(0);
        System.out.println(ans);
    }

    static void Throw(int index) {
        if(index == N) {
            int count = 0;
            for(Egg egg : al) if(egg.dur<=0) count++;
            ans = Math.max(ans, count);
            return;
        }

        if(al.get(index).dur<=0) {
            Throw(index+1);
            return; // have to return cuz below codes should not be activated
        }

        boolean isPossible = false;
        for(int i=0;i<N;i++) {
            if(i==index || al.get(i).dur<=0) continue;

            isPossible = true;
            al.get(index).dur -= al.get(i).weight;
            al.get(i).dur -= al.get(index).weight;
            Throw(index+1);
            al.get(index).dur += al.get(i).weight;
            al.get(i).dur += al.get(index).weight;
        }

        if(!isPossible) Throw(index+1);
    }
}

