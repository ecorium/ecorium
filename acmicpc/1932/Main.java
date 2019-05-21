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
		int sizeofArray = (1 + N) * N / 2;
		int[] cache = new int[sizeofArray + 1];
		
		int idx = 1;
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			String[] values = br.readLine().split(" ");
			for (int j = 0; j < i; j++) {
				cache[idx++] = Integer.parseInt(values[j]);
			}
		}
		/*
		// 1
		cache[1] += cache[0];
		// 2
		cache[2] += cache[1];
		cache[3] += cache[1];
		// 3
		cache[4] += cache[2];
		cache[5] = Math.max(cache[5] + cache[2], cache[5] + cache[3]);
		cache[6] += cache[3];
		// 4
		cache[7] += cache[4];;
		cache[8] = Math.max(cache[8] + cache[4], cache[8] + cache[5]);
		cache[9] = Math.max(cache[9] + cache[5], cache[9] + cache[6]);
		cache[10] += cache[6];
		// 5
		cache[11] += cache[7];
		cache[12] = Math.max(cache[12] + cache[7], cache[12] + cache[8]);
		cache[13] = Math.max(cache[13] + cache[8], cache[13] + cache[9]);
		cache[14] = Math.max(cache[14] + cache[9], cache[14] + cache[10]);
		cache[15] += cache[10];
		*/
		idx = 2;
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				if (j == 1) {
					cache[idx] = cache[idx] + cache[idx - i + 1];
				} else if (j == i) {
					cache[idx] = cache[idx] + cache[idx - i];
				} else {
					int left = cache[idx] + cache[idx - i + 1];
					int right = cache[idx] + cache[idx - i];
					cache[idx] = Math.max(left, right); 
				}
				answer = Math.max(answer, cache[idx]);
				idx++;
			}
		}

		System.out.println(answer);
		br.close();

	}

}
