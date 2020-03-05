package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 11403_경로찾기
 * 메모리 : 16080KB
 * 시간 : 140ms
 * 길이 : 1331B
 * 풀이
 * 1. 가중치가 없는 방향 그래프
 * 2. 각 정점을 기준으로 DFS돌면서 간선이 존재할때마다 방문처리를 해주었다
 * => 방문했다면 기준 정점과 방문 정점간에 연결되었기 때문에 경로가 존재하는 것이 됨
 *
 */

public class Main_B_S1_11403_경로찾기 {

	private static int N;
	static int start,des;
	private static int[][] map;
	private static int[] visited;
	private static int[][] result;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new int[N];	//정점
		result = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//dfs
		for(int i=0; i<N; i++) {
			//한 정점을 기준으로 DFS를 돌면서 방문여부 체크
			//방문했다면 기준 정점과 방문 정점간의 연결되었기 때문에 경로가 존재함
			dfs(map,visited,i);
			for(int j=0; j<N; j++) {
				result[i][j] = visited[j];
			}
			Arrays.fill(visited, 0);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j]+" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int[][] map, int[] visited, int v) { //start, destination
		
		for(int j=0; j<N; j++) {
			if(map[v][j] == 1 && visited[j] == 0) {
				visited[j] = 1;
				dfs(map,visited,j);
			}
		}
		
	}

}
