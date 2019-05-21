import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		int N = Integer.parseInt(br.readLine().trim());
		
		int[] arr = new int[N];
		for (int i = 0 ; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine().trim());
		}
		
		int[][] dp = new int[2][N];
		dp[0][0] = arr[N-1];
		dp[1][0] = arr[N-1];
		
		int answer = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2; j++) {
				if (dp[j][i] == 0) {
					continue;
				}
				
				if (j == 0) {
					if (i+1 < N) {
						int val1 = dp[j][i] + arr[N-1-i-1];
						dp[j+1][i+1] = Math.max(dp[j+1][i+1], val1);
						
						answer = Math.max(answer, dp[j+1][i+1]);
					}
					if (i+2 < N) {
						int val2 = dp[j][i] + arr[N-1-i-2];
						dp[j][i+2] = Math.max(dp[j][i+2], val2);
						answer = Math.max(answer, dp[j][i+2]);
					}
				} else {
					if (i+2 < N) {
						int val1 = dp[j][i] + arr[N-1-i-2];
						dp[j-1][i+2] = Math.max(dp[j-1][i+2], val1);
						answer = Math.max(answer, dp[j-1][i+2]);
					}
				}
			}
		}
		
		/*
		for (int j = 0; j < 2; j++) {
			for (int i = 0; i < N; i++) {
				System.out.print(dp[j][i] + " ");
			}
			System.out.println();
		}
		*/
		System.out.println(answer);
		br.close();
	}

}
