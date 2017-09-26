import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
		
	public static void main(String[] args) throws IOException {

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		int N = Integer.parseInt(br.readLine());
		int[][] maze = new int[N][N];
		long[][] result = new long[N][N];
		
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result[0][0] = 1;
		
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				int point = maze[x][y];
				
				if (result[x][y] == 0) {
					continue;
				}
				
				if (x == (N - 1) && y == (N - 1)) {
					break;
				}
				
				if (y + point < N) {
					result[x][y + point] += result[x][y];
				}
				
				if (x + point < N) {
					result[x + point][y] += result[x][y];
				}
			}
		}
		
        System.out.println(result[N - 1][N - 1]);
		br.close();
	}

}
