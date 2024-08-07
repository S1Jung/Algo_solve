import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        copymap = new int[r][c];

        for(int i=0;i<r;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<c;j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        boolean divide = true;
        while(divide) {
            count ++;
            checkSize = 0;
            visited = new boolean[r][c];
            q = new ArrayDeque<>();
            Search();
            divide = Check(); // 여기서 모든 배열 0되는 것 확인, 나뉘었는지 확인 -> 다 0배열이면 ans는 0 아니면 count 출력.
        }
        System.out.println(ans);
    }
    static int r, c, count, ans, checkSize;
    static int [][] map, copymap;
    static boolean [][] visited;
    static Queue<Node> q;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static class Node {
        int row;
        int col;
        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    static void Search() {
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                copymap[i][j] = map[i][j];
            }
        }
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                if(map[i][j]!=0) {
                    for(int k=0;k<4;k++) {
                        int x = i+dr[k];
                        int y = j+dc[k];
                        if(x<0 || y<0 || x>=r || y>=c) continue;
                        if(map[x][y] == 0) copymap[i][j]--;
                        copymap[i][j] = Math.max(copymap[i][j],0);
                    }
                }
            }
        }
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++) {
                map[i][j] = copymap[i][j];
            }
        }
    }
    static void Spread (int x, int y) {
        visited[x][y]=true;
        while(!q.isEmpty()) {
            Node node = q.poll();
            for(int i=0;i<4;i++) {
                int dx = node.row+dr[i];
                int dy = node.col+dc[i];
                if(dx<0 || dy<0 || dx>=r|| dy>=c || visited[dx][dy]) continue;
                if(map[dx][dy] != 0 ) {
                    visited[dx][dy]=true;
                    q.add(new Node(dx,dy));
                }

            }
        }

    }
    static boolean Check () {
        boolean isZero = false;
        for(int i=0;i<r;i++) {
            for(int j=0;j<c;j++){
                if(map[i][j]!=0 && !visited[i][j]) {
                    checkSize++;
                    if(checkSize >1) {
                        isZero = false;
                        ans = count;
                        break;
                    }
                    isZero = true;
                    q.add(new Node(i,j));
                    Spread(i,j);
                }
            }
        }
        if(!isZero && checkSize == 1) {
            ans = 0;
            return false;
        } else return isZero;
    }
}