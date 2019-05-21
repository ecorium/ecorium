import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		String[] NK = br.readLine().split(" ");
		int N = Integer.parseInt(NK[0]);
		int K = Integer.parseInt(NK[1]);
		int[] cost = new int[N+1];
		for (int i = 0; i < N; i++) {
			cost[i+1] = Integer.parseInt(br.readLine());
		}
		
		int[][] cache = new int[N + 1][K + 1];
		
		for (int i = 1; i <= N; i++) {
			int coin = cost[i];
			for (int j = 1; j <= K; j++) {
				int w = j;
				if (w < coin) {
					cache[i][j] = cache[i-1][j];
				} else if ((w - coin) == 0) {
					cache[i][j] = cache[i-1][j] + 1;
				} else {
					cache[i][j] = cache[i-1][j] + cache[i][w-coin];
				}
			}
		}
		
		System.out.println(cache[N][K]);
		br.close();
	}

}
