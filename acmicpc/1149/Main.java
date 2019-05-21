import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int COLOR = 3;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] rgb = br.readLine().trim().split(" ");
			if (i == 0) {
				for (int j = 0; j < COLOR; j++) {
					dp[i][j] = Integer.parseInt(rgb[j]);
				}
			} else {
				for (int j = 0; j < COLOR; j++) {
					for (int k = 0; k < COLOR; k++) {
						if (j == k) {
							continue;
						}
						
						int nextColor = Integer.parseInt(rgb[k]);
						int sum = dp[i-1][j] + nextColor;
						if (dp[i][k] == 0) {
							dp[i][k] = sum;
						} else {
							dp[i][k] = Math.min(dp[i][k], sum);
						}
					}
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < COLOR; i++) {
			answer = Math.min(answer, dp[N-1][i]);
		}

		System.out.println(answer);
		br.close();
	}

}
