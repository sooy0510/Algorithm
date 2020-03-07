package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 2583_영역구하기
 * 
 * (BFS)
 * 메모리 : 13864KB
 * 시간 : 88ms
 * 길이 : 2328B
 * 
 * (DFS)
 * 메모리 : 13608KB
 * 시간 : 84ms
 * 길이 : 2039B
 *
 */

public class Main_B_S1_2583_영역구하기 {

	private static int N;
	private static int M;
	private static int K;
	private static int[][] map;
	private static boolean[][] visited;
	static int result = 0;
	static int area;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		//areas = new int[10000];
		
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int sj = Integer.parseInt(st.nextToken()); 
			int si = Integer.parseInt(st.nextToken());
			int ej = Integer.parseInt(st.nextToken());
			int ei = Integer.parseInt(st.nextToken());
			
			for(int r=si; r<ei; r++) {
				for(int c=sj; c<ej; c++) {
					map[r][c] = 1;
				}
			}
		}

		
		//bfs
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				if(map[i][j] == 0 && !visited[i][j]) {
//					bfs(i,j);
//					list.add(area);
//					result++;
//				}
//			}
//		}
		
		//dfs
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0 && !visited[i][j]) {
					dfs(i,j,1);
					list.add(area);
					result++;
					area = 0;
				}
			}
		}
		
		System.out.println(result);
		Collections.sort(list);
		for (int a : list) {
			System.out.print(a+" ");
		}
	}

	//우, 하, 좌, 상
	static int[] di = {0,1,0,-1};
	static int[] dj = {1,0,-1,0};

	private static void dfs(int i, int j, int c) {
		visited[i][j] = true;
		area++;
		
		int cnt = 0;
		for(int d=0; d<4; d++) {
			int next_i = i + di[d];
			int next_j = j + dj[d];

			if(next_i > -1 && next_i < N && next_j > -1 && next_j < M
					&& map[next_i][next_j] == 0 && !visited[next_i][next_j]) {
				dfs(next_i,next_j,c+1);
			}
			
			
		}
	}

	
	private static void bfs(int i, int j) {
		area = 0;
		
		Queue<Point7> queue = new LinkedList<Point7>();
		
		visited[i][j] = true;
		area++;
		queue.add(new Point7(i, j));
		
		while(!queue.isEmpty()) {
			Point7 now = queue.poll();
			
			for(int d=0; d<4; d++) {
				int next_i = now.i + di[d];
				int next_j = now.j + dj[d];
				
				
				if(next_i > -1 && next_i < N && next_j > -1 && next_j < M
						&& map[next_i][next_j] == 0 && !visited[next_i][next_j]) {
					visited[next_i][next_j] = true;
					area++;
					queue.add(new Point7(next_i, next_j));
				}
				
			}
		}
		
	}

}

class Point7{
	int i,j;
	Point7(int i, int j){
		this.i = i;
		this.j = j;
	}
}
