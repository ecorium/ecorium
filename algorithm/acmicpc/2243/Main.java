import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int CANDY_MAXIMUM_LEVEL = 1000000;
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		
		int height = (int)Math.ceil(Math.log(CANDY_MAXIMUM_LEVEL)/Math.log(2));
		int maxSize = (int)Math.pow(2, height + 1) - 1;
		
		tree = new int[maxSize + 1];
		
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			String[] values = br.readLine().trim().split(" ");
			
			int action = Integer.parseInt(values[0]);
			
			if (action == 1) {
				int pick = Integer.parseInt(values[1]);
				// pop
				sb.append(getCandy(1, 1, CANDY_MAXIMUM_LEVEL, pick) + "\n");
			} else {
				// push or pop
				int candyNum = Integer.parseInt(values[1]);
				int candyCnt = Integer.parseInt(values[2]);

				updateCandy(1, 1, CANDY_MAXIMUM_LEVEL, candyNum, candyCnt);
			}
		}
		
		System.out.println(sb.toString());

		br.close();
	}

	private static int getCandy(int node, int start, int end, int pick) {
		int result = 0;
		
		tree[node] -= 1;

		if (start == end) {
			return start;
		}
		
		int left = tree[node * 2];
		int right = tree[node * 2 + 1];
		
		int mid = (start + end) / 2;
		if (left < pick) {
			result = getCandy(node * 2 + 1, mid + 1, end, pick - left);
		} else {
			result = getCandy(node * 2, start, mid, pick);
		}
		
		return result;
	}

	private static void updateCandy(int node, int start, int end, int index, int diff) {
		if (start > index || end < index) {
			return;
		}
		
		if (start == end && start == index) {
			tree[node] += diff;
			return;
		} 
		
		int mid = (start + end) / 2;
		updateCandy(2 * node, start, mid, index, diff);
		updateCandy(2 * node + 1, mid + 1, end, index, diff);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
}
