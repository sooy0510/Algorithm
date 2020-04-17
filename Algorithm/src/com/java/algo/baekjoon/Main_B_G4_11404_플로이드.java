package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 11404_플로이드
 * 메모리 : 45244KB
 * 시간 : 428ms
 * 길이 : 1600B
 * 풀이
 * 1. 최단쌍 경로문제 -> 플로이드 와샬
 * 2. 정점 적고, 간선많음 -> 인접행렬 사용
 * 3. 간선정보가 중복될때는 기존 간선의 가중치와 비교해서 더 작은 값으로 유지
 *
 */

public class Main_B_G4_11404_플로이드 {

	private static int[][] D;
	private static final int MAX = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		//최단쌍 경로문제 -> 플로이드 와샬
		//정점 적고, 간선많음 -> 인접행렬 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		D = new int[N][N];
		
		// 간선 정보 MAX값으로 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(D[i], MAX);
		}
		
		// 간선 정보 저장
		int s, e;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			s = Integer.parseInt(st.nextToken())-1;
			e = Integer.parseInt(st.nextToken())-1;
			//똑같은 간선 정보가 입력될때는 기존값이랑 비교해서 더 작은 값으로 넣어줌
			D[s][e] = Math.min(D[s][e], Integer.parseInt(st.nextToken()));
		}
		
		//플로이드 와샬
		for(int k=0; k<N; k++) {	//경유정점
			for(int i=0; i<N; i++) {	//시작정점
				if(k == i) continue;
				for(int j=0; j<N; j++) {	//도착정점
					if(k == j || i == j) continue;
						
					if(D[i][k] != MAX && D[k][j] != MAX && D[i][j] > D[i][k] + D[k][j])
						D[i][j] = D[i][k] + D[k][j];
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print((D[i][j] == MAX ? 0 : D[i][j])+" ");
			}
			System.out.println();
		}
		
	}

}
