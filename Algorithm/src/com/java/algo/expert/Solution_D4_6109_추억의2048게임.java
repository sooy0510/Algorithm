package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 6109_추억의2048게임
 * 메모리 : 43856KB
 * 시간 : 161ms
 * 길이 : 2136B
 * 풀이
 * 1. 이미 값이 합쳐진 칸은 또 접근하지 못하도록 방문처리 잘해주기
 */

public class Solution_D4_6109_추억의2048게임 {
	private static int N;
	private static int[][] map;
	private static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};	//상하좌우
	private static String input;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			input = st.nextToken();
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			switch (input) {
			case "up":
				for (int j = 0; j < N; j++) {
					for (int i = 1; i < N; i++) move(i,j,0);
				}
				break;
			case "down":
				for (int j = 0; j < N; j++) {
					for (int i = N-2; i >= 0; i--) move(i,j,1);
				}
				break;
			case "left":
				for (int i = 0; i < N; i++) {
					for (int j = 1; j < N; j++) move(i,j,2);
				}
				break;
			case "right":
				for (int i = 0; i < N; i++) {
					for (int j = N-2; j >= 0; j--) move(i,j,3);
				}
				break;
			}
			
			
			sb.append("#"+test+"\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j]+" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}

	private static void move(int i, int j, int d) {
		int ni = i + dir[d][0];
		int nj = j + dir[d][1];
		
		//범위 초과, 이미 값이 변한 칸은 건들지 않기
		if(ni < 0 || nj < 0 || ni >= N || nj >= N || visited[ni][nj] || visited[i][j])return; 	//범위초과
		
		//현재칸 값과 다음칸 값이 일치
		if(map[i][j] != 0 && map[i][j] == map[ni][nj]) {
			map[ni][nj] = map[i][j] * 2;
			map[i][j] = 0;
			visited[ni][nj] = true;
		}else if(map[ni][nj] == 0) {
			map[ni][nj] = map[i][j];
			map[i][j] = 0;
		}
		move(ni, nj, d);
		
	}

}
