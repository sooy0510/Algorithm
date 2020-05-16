package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 5643_키순서
 * 메모리 : 90164KB
 * 시간 : 2281ms
 * 길이 : 1690B
 * 풀이
 * 1. DFS -> 자기보다 큰사람 / 자기보다 작은사람
 */

public class Solution_D4_5643_키순서_DFS {
	
	private static int N,M;
	private static int[][] map;
	private static int cnt;
	private static boolean[] visited;
	private static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			

			map = new int[N+1][N+1];	//관계를 저장할 배열
			 
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[x][y] = 1;	//x가 y보다 키가 작음
			}
			
			result = 0;
			//자기보다 작은 사람 + 자기보다 큰 사람 = N-1 이면 자신의 키가 몇 번쨰인지 알수있음
			for(int i=1; i<=N; i++) {
				cnt = 0;	
				//나보다 작은 사람
				visited = new boolean[N+1];
				dfs(i);
				
				//나보다 큰 사람
				Arrays.fill(visited, false);
				dfs1(i);
				
				if(cnt == N-1) {
					result++;
				}
			}
			
			
			System.out.println("#"+test+" "+result);
		}
	}

	private static void dfs1(int idx) {
		visited[idx] = true;
		
		for(int i=1; i<=N; i++) {
			if(visited[i])
				continue;
			
			if(map[idx][i] == 1) {
				cnt++;
				dfs1(i);
			}
		}
	}

	private static void dfs(int idx) {
		visited[idx] = true;
		
		for(int i=1; i<=N; i++) {
			if(visited[i])
				continue;
			
			if(map[i][idx] == 1) { //자기보다 작으면
				cnt++;
				dfs(i);
			}
		}
	}
}


