package com.java.algo.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B_G4_17135_캐슬디펜스 {

	static int[][] map;
	static boolean [] visited;
	static int[] gungsu;	//궁수
	static int N,M,D;
	static int MAX = 0;
	static int attackCnt=0;
	//static int[][] result;
	static Stack<int[]> stack;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M];	//N행 바로 아래의 모든 칸에 성이 있음
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int i=0; i<N+1; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		//처음 궁수 위치 셋팅(np사용, 가장 작은 순열 만듦)
		map[N][M-3] = map[N][M-2] = map[N][M-1] = 1; 	
		
		//궁수 위치 np
		do {
			
		}while(!np());
		
	}

	private static boolean np() {
		
		//i-1구하기
		
		return false;
	}

}
