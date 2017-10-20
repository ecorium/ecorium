import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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
		
		dp[0] = arr[0];
		int count = 0;
		for (int i = 1; i < N; i++) {
			if (dp[count] < arr[i]) {
				count++;
				dp[count] = arr[i];
			} else {
				int idx = lower_bound(0, count, dp, arr[i]);
				dp[idx] = arr[i];
			}
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();

		System.out.println(count+1);
		br.close();
	}

	private static int lower_bound(int start, int end, int[] dp, int num) {
		
		while (end > start) {
			int mid = (start + end) / 2;

			if (dp[mid] < num) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		
		return end;
	}

}
