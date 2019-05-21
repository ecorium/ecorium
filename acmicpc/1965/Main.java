import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
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
		
		Arrays.fill(dp, 1);

		int answer = Integer.MIN_VALUE;

		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
				
				answer = Math.max(answer, dp[i]);
			}
		}
		
		System.out.println(answer);
		br.close();

	}

}
