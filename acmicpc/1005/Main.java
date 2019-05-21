import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("sample.txt"));
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int testcases = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < testcases; t++) {
			String[] NK = br.readLine().split(" ");
			int N = Integer.parseInt(NK[0]);
			int K = Integer.parseInt(NK[1]);
			
			Node[] nodes = new Node[N + 1];
			String[] D = br.readLine().split(" ");
			for (int n = 0; n <= N; n++) {
				if (n == 0) {
					nodes[n] = new Node(0, 0);
					nodes[n].entryPoints = Integer.MAX_VALUE;
					continue;
				}
				nodes[n] = new Node(n, Integer.parseInt(D[n-1]));
			}
			
			for (int k = 0; k < K; k++) {
				String[] XY = br.readLine().split(" ");
				int v = Integer.parseInt(XY[0]);
				int e = Integer.parseInt(XY[1]);
				
				nodes[v].edges.add(e);
				nodes[e].entryPoints++;
			}
			
			int W = Integer.parseInt(br.readLine());
			
			Queue<Node> q = new LinkedList<>();
			for (Node n : nodes) {
				if (n.entryPoints == 0) {
					q.add(nodes[n.vertex]);
				}
			}
			
			int answer = 0;
			while(!q.isEmpty()) {
				Node node = q.poll();
				int v = node.vertex;
				int cost = node.cost;
				int accumulation = node.acc;
				
				if (v == W) {
					answer = cost + accumulation;
					break;
				}
				
				for (int e : node.edges) {
					nodes[e].entryPoints--;
					nodes[e].acc = Math.max(nodes[e].acc, accumulation + cost);
					if (nodes[e].entryPoints == 0) {
						q.add(nodes[e]);
					}
				}
				nodes[v].entryPoints = Integer.MAX_VALUE;
			}
			System.out.println(answer);
		}
		br.close();

	}
	
	public static class Node {
		int cost;
		int acc;
		int entryPoints;		
		int vertex;
		ArrayList<Integer> edges;
		
		Node(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
			
			this.acc = 0;
			this.entryPoints = 0;
			this.edges = new ArrayList<>();
		}
	}

}
