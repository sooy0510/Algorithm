package com.java.algo.expert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Floyed Washall
 * '모든 정점'에서 '모든 정점'으로의 최단 경로
 * 거쳐가는 정점을 기준으로 최단 거리를 구한다
 * 
 * 5643_키순서(Floyed Washall)
 * 메모리 : 93180KB
 * 시간 : 2815ms
 * 길이 : 1566B
 * 풀이
 * 1. Floyed Washall
 */

public class Solution_D4_5643_키순서_FloyedWashall {
	
	private static int N,M;
	private static int[][] map;
	private static int INF = 999_999;
	private static int result;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int test=1; test<=T; test++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			map = new int[N+1][N+1];	//관계를 저장할 배열
			
			for(int[] arr : map) {
				Arrays.fill(arr, INF);
			}
			 
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				map[x][y] = 1;	//x가 y보다 키가 작음
			}
			
			//플로이드 와샬
			for(int k=1; k<=N; k++) {
				for (int i = 1; i <=N; i++) {
					for (int j = 1; j <=N; j++) {
						if(map[i][j] > map[i][k] + map[k][j]) {	//k는 거쳐가는 정점
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}
			
			int[] counts = new int[N+1];
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(map[i][j] != INF) {
						counts[i]++;	//정보를 안다
						counts[j]++;
					}
				}
			}
			
			result = 0;
			for(int i=1; i<=N; i++) {
				if(counts[i] == N-1) {	//나를 뺀 사람들의 정보를 모두 안다
					result++;
				}
			}
			System.out.println("#"+test+" "+result);
		}
	}

}


