package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_1600_말이되고픈원숭이 {

	private static int K;
	private static int N,M;
	private static int[][] map;
	private static int MIN;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine()); 
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		MIN = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < M; j++) {
				map[i][j] = st.nextToken().charAt(0)-'0';
			}
		}

		if(N==1 && M==1) {
			System.out.println(0);
			return;
		}
		
		bfs(0,0);
		System.out.println(MIN == Integer.MAX_VALUE? -1 : MIN);
	}

	//우하좌상
	static int[] di = {0,1,0,-1};
	static int[] dj = {1,0,-1,0};
	//horse
	static int[] hi = {1,2,2,1,-1,-2,-2,-1};
	static int[] hj = {2,1,-1,-2,-2,-1,1,2};
	
	private static void bfs(int si, int sj) {

		Queue<Point> queue = new LinkedList<Point>();
			
		visited[si][sj] = true;
		queue.add(new Point(si,sj,K));
		
		int result=-1;
		while(!queue.isEmpty()) {
			int size = queue.size();
			result++;
			while(size --> 0) {
				Point now = queue.poll();
				
				if(now.i == N-1 && now.j== M-1) {
					MIN = Math.min(result, MIN);
					return;
				}
				
				for(int x=0; x<2; x++) {
					switch (x) {
					case 0:	//인접
						for(int d=0; d<4; d++) {
							int next_i = now.i + di[d];
							int next_j = now.j + dj[d];

//							if(next_i == N-1 && next_j== M-1) {
//								MIN = Math.min(result, MIN);
//								return;
//							}
							
							if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M)
								continue;
							
							if(map[next_i][next_j] == 1 || visited[next_i][next_j]) 
								continue;
							
							//System.out.println("인접 // next.i : "+next_i+ " / next.j : "+next_j + "/ result : "+result);

							visited[next_i][next_j] = true;
							queue.add(new Point(next_i, next_j, now.k));
							
						}
						break;

					case 1:	//말
						if(now.k <= 0) break;
						for(int d=0; d<8; d++) {
							int next_i = now.i + hi[d];
							int next_j = now.j + hj[d];
							

							if(next_i < 0 || next_i >= N || next_j < 0 || next_j >= M)
								continue;
							
							if(map[next_i][next_j] == 1 || visited[next_i][next_j])
								continue;
							
//							if(next_i == N-1 && next_j== M-1) {
//								MIN = Math.min(result, MIN);
//								return;
//							}
							
							//System.out.println("말 // next.i : "+next_i+ " / next.j : "+next_j + "/ result : "+result);
							visited[next_i][next_j] = true;
							queue.add(new Point(next_i, next_j, now.k-1));
						}
						
						break;
					}
				}
			}
		}
		
	}
	
	static class Point{
		int i,j,k;
		Point(int i, int j, int k){
			this.i = i;
			this.j = j;
			this.k = k;
		}
	}

}
