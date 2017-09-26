import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

//		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		long[] a = new long[N + 1];
		long[] tree1 = new long[N + 1];
		
		for (int i = 1; i <= N; i++) {
			long value1 = Long.parseLong(br.readLine());
			a[i] = value1;
			update(tree1, i, a[i]);
		}
		
		M += K;
		
		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int action = Integer.parseInt(st.nextToken());
			int value1 = Integer.parseInt(st.nextToken());
			int value2 = Integer.parseInt(st.nextToken());
			
			if (action == 1) {
				long diff = value2 - a[value1];
				a[value1] = (long)value2;
				update(tree1, value1, diff);
			} else {
				long result = (sum(tree1, value2) - sum(tree1, value1 - 1));
				sb.append(result + "\n");
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

	private static long sum(long[] tree1, int index) {
		long answer = 0;
		
		while (index > 0) {
			answer += tree1[index];
			index -= (index & -index);
		}
		
		return answer;
	}

	private static void update(long[] tree1, int index, long diff) {
		while (index < tree1.length) {
			tree1[index] += diff;
			index += (index & -index);
		}
	}

}
