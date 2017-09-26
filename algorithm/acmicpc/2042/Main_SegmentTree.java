import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
//import java.util.ArrayList; 
//import java.util.Vector;

public class Main {
	static int N = 0;
	static int M = 0;
	static int K = 0;

	static long[] arr1 = null;
	static long[] tree1 = null;

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		String[] NMK = br.readLine().trim().split(" ");
		N = Integer.parseInt(NMK[0]);
		M = Integer.parseInt(NMK[1]);
		K = Integer.parseInt(NMK[2]);
		
//		System.out.println(N + " " + M + " " + K);

		arr1 = new long[N + 1];
//		tree1 = new Long[4*N + 1];
		double height = Math.ceil(Math.log(N) / Math.log(2));

//		double size = Math.pow(2,  height + 1) - 1;
		double size = 2 * Math.pow(2, height) - 1;
		tree1 = new long[(int)size];

		for (int n = 1; n <= N; n++) {
			Long value = Long.parseLong(br.readLine().trim());
			arr1[n] = value;
		}
		// init
		init(1, 1, N);
		
		int behaviour = 0;
		int value1 = 0;
		int value2 = 0;
		
//		System.out.println(query(1, 1, N, 1, N));
		
		for (int mk = 0; mk < (M + K); mk++) {
			String[] values = br.readLine().trim().split(" ");
			behaviour = Integer.parseInt(values[0]);
			value1 = Integer.parseInt(values[1]);
			value2 = Integer.parseInt(values[2]);
			if (behaviour == 1) {
				// int diff = (int)(value2 - arr1[value1]);
				// arr1[value1] = (long)value2;
				update(1, 1, N, value1, value2);
			} else {
				Long results = query(1, 1, N, value1, value2);
				System.out.println(results);
			}
		}
		
		br.close();
	}

	private static long update(int node, int left, int right, int index, int value) {

		if (index < left || right < index) {
			return tree1[node];
			// return;
		}
		
		if (left == right) {
			return tree1[node] = (long) value;
		}

       int mid = (left+right)/2;
		return tree1[node] = update(node*2, left, mid, index, value) + update(node*2+1, mid+1, right, index, value);
	}

	private static long query(int node, int left, int right, int start, int end) {
		if (left > end || right < start) {
			return (long) 0;
		}
//		else if (left == right || left == start && right == end) {
		else if (left >= start && right <= end) {
			return tree1[node];
		}
		
		int mid = (left+right)/2;
		return query(node*2, left, mid, start, end) + query(node*2+1, mid+1, right, start, end);
	}

	private static long init(int node, int left, int right) {
		if (left == right) {
			tree1[node] = arr1[left];
		} else {
			int mid = (left+right)/2;
			tree1[node] = init(node*2, left, mid) + init(node*2+1, mid+1, right);
		}
		
		return tree1[node];
	}
}
