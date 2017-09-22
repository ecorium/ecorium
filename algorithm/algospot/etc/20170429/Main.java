import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[] seg;
	static final int MAX_TARGET_NUM = 100000;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			String[] NM = br.readLine().trim().split(" ");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			
//			int maxHeight = (int)(Math.ceil(Math.log(MAX_TARGET_NUM)/Math.log(2)));
			int height = (int)(Math.ceil(Math.log(N)/Math.log(2)));
			int maxSize = (int)(Math.pow(2, height + 1) - 1);
			
			seg = new int[maxSize + 1];
			
			init(1, 1, N);
			
			for (int i = 0; i < M; i++) {
				String[] values = br.readLine().trim().split(" ");
				int start = Integer.parseInt(values[0]);
				int end = Integer.parseInt(values[1]);
				
				int result = query(1, 1, N, start, end);
				System.out.println(result);
			}
		}
		
		br.close();

	}
	
	private static void update(int node, int left, int right, int index, int value) {
		
		if (left > index || right < index) {
			return;
		}
		
		if (left == right) {
			seg[node] = value;
		}
		
		int mid = (left + right) / 2;
		update(node * 2, left, mid, index, value);
		update(node * 2 + 1, mid + 1, right, index, value);

		seg[node] = seg[node * 2] + seg[node * 2 + 1];
	}

	private static int query(int node, int left, int right, int start, int end) {
		
		if (left > end || right < start) {
			return 0;
		}
		
		if (start <= left && right <= end) {
			return seg[node];
		}
		
		int mid = (left + right) / 2;
		int lr = query(node * 2, left, mid, start, end);
		int rr = query(node * 2 + 1, mid + 1, right, start, end);

		return lr + rr;
	}

	private static void init(int node, int left, int right) {
		
		if (left == right) {
			seg[node] = 1;
			return;
		}
		
		int mid = (left + right) / 2;
		init(node * 2, left, mid);
		init(node * 2 + 1, mid + 1, right);
		seg[node] = seg[node * 2] + seg[node * 2 + 1];
	}

}
