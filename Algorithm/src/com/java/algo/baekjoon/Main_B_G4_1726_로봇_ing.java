package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_G4_1726_로봇_ing {

	static int M,N;
	static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start_i, start_j, start_d;
		int end_i, end_j, end_d;
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M+1][N+1];
		
		for (int i = 1; i < M+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				map[i][j] = st.nextToken().charAt(0)-'0';
			}
		}
		
		st = new StringTokenizer(br.readLine());
		start_i = Integer.parseInt(st.nextToken());
		start_j = Integer.parseInt(st.nextToken());
		start_d = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		end_i = Integer.parseInt(st.nextToken());
		end_j = Integer.parseInt(st.nextToken());
		end_d = Integer.parseInt(st.nextToken());
		
//		for (int i = 1; i < M+1; i++) {
//			for (int j = 1; j < N+1; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//		
//		System.out.println(start_i+" "+start_j+" "+start_d);
//		System.out.println(end_i+" "+end_j+" "+end_d);
		
		dfs(start_i, start_j, start_d);
		
	}

	// 동=1, 서=2, 남=3, 북=4
	static int[] di = {0,0,0,1,-1};
	static int[] dj = {0,1,-1,0,0};
	
	//좌,우
//	static int[] di = {0,0};
//	static int[] dj = {-1,1};
	
	private static void dfs(int i, int j, int d) {
		
		for(int s=0; s<=4; s++) {
			int next_i = i + di[s];
			int next_j = j + dj[s];
			int next_d;
			
			if(next_i < 1 || next_i >= M+1 || next_j < 1 || next_j >= N+1)
				continue;
			
			if(map[next_i][next_j] == 0) {
				next_d = s;
			}
		}
	}

}
