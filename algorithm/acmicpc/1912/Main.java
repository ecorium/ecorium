import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine().trim());
		int[] dp = new int[N+1];
		int[] arr = new int[N+1];
		
		String[] values = br.readLine().trim().split(" ");
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(values[i-1]);
		}

		int answer = Integer.MIN_VALUE;
		
		for (int i = 0; i <= N-1; i++) {
			if (dp[i] + arr[i+1] > arr[i+1]) {
				dp[i+1] = dp[i] + arr[i+1];
			} else {
				dp[i+1] = arr[i+1];
			}
			answer = Math.max(answer, dp[i+1]);
		}
		
		System.out.println(answer);
		br.close();
	}
}
