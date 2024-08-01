import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];
        ans = new int[2];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i]= Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N-1;i++) {
            Search(arr[i],i+1,N-1);
        }

        System.out.println(ans[0]+" "+ans[1]);
    }

    static int [] arr;
    static int [] ans;
    static int min = Integer.MAX_VALUE;
    static void Search (int point, int left, int right) {
        while(left<=right) {
            int mid = (left+right)/2;
            int sum = Math.abs(point + arr[mid]);
            if(sum<min) {
                min = sum;
                ans[0]=point;
                ans[1]=arr[mid];
            }
            if(point+arr[mid]<0) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

    }
}