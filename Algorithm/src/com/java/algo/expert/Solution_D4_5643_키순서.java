package com.java.algo.expert;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 플로이드와샬
 */

//public class Solution_D4_5643_키순서 {
//	private static int N,M;
//	private static int[][] map;
//	private static int result;
//	private static int cnt;
//	static boolean[] visited;
//	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int T = sc.nextInt();
//		
//		for(int t=1; t<=T; t++) {
//			N = sc.nextInt();
//			M = sc.nextInt();
//			map = new int[N+1][N+1];
//			for(int i=0; i<M; i++) {
//				int x = sc.nextInt();
//				int y = sc.nextInt();
//				map[x][y] = 1;
//			}
//			result = 0;
//			//구현 모든 정점에서 나보다 큰사람수 세고 나보다 작은 사람세고
//			//그 합이 N-1이면 알수있다. result++;
//			for(int i=1; i<=N; i++) {
//				cnt = 0;
//				//나보다 큰사람 세기
//				visited = new boolean[N+1];
//				dfs(i);
//				
//				//나보다 작은 사람세기
//				visited = new boolean[N+1];
//				dfs1(i);
//				
//				if(cnt == N-1) {
//					result++;
//				}
//			}
//			System.out.println("#"+t+" "+result);
//		}
//	}
//
//	private static void dfs(int idx) {
//		visited[idx] = true;
//		
//		for(int i=1; i<=N; i++) {
//			if(visited[i]) {
//				continue;
//			}
//			
//			if(map[idx][i] == 0) {	//관계없음
//				continue;
//			}
//			
//			cnt++;
//			dfs(i);
//		}
//	}
//	
//	//반대방향
//	private static void dfs1(int idx) {
//		visited[idx] = true;
//		
//		for(int i=1; i<=N; i++) {
//			if(visited[i]) {
//				continue;
//			}
//			
//			if(map[i][idx] == 0) {	//관계없음
//				continue;
//			}
//			
//			cnt++;
//			dfs1(i);
//		}
//	}
//}


//플로이드와샬
public class Solution_D4_5643_키순서 {
	private static int N,M;
	private static int[][] map;
	static int INF = 999_999;
	private static int result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int t=1; t<=T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N+1][N+1];
			for(int[] arr : map) {
				Arrays.fill(arr, INF);
			}
			
			for(int i=0; i<M; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				map[x][y] = 1;
			}
			
			for(int k=1; k<=N; k++) {
				for(int i=1; i<=N; i++) {
					for(int j=1; j<=N; j++) {
						if(map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			
			int[] counts = new int[N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(map[i][j] != INF) {
						counts[i]++;	//정보를 알게되엇다
						counts[j]++;
					}
				}
			}
			
			for(int i=1; i<=N; i++) {
				if(counts[i] == N-1) {	//나를 뺀 사람들의 정보를 다안다
					result++;
				}
			}
			
			result = 0;
			System.out.println("#"+t+" "+result);
		}
	}
}
