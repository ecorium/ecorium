import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Algorithm: MST(Minimum Spanning Tree)
 * Problem: https://www.acmicpc.net/problem/1922
 * Kruskal Algorithm
 */

public class Main {
	static int[] roots;

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		roots = new int[V + 1];
		
		Queue<Place> pq = new PriorityQueue<>(new Comparator<Place>() {
			@Override
			public int compare(Place o1, Place o2) {
				if (o1.weight > o2.weight) {
					return 1;
				} else if (o1.weight < o2.weight) {
					return -1;
				}
				return 0;
			}
		});
		
		for (int i = 1; i <= V; i++) {
			roots[i] = i;
		}
	
		for (int i = 0; i < E; i++) {
			String[] values = br.readLine().trim().split(" ");
			
			int v1 = Integer.parseInt(values[0]);
			int v2 = Integer.parseInt(values[1]);
			int w = Integer.parseInt(values[2]);
			
			pq.add(new Place(v1, v2, w));
		}

		br.close();
		
		int answer = 0;
		int mstEdges = 0;

		while(!pq.isEmpty()) {
			Place pp = pq.poll();
			
			int v1 = pp.v1;
			int v2 = pp.v2;
			int w = pp.weight;

			if (mergeRoot(v1, v2)) {
				answer += w;
				mstEdges++;
			}
			
			if (mstEdges == (V - 1)) {
				break;
			}
		}
				
		System.out.println(answer);
	}
	
	static int findRoot (int vertex) {
		if (roots[vertex] == vertex) {
			return roots[vertex];
		}
		
		return roots[vertex] = findRoot(roots[vertex]);
	}
	
	static boolean mergeRoot(int u, int v) {
		int u1 = findRoot(u);
		int v1 = findRoot(v);
		
		if (u1 != v1) {
			roots[v1] = u1;
			return true;
		}
		
		return false;
	}
	

	private static class Place {
		int v1;
		int v2;
		int weight;
		
		Place (int v1, int v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}
	}
}
