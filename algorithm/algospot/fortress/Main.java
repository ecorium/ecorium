import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;



public class Main {
	static ArrayList<TreeNode> leafList;
	static TreeNode root;
	static int move;

	public static void main(String[] args) throws IOException {

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine().trim());

			TreeNode[] treeList = new TreeNode[N];
			
			for (int n = 0; n < N; n++) {
				String[] values = br.readLine().trim().split(" ");
				
				int x1 = Integer.parseInt(values[0]);
				int y1 = Integer.parseInt(values[1]);
				int r1 = Integer.parseInt(values[2]);

				Node data= new Node(x1, y1, r1);
				treeList[n] = new TreeNode(data);
			}
			
			Arrays.sort(treeList, new Comparator<TreeNode>() {

				@Override
				public int compare(TreeNode o1, TreeNode o2) {
					if (o1.data.radian > o2.data.radian) {
						return -1;
					} else if (o1.data.radian < o2.data.radian) {
						return 1;
					}
					return 0;
				}
			});

			root = treeList[0];
			
			for (int i = treeList.length - 1; i >= 0; i--) {
				TreeNode a1 = treeList[i];
				
				for (int j = i - 1; j >= 0; j--) {
					TreeNode a2 = treeList[j];
					
					if (a2.isInclude(a1.data)) {
						a2.children.add(a1);
						a1.parent = a2;
						a1.depth = a2.depth + 1;
						
						for (TreeNode child : a1.children) {
							child.updateDepth();
						}
						break;
					}
				}
			}
			
			leafList = new ArrayList<>();
			root.lookUp1();
			
			int answer = 0;
			if (leafList.size() == 1) {
				answer = leafList.get(0).depth;
			} else {
				Collections.sort(leafList, new Comparator<TreeNode>() {

					@Override
					public int compare(TreeNode o1, TreeNode o2) {
						if (o1.depth > o2.depth) {
							return -1;
						} else if (o1.depth < o2.depth) {
							return 1;
						}
						return 0;
					}
					
				});
				
				for (int i = 0; i < leafList.size(); i++) {
					TreeNode a1 = leafList.get(i);
							
					for (int j = i + 1; j < leafList.size(); j++) {
						TreeNode a2 = leafList.get(j);	
						
						move = 0;
						a1.compareParent(a2);
						answer = Math.max(answer, move);
					}
				}
			}
			System.out.println(answer);

		}
		
		br.close();
	}
	
	private static class TreeNode {
		TreeNode parent;
		Node data;
		ArrayList<TreeNode> children;
		int depth;
		
		public TreeNode(Node node) {
			this.data = node;
			this.depth = 0;
			children = new ArrayList<>();
		}

		public void updateDepth() {
			if (this.children.size() == 0) {
				this.depth++;
				return;
			}
	
			this.depth++;
			for (TreeNode child : this.children) {
				child.updateDepth();
			}
		}

		public void compareParent(TreeNode a2) {
			TreeNode a1 = this;
			
			if (a1.parent == a2.parent) {
				move += 2;
				return;
			}

			int depth_a1 = a1.depth;
			int depth_a2 = a2.depth;
			
			if (depth_a1 == depth_a2) {
				move += 2;
				a1.parent.compareParent(a2.parent);
			} else if (depth_a1 > depth_a2) {
				move++;
				a1.parent.compareParent(a2);
			} else {
				move++;
				a2.parent.compareParent(a1);
			}
		}

		public void lookUp1() {
			
			if (this.children.size() == 0) {
				leafList.add(this);
				return;
			}
			
			for (TreeNode child : this.children) {
				child.lookUp1();
			}
		}
		
		public int sqrt(int x) {
			return x * x;
		}
		
		public boolean isInclude(Node v) {
			Node u = this.data;
			
			return ((u.radian > v.radian) &&
				(sqrt(u.x - v.x) + sqrt(u.y - v.y) < sqrt(u.radian - v.radian)));
		}
	}

	private static class Node {
		
		int x;
		int y;
		int radian;
		
		public Node(int x1, int y1, int r1) {
			this.x = x1;
			this.y = y1;
			this.radian = r1;
		}
	}
}