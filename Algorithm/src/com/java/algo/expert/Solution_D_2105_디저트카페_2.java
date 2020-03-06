package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;


/**
 * 
 * 
 *
 */

public class Solution_D_2105_디저트카페_2 {
	private static int max;
	private static int[][] map;
	private static int N;
	static boolean[] visited;
	static int si, sj;	//시작 좌표

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		visited = new boolean[101];
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			max = 0;

			//입력
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//dfs
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//새로 돌거니까 visited 초기화
					Arrays.fill(visited, false);
					si = i; sj = j;
					dfs(si, sj, 0, 1);	//좌표, 방향, 디저트수
				}
			}
			
			sb.append("#"+test+" "+(max==0?-1:max)+"\n");
		}
		System.out.println(sb);
	}

	//우하, 좌하, 좌상, 우상
	static int[] di = {1,1,-1,-1};
	static int[] dj = {1,-1,-1,1};
 	private static void dfs(int i, int j, int dir, int cnt) {
		
 		visited[map[i][j]] = true;	//i,j의 좌표를 방문해서 디저트도 먹고 방문처리도 하게 됨
 		
 		
		for(int d=dir; d<4; d++) {
			int next_i = i + di[d];
			int next_j = j + dj[d];
			
			if(next_i == si && next_j == sj && cnt >= 4) {
				if(cnt > max) {
					max = cnt;
					return;
				}
			}
			
			//경계안에 있고 아직 안먹어본 디저트면
			if(next_i > -1 && next_i < N && next_j > -1 && next_j < N
					&& !visited[map[next_i][next_j]]) {
				dfs(next_i, next_j, d, cnt+1);
			}
			
		}
		
		
		//dfs니까 한 정점 기준으로 탐색 끝냈으면 다시 되돌아가기
		visited[map[i][j]] = false;
	}
}
