import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int answer;
	static int[] path;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		String[] MC = br.readLine().trim().split(" ");
		int M = Integer.parseInt(MC[0]);
		int C = Integer.parseInt(MC[1]);
		
		int[][] data = new int[C+1][M+1];
		int[][] dp = new int[C+1][M+1];
		int[][] route = new int[C+1][M+1];
		
		path = new int[C+1];

		int[] money = new int[M + 1];
		
		for (int i = 1; i <= M; i++) {
			String[] values = br.readLine().trim().split(" ");
			money[i] = Integer.parseInt(values[0]);
			
			for (int j = 1; j <= C; j++) {
				data[j][i] = Integer.parseInt(values[j]);
			}
		}
		
		int answer = Integer.MIN_VALUE;
		
		for (int i = 1; i <= C; i++) {
			for (int j = 1; j <= M; j++) {
				for (int k = 0; k <= j; k++) {
					if (dp[i][j] < dp[i - 1][j - k] + data[i][k]) {
						dp[i][j] = dp[i - 1][j - k] + data[i][k];
						route[i][j] = k;
					}
					
					answer = Math.max(answer, dp[i][j]);
				}
			}
		}
		
		System.out.println(answer);
		findRoute(route, C, M);
		for (int i = 1; i <= C; i++) {
			System.out.print(path[i] + " ");
		}
		br.close();
	}

	private static void findRoute(int[][] route, int c, int m) {
		if (c == 0) {
			return;
		}

		int val = route[c][m];
		path[c] = val;
		findRoute(route, c - 1, m - val);
	}

}
