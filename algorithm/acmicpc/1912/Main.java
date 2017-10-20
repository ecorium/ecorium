import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine().trim());
		int[] dp = new int[N];
		int[] arr = new int[N];
		
		String[] values = br.readLine().trim().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(values[i]);
		}

		int answer = Integer.MIN_VALUE;
		dp[0] = arr[0];
		answer = Math.max(answer, dp[0]);
		
		for (int i = 0; i < N-1; i++) {
			if (dp[i] + arr[i+1] > 0) {
				dp[i+1] = dp[i] + arr[i+1];
			} else {
				dp[i+1] = 0;
			}
			answer = Math.max(answer, dp[i+1]);
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();
		
		System.out.println(answer);
		br.close();

	}

}
