import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		br.close();

		int[] cache = new int[N + 1];
		
		for (int i = 2; i <= N; i++) {
			if (i == 2 || i == 3) {
				cache[i] = 1;
				continue;
			}
			int first = (i % 2 == 0) ? i / 2 : Integer.MAX_VALUE;
			int second = (i % 3 == 0) ? i / 3 : Integer.MAX_VALUE;
			int third = i - 1;

			int f = Integer.MAX_VALUE;
			if (first != Integer.MAX_VALUE) {
				f = cache[first] + 1;
			}
			
			int s = Integer.MAX_VALUE;
			if (second != Integer.MAX_VALUE) {
				s = cache[second] + 1;
			}
			
			int t = cache[third] + 1;
			
			int result = Math.min(f, Math.min(s, t));
			cache[i] = result;
		}

		System.out.println(cache[N]);
	}

}
