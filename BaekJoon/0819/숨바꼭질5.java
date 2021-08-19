package BaekJoon.BfsDfs;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 숨바꼭질5 {

    private static int n, k;
    private static int[][] visit = new int[2][500001];
 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
 
        Arrays.fill(visit[0], -1);
        Arrays.fill(visit[1], -1);
 
        visit[0][n] = 0;
 
        System.out.println((n == k) ? 0 : solution());
    }
 
    private static int solution() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
 
        int len, mod, time = 0;
        while (!queue.isEmpty()) {
            len = queue.size();
            time++;
            mod = time % 2; 
 
            for (int i = 0; i < len; i++) {
                int sb = queue.poll();
 
                if (sb - 1 >= 0 && visit[mod][sb - 1] == -1) {
                    queue.add(sb - 1);
                    visit[mod][sb - 1] = time;
                }
                if (sb + 1 <= 500000 && visit[mod][sb + 1] == -1) {
                    queue.add(sb + 1);
                    visit[mod][sb + 1] = time;
                }
                if (sb * 2 <= 500000 && visit[mod][sb * 2] == -1) {
                    queue.add(sb * 2);
                    visit[mod][sb * 2] = time;
                }
            }
 
            int bro = getBro(time); 
            if (bro > 500000) break; 
            if (visit[mod][bro] != -1) return time; 
        }
 
        return -1;
    }
 
    private static int getBro(int n) {
        return k + (n * (n + 1) / 2);
    }

}
