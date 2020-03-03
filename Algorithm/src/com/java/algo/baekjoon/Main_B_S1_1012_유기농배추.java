package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 1012_유기농배추
 * 메모리 : 15004KB
 * 시간 : 104ms
 * 길이 : 1994B
 * 풀이 
 * 1. BFS
 *
 */

public class Main_B_S1_1012_유기농배추 {

	private static int N;
	private static int M;
	private static int[][] map;
	private static int K;
	private static boolean[][] visited;
	private static int result;
 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			result = 0;
			
			for(int k=0; k<K; k++) {
				st = new StringTokenizer(br.readLine());
				int j = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken());
				map[i][j] = 1;
 			}
			
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(!visited[i][j] && map[i][j] == 1) {
						bfs(i,j);
					}
				}
			}
			
			sb.append(result+"\n");
		}
		System.out.println(sb);
		
	}

	//상하좌우
	static int[] di = {-1,1,0,0};
	static int[] dj = {0,0,-1,1};
	
	private static void bfs(int i, int j) {
		Queue<Point6> queue = new LinkedList<Point6>();
		visited[i][j] = true;
		queue.add(new Point6(i, j));
		
		while(!queue.isEmpty()) {
			Point6 now = queue.poll();
			
			for(int d=0; d<4; d++) {
				int next_i = now.i + di[d];
				int next_j = now.j + dj[d];
				
				if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M)
					continue;
				
				if(!visited[next_i][next_j] && map[next_i][next_j] == 1) {
					visited[next_i][next_j] = true;
					queue.add(new Point6(next_i, next_j));
				}
			}
		}
		result++;
	}

}
class Point6{
	int i,j;
	Point6(int i, int j){
		this.i = i;
		this.j = j;
	}
}
