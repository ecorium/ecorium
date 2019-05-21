import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		char[] v = br.readLine().trim().toCharArray();
		char[] u = br.readLine().trim().toCharArray();
		
		int vLen = v.length;
		int uLen = u.length;
		
		int[][] dp = new int[vLen + 1][uLen + 1];
		
		for (int i = 1; i <= vLen; i++) {
			for (int j = 1; j <= uLen; j++) {
				if (v[i-1] == u[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[vLen][uLen]);
		br.close();
	}
}
