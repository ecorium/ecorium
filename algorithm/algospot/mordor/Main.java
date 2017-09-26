import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static Node[] seg;
	
	public static void main(String[] args) throws IOException {

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int t = 0; t < T; t++) {
			
			String[] NQ = br.readLine().trim().split(" ");
			int N = Integer.parseInt(NQ[0]);
			int Q = Integer.parseInt(NQ[1]);
			
			int height = (int)(Math.ceil((Math.log(N) / Math.log(2))));
			int maxSize = (int)(Math.pow(2, height + 1) - 1);
			
			seg = new Node[maxSize + 1];
			
			String[] routes = br.readLine().trim().split(" ");
			
			init(1, 1, N, routes);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < Q; i++) {
				String[] openRoute = br.readLine().trim().split(" ");
				
				int start = Integer.parseInt(openRoute[0]);
				int end = Integer.parseInt(openRoute[1]);
				
				if (start == end) {
					sb.append(0 + "\n");
				} else {
					Node result = query(1, 1, N, start + 1, end + 1);
					sb.append((result.maxVal - result.minVal) + "\n");
				}
			}
			
			System.out.print(sb.toString());
		}
		
		br.close();
		
	}

	private static Node query(int node, int left, int right, int start, int end) {

		if (start > right || end < left) {
			return null;
		}
		
		if (start <= left && right <= end) {
			return seg[node];
		}
		
		int mid = (left + right) / 2;
		Node n1 = query(node * 2, left, mid, start, end);
		Node n2 = query(node * 2 + 1, mid + 1, right, start, end);
		
		int minVal = Math.min(n1 != null ? n1.minVal : Integer.MAX_VALUE, n2 != null ? n2.minVal : Integer.MAX_VALUE); 
		int maxVal = Math.max(n1 != null ? n1.maxVal : Integer.MIN_VALUE, n2 != null ? n2.maxVal : Integer.MIN_VALUE);
		
		return new Node(minVal, maxVal);
	}

	private static void init(int node, int left, int right, String[] routes) {

		if (left == right) {
			int value = Integer.parseInt(routes[left - 1]);
			seg[node] = new Node(value, value);
			return;
		}
		
		int mid = (left + right) / 2;
		init(node * 2, left, mid, routes);
		init(node * 2 + 1, mid + 1, right, routes);
		
		int minVal = Math.min(seg[node * 2].minVal, seg[node * 2 + 1].minVal); 
		int maxVal = Math.max(seg[node * 2].maxVal, seg[node * 2 + 1].maxVal);
		
		seg[node] = new Node(minVal, maxVal);
	}
	
	private static class Node {
		int minVal;
		int maxVal;
		
		Node (int minVal, int maxVal) {
			this.minVal = minVal;
			this.maxVal = maxVal;
		}
	}

}