package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_G4_1504_특정한최단경로 {

	private static int V,E;
	private static int[][] adj;
	private static int v1, v2;
	private final static long INF = 800 * 1000;
	
	public static void main(String[] args) throws Exception{
		//최단경로 -> 다익스트라
		//간선많음 -> 인접리스트 이용
		//0 -> v1 -> v2 -> V-1
		//0 -> v2 -> v1 -> V-1
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new int[V][V];
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine()," ");
			//무향그래프
			int input1 = Integer.parseInt(st.nextToken())-1; 
			int input2 = Integer.parseInt(st.nextToken())-1; 
			int input3 = Integer.parseInt(st.nextToken());
			adj[input1][input2] = input3;
			adj[input2][input1] = input3;
		}
		
		//반드시 거쳐야 하는 두 정점 v1, v2
		st = new StringTokenizer(br.readLine()," ");
		v1 = Integer.parseInt(st.nextToken())-1;
		v2 = Integer.parseInt(st.nextToken())-1;
		
		long sum1 = getDistance(0,v1) + getDistance(v1,v2) + getDistance(v2,V-1);
		long sum2 = getDistance(0,v2) + getDistance(v2,v1) + getDistance(v1,V-1);
		
		
		//System.out.println(Arrays.toString(D));
		//System.out.println(Arrays.toString(check));
		if(sum1 > INF && sum2 > INF) {
			System.out.println(-1);
		}else {
			System.out.println(Math.min(sum1, sum2));
		}
	}

	private static long getDistance(int v11, int v22) {
		long[] D = new long[V];
		//Arrays.fill(D, Long.MAX_VALUE); 	//min값으로 채워나갈것임
		Arrays.fill(D, INF);
		boolean[] check = new boolean[V];
		
		//시작점은 v1으로
		D[v11] = 0;
		
		for(int i=0; i<V-1; i++) {
			long min = INF;
			int index = -1;
			for(int j=0; j<V; j++) {
				if(min > D[j]) {
					min = D[j];
					index = j;
				}
			}
			
			//연결이 없는경우 끝
			if(index == -1)
				break;
			
			
			//새로운 경로 업데이트
			for(int j=0; j<V; j++) {
				//아직 처리하지 않았으면서(!check[j), 경로가 존재하고(adj[index][j] != 0), index까지의 거리(D[index]) + index부터 j까지의 거리(adj[index][j])가 D[j]보다 작다면
				if(D[index]+adj[index][j] < D[j]) {
					D[j] = D[index] + adj[index][j];
				}
			}
			
			//처리된놈으로 체크
			//check[index] = true;
		}
		
		//System.out.println(Arrays.toString(D));
		//System.out.println(D[v22]);
		//System.out.println();
		return D[v22];
	}

}
