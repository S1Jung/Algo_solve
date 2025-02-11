import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        request = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            request[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());

        Arrays.sort(request);

        ans = 0;
        int total = 0;
        for(int num : request) {
            total += num;
        }
        if(total <= M) ans = request[request.length-1];
        else Search();
        System.out.println(ans);
    }
    static int [] request;
    static int M, ans;
    static void Search() {
        // 상한액이 주어진 배열보다 작은 수 일 수도 있다. 따라서 0부터 시작한다.
        int left = 0;
        int right = request[request.length-1];

        while(left<=right) {
            int mid = (left+right)/2;
            int total = 0;
            for(int num : request) {
                if(num <= mid) total += num;
                else total += mid;
            }
            if(total<=M) {
                left = mid+1;
                ans = mid;
            } else {
                right = mid-1;
            }
        }

    }
}

